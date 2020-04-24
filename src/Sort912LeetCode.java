/**
 * 给定一个整数数组 nums，将该数组升序排列。
 * 示例 1：
 * 输入：[5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：[5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * 提示：
 * 1 <= A.length <= 10000
 * -50000 <= A[i] <= 50000
 * @author LemonLin
 * @Description :Sort912LeetCode
 * @date 20.2.21-21:27
 * 思路：用九种算法进行排序
 * 参考：https://blog.csdn.net/yjh_dream/article/details/81232851
 * 逆序对：https://www.icourse163.org/learn/ZJU-93001?tid=1207006212#/
 * learn/content?type=detail&id=1212031657&cid=1215166349
 * 对于下标i<j，如果A[i]>A[j],则称(i,j)是一对逆序对，排序其实就是为了消除逆序对。
 * 比如交换类的排序，交换两个元素，正好消除一个逆序对。
 * 所以要减低算法的时间复杂度(意思是让算法速度变快)，必须每次消去不止一个逆序对。
 */
public class Sort912LeetCode {
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }

    public int[] sortArray(int[] nums) {
        //*********插入类排序。
//       straightInsertionSort(nums);
//        binaryInsertionSort(nums);
//        shellSort(nums);
        //*********交换类排序
//        bubbleSort(nums);
        quickSort(nums);
        //*********选择类排序
//        selectSort(nums);
//        heapSort(nums);
        //*********归并排序
//        mergeSort(nums);
        mergeSort2(nums);//非递归写法
        return  nums;
    }

    /**
     * 直接插入排序
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
     * 找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)
     * 的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后
     * 挪位，为最新元素提供插入空间。
     * 关键点，首先，数组前部分被分成有序部分，后部分是无序部分。
     * 第二，代码实现循环外层是无序，循环内层时有序
     * 第三：需要在有序部分查找插入位置，是从后往前查找的。
     * 第四：因为是in-place排序（即只需用到O(1)),循环内层需要查找插入位置，移动腾
     * 出插入位置，移动是用有序部分的向后移nums[j+1]=nums[j];
     * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
     * 第五、和冒泡排序一样，判断条件是temp<nums[j]才交换，所以算法是稳定的。
     * bug1:
     * 输入:
     * [5,2,3,1]
     * 输出
     * [1,3,5,5]
     * 预期结果
     * [1,2,3,5]
     */
    private void straightInsertionSort(int[] nums){
        //待插入的无序元素为了防止挪出待插入位置时而被覆盖，需要用个临时变量保存起来。
        int temp =0;
        //最外层是无序数组
        for (int i=1;i<nums.length;i++){
            //里面一层是有序数组,j指向有序数组
            temp =  nums[i];
            //此时j是有序的最后一位，i是无序的第一位，i已经临时保存起来了,从后往前查找
            int j=0;
            //注意这里的只能用temp<nums[j]，不能用nums[i]<nums[j],因为i在遍历最后
            // 一个时，会被覆盖，导致nums[i]读取出错。
            //这里一定要注意插入排序易错的地方，判断条件一定要写在for循环中，如果用for
            //循环内的if判断，会出问题，不可明状，解决bug1
            for (j =i-1;j>=0&&temp<nums[j];j--){
                    //i是无序的第一位，i已经临时保存起来了,所以j+1这个位置不需要了。
                    nums[j+1] = nums[j];
            }
            //这里的j就是最开始的i,只不过现在i变了。
            nums[j+1]= temp;
        }
    }

    /**
     * 折半插入排序
     * 它的工作原理是在直接插入排序的基础进行优化，通过构建有序序列，对于未排序数据，
     * 在已排序序列中使用二分查找方法，找到相应位置并插入。插入排序在实现上，通常
     * 采用in-place排序（即只需用到O(1)的额外空间的排序），因而在找到最终的插入位置时，
     * 需要把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * 关键点：第一，数组前部分被分成有序部分，后部分是无序部分。
     * 第二，代码实现循环外层是无序，循环内层时有序
     * 第三：需要在有序部分查找插入位置，是从用二分法查找的。
     * 第四：因为是in-place排序（即只需用到O(1)),循环内层需要查找插入位置，移动腾
     * 出插入位置，移动是用有序部分的向后移nums[j+1]=nums[j];
     * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
     * 时间复杂度的分析可以看出虽然查找时间缩短到O(log2n),但是因为还是要一个一个将
     * 有序数组向后挪动，所以内循环还是有一个for循环，综合下来看还是O(n2)的复杂度。
     * bug1:
     * 输入:
     * [-2,3,-5]
     * 输出
     * [-5,3,-2]
     * 预期结果
     * [-5,-2,3]
     */
    private void binaryInsertionSort(int[] nums){
        //待插入的无序元素为了防止挪出待插入位置时而被覆盖，需要用个临时变量保存起来。
        int temp =0;
        //最外层是无序数组
        for (int i=1;i<nums.length;i++){
            //里面一层是有序数组,对有序数组用二分查找
            temp =  nums[i];
            int left=0;
            int right=i-1;
            int mid =0;
            //二分查找插入位置过程
            while (left<=right){
                mid = left+(right-left)/2;
                if (nums[mid]<temp){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
            //向后挪出插入位置,注意这里的j范围为left，因为看二分查找循环跳出条件应该为
            // left>right,所以肯定是left或者right相关的参数，不可能是mid
            //解决bug1，把left写成了mid
            for (int j=i-1;j>=left;j--){
                nums[j+1]=nums[j];
            }
            nums[left]=temp;
        }
    }

    /**
     * 希尔排序，为了一次交换消除更多的逆序对，引入了增量循环，将插入排序中大多数
     * 和1相关的都换成增量P，插入排序的时间复杂度小于O(n2)，具体涉及复杂数学证明，
     * 记住小于即可。
     * @param nums
     */
    private  void  shellSort(int [] nums){
        //外层是希尔排序的增量，里面两层主体框架是插入排序。注意希尔排序的P>0,不能
        // 有等号，因为除以2，一定最后会在0，如果用等号，就退不出循环了。
        for (int P=nums.length/2;P>0;P/=2){
        //两个for循环是插入排序变形
            int temp =0;
            for (int i=P;i<nums.length;i++){
                temp =  nums[i];
                int j=0;
                for (j =i-P;j>=0&&temp<nums[j];j-=P){
                    nums[j+P] = nums[j];
                }
                nums[j+P]= temp;
            }

        }
    }

    /**
     * 参考：https://www.icourse163.org/course/ZJU-93001
     * 冒泡排序，属于交换类排序，在LeetCode中提交超时，数据量过大。
     * 1、写冒泡排序先写内部循环，就是完成一次把最大值沉入最低部。注意这里的循环
     *条件是j<i;注意这个i,第一次肯定是num.length-1，因为沉入到的是最底部，所以遍历
     * 到最底部。那么第二次遍历，肯定是沉入到倒数第二个位置。所以应该是num.length-2。
     * 以此类推，所以有了外循环。
     * 2、写外循环，由1可得应该是 for (int i=nums.length-1;i>=0;i--){从大到小。
     * 3、有一种情况是，数组本来就有序，如果只有两个循环，那么内循环即使有序，都要
     * 比较一遍，有没有办法，有序的时候不需要交换呢，用上一个标志位flag，如果有序的
     * 情况下，就是一次遍历过程中都没有发生交换，就说明有序，就没有进入内循环的  if判断
     * if (nums[j]>nums[j+1]){中，那么flag等于Flase，可以直接退出循环。
     * 4、由3可得，冒泡排序最好的时间复杂度为O(n),最差情况还是O(n2)
     * 5、可以看到交换的判断是 if (nums[j]>nums[j+1]){，所以两个值如果相等，就
     * 不会交换，所以冒泡排序是稳定的排序。
     * 什么是稳定，就是两个值相等，在排序前的相对位置和排序后的相对位置没有改变。
     * 6、交换函数swap，一定要传入nums数组，否则，如果只传入两个数的话，因为是
     * 局部变化，出了函数就交换效果就消失。
     * 7、冒泡排序有一个其他排序没有的好处，就是冒泡排序适用于底层不管是数组存储还是
     * 链表存储，因为它使用的是两两交换。其他排序不是使用两两交换，如果底层是链表
     * 存储的话，会比较麻烦处理。
     */
    private void bubbleSort(int[] nums){
        for (int i=nums.length-1;i>=0;i--){
            boolean flag =false;
            for (int j=0;j<i;j++){
                if (nums[j]>nums[j+1]){
                    flag =true;
                    swap(nums,j,j+1);
                }
            }
            if (!flag )break;
        }
    }

    /**
     * 快速排序
     */
    private void quickSort(int []  nums){
        quickHelper(nums,0,nums.length-1);
    }
    private void quickHelper(int arr[],int left,int right){
        //递归出口
        if (left>=right){
            return;
        }
        int l=left;
        int r=right;
        //把这个pivot中心点设置成从数组左侧开始选取
        int pivot = arr[l];
        //三个循环，外部循环解决左右两个指针不能碰面的控制，内部的两个循环解决左边
        // 指针向右移动和右边指针向左移动
        while (l < r) {
            //第一个先选用的是低位的数组为参考对象，则从右边比较起来
            while (l < r && pivot <= arr[r]) r--;
            if (l < r) {
                arr[l] = arr[r];
                //交换之后，l向后移动一位
                l++;
            }
            while (l < r && arr[l] <= pivot) l++;
            if (l < r) {
                arr[r] = arr[l];
                //交换之后，r向前移动一位
                r--;
            }
        }
        arr[l] = pivot;
        //以上完成了第一趟的排序
        quickHelper(arr, left, l - 1);
        quickHelper(arr, l + 1, right);
    }
    /**
     * 选择排序：最外层的循环是已经拍好序的元素遍历
     * 内层循环是未拍好序的元素遍历。
     * 时间复杂度为O(n2)
     * 瓶颈在找最小元素，每次都是遍历一边无序部分，优化过程可以考虑用最小堆。
     */
    private void selectSort(int[] nums){
        for (int i=0;i<nums.length;i++){
            //在未排序的数组序列中选择一个最小元素
            int minIndex =i;
            for (int j =i;j<nums.length;j++){
                if (nums[minIndex]>nums[j]){
                    minIndex = j;
                }
            }
            //把最小元素放在已经拍好序元素的最后，因为每次最小元素都是放在最后。所以
            // 新来的最小元素肯定是比旧的已经排好序的大
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    /**
     * 第一版堆排序
     */
    private void heapSort0(int[] nums){
        //参考：https://www.icourse163.org/learn/ZJU-93001?tid=12070062
        // 12#/learn/content?type=detail&id=1212031659&cid=1215166357&replay=true
        //1、建立最小堆，把数组元素一个一个插入。O(n)
        //2、把最小元素从堆中删除，放到临时数组中，然后继续调整最小堆，因为原来的数
        // 组在堆中还有继续调整，所以不能用到原来数组。O(nlogn)
        //3、把临时数组中的元素存入到原数组中返回。O(n)
        //所以总得时间复杂度为O(nlogn)，但是需要利用多一个数组空间，浪费内存
    }
    /**
     * 改进的堆排序，不需要开辟临时数组，
     * 1、建立最大堆。每次把最大的元素放在堆的末尾，
     * 2、把堆的调整坐标向前移动一位HeapAdjust(nums,0,nums.length-2)就可以。
     */
    private void heapSort(int[] nums){
        //1、建立最大堆
        int i;
        //主要这里下标为何是nums.length/2-1开始，可以举几个例子，最末尾的下标
        // 是nums.length-1,它的父亲节点应该是nums.length/2-1，nums.length/2-1就
        // 代表倒数第一个有孩子的节点坐标。
        for (i =nums.length/2-1;i>=0;i--){
            //父亲节点小的下沉，大的上升，最后会形成最大堆
            heapAdjust(nums,i,nums.length-1);
        }
        //2、在堆的末尾建立有序数组
        for (i =nums.length-1;i>0;i--){
            //根节点和有序节点部分交换。
            swap(nums,0,i);
            //这个时候nums.length-1已经算是有序数组部分了。因为上面的swap进行了交换，
            // 所以nums.length-1以及在有序节点中，接下来是num.length-2并入有序，以
            // 此类推。最后因为是i-1，所以循环条件是>0,最后一个无序无需调整，因为其他
            // n-1个部分都有序了，那么最后剩下一个自然有序了。
            heapAdjust(nums,0,i-1);
        }
    }

    /**
     * 堆排序的调整函数，这样首先要弄清楚，堆的元素的父亲和孩子节点的下标对应关系
     *         0                    i
     *        / \                /     \
     *      1    2         2i+1   2i+2
     *     / \   / \
     *   3   4 5   6
     * @param nums 需要调整的堆数组
     * @param peekIndex  堆顶元素的下标，可能是某个子树的根节点
     * @param lastIndex 堆的最后一个元素的下标，为什么要传这个，因为有序部分会
     *                  越来越多，所以无序部分的下标要不断前移。调整的范围也要不断的前移。
     */
    private void heapAdjust(int[] nums,int peekIndex,int lastIndex){
        int parent =0;
        int child = 0;
        //循环退出的条件是左儿子的下标比堆的最后一个元素的下标大，如果大，就说明没有
        // 左儿子，那更没有右儿子了。 parent = child的原因在循环内的第二个if
        for (parent = peekIndex;parent*2+1<=lastIndex;parent=child){
            //先指向左儿子。右儿子即为child+1;
            child = parent*2+1;
            //判断左右儿子的值哪个大,如果右儿子的值大，就child++
            //child不等于lastIndex设置的原因是，如果child=lastIndex，意味着没有右儿子
            //child不等于lastIndex说明有右儿子，才有比较右儿子的必要。
            if (child!=lastIndex&&nums[child]<nums[child+1]){
                child++;
            }
            //开始把父亲节点和孩子节点最大的那个比较,如果父亲节点的值比孩子节点最大的
            // 那个值还要打，说明父亲节点的值最大，不需要调整了。否则大的那个孩子节点的值
            // 和父亲节点的值互相交换,交换完之后，父亲节点的下标移动到孩子节点，就是
            //for循环的最后parent = child ,然后继续向下遍历
            if (nums[parent]>nums[child]){
                break;
            }else {
                swap(nums,parent,child);
            }
        }
    }

    /**
     * 归并排序，和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排
     * 序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间
     * 步骤：
     * 1、 把长度为n的输入序列分成两个长度为n/2的子序列。
     * 2、对这两个子序列分别采用归并排序。
     * 3、将两个排序好的子序列合并成一个最终的排序序列。
     * 由于递归的关系，递最后每个数组长度都为1，才开始归，认真想怎么归会比较容易想明白。
     * 从每个数组长度都为1开始归：比如：原始序列49 38  65  97  76  13 27
     * 1）将原始数组看着7个长度为1的子序列，显然这些子序列都是有序的。
     * 子序列1:49
     * 子序列2:38
     * 子序列3:65
     * 子序列4:97
     * 子序列5:76
     * 子序列6:13
     * 子序列7:27
     * 2）两两归并，形成有序二元组，{38,49},{65,97},{13,76},{27}
     * 以此类推。
     * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
     */
    //这个函数就是为了隐藏内部传递函数，其实可有可无，有了外面只需要传一个nums进
    // 来就可以，对使用者比较友好。
    private void mergeSort(int[] nums){
        int [] temp = new int[nums.length];
        mSort(nums,temp,0,nums.length-1);
    }
    //递归的核心函数
    private void mSort(int [] result,int [] temp,int left,int right){
        if (left>=right){
            return;
        }
        int mid=0;
        //确定一分为二的分界点,mid是左边数组的末尾元素
        mid=left+(right-left)/2;
        //递归解决左边，右边，这里和二分法不一样，要包括全部的元素，所以不能有遗漏，
        // 所以不能写mid-1和mid+1,要覆盖全部，mid和mid+1就无缝覆盖
        mSort(result,temp,left,mid);
        mSort(result,temp,mid+1,right);
        //合并已经排好序的左右两边的数组。
        // 最初始状态，每个小模块数量都是1的时候，可以看做1个元素就是有序的
        mergeS(result,temp,left,mid,right);
    }

    /**
     * 这里假设要合并数组A和数组B，因为两个数组放在同一个数组中的，所以需要下标区分
     * @param result 输入数组
     * @param temp  临时数组，为什么要传入临时数组，而不是在内部开辟new一个，这
     *              里传入临时数组，相当于这个临时数组是全局变量，因为是数组，是引用类
     *              型的，所以可以看做全局变量，因为mergeS函数每合并一次，就需要一个
     *              临时数组，如果放在mergeS内部new数组，那么每合并一次，就需要new
     *              一次，系统开销比new一次全局变量大。所以选择传入参数。
     * @param leftStart 合并数组之前数组A的起始下标
     * @param mid 合并数组之前数组A的结尾下标，mid+1就是合并数组之前数组B的起始下标
     * @param rightEnd 就是合并数组之前数组B的结尾下标
     */
    private void mergeS(int [] result,int[] temp,int  leftStart,int mid,int rightEnd){
        //第一小块数组的终点
        int leftEnd=mid;
        //数组所存的数据的个数，方便后面临时数组从后往前复制数据到result数组中
        int count=rightEnd-leftStart+1;
        //临时数组的起始位置
        int tempStart=leftStart;
        //第二小块的起始位置
        int rightStart = mid+1;
        while (leftStart<=leftEnd&&rightStart<=rightEnd){
            //将第一小块的数组数据复制到临时数组中去，加等号表示稳定的排序算法
            if (result[leftStart]<=result[rightStart]){
                temp[tempStart++] = result[leftStart++];
            }else {
                //将第二小块的数组数据复制到临时数组中去
                temp[tempStart++]=result[rightStart++];
            }
        }
        //当第二小块数组数据复制完时，直接复制剩下的第一小块的数组数据
        while (leftStart<=leftEnd){
            temp[tempStart++]=result[leftStart++];
        }
        while (rightStart<=rightEnd){
            temp[tempStart++]=result[rightStart++];
        }
        //将临时数组的数据复制到result数组中去
        //注意这里用了一个很巧妙的利用数据个数从右边开始将数据复制，因为左边起始位
        // 置leftStart,已经变化了，不能使用
        for (int i =0;i<count;i++,rightEnd--){
            result[rightEnd]=temp[rightEnd];
        }
    }

    /**
     * 归并排序非递归算法，递归算法需要用到logN的空间栈。所以尝试使用非递归算法。
     * 参考：https://www.icourse163.org/learn/ZJU-93001?tid=1207006212#/
     * learn/content?type=detail&id=1212031660&cid=1215166364&replay=true
     * @param result  待排序数组
     * @param temp  临时存放的数组
     * @param length  每个小分段的长度，大小为1,2,4,8...
     */
    private void mergeSort2(int [] nums){
        int [] temp = new int[nums.length];
        int length =1;
        while (length<nums.length){
            //做两遍mergePass,最后一遍的mergePass为了把最后的结果肯定放在nums上，
            // 即使已经排序结束，只需要把temp数组元素复制一遍到nums即可
            mergeSPass(nums,temp,length);
            length*=2;
            mergeSPass(temp,nums,length);
            length*=2;
        }
    }
    private void mergeSPass(int [] result,int[] temp,int  length){
        int resultLength = result.length;
        //从左到右一对一对的执行合并的函数。这里的i每次都跨越两段。那么循环判断条件
        // 为什么是要-2*length，因为mergeS1函数是每次归并看输入下标就知道是成对偶
        // 数个的，但是输入可能是奇数个，可能最后末尾归并并不是完整的个数，所以末尾
        // 的2*length以内的数要特殊处理。
        int i;
        for (i=0;i<=resultLength-2*length;i+=2*length){
            //i是左小段的起始位置，i+length-1是左小段的终止位置，i+2*length-1是右小
            // 段的终止位置。这里的mergeS1 和递归写法中的mergeS还是有一点点不一样
            // 我们最后不把temp排好序的复制到result数组中。
            mergeS1(result,temp,i,i+length-1,i+2*length-1);
        }
        //i+length<resultLength说明最后还剩下两个子列
        if (i+length<resultLength){
            mergeS1(result,temp,i,i+length-1,resultLength-1);
            //说明最后只剩下一个子列，所以不需要合并，直接把最后一个子列放在临时数组
            // 里面即可
        }else{
            for (int j=i;j<resultLength;j++){
                temp[j]=result[j];
            }
        }
    }
    private void mergeS1(int [] result,int[] temp,int  leftStart,int mid,int rightEnd){
        int leftEnd=mid;
        int tempStart=leftStart;
        int rightStart = mid+1;
        while (leftStart<=leftEnd&&rightStart<=rightEnd){
            if (result[leftStart]<result[rightStart]){
                temp[tempStart++] = result[leftStart++];
            }else {
                temp[tempStart++]=result[rightStart++];
            }
        }
        while (leftStart<=leftEnd){
            temp[tempStart++]=result[leftStart++];
        }
        while (rightStart<=rightEnd){
            temp[tempStart++]=result[rightStart++];
        }
    }

    public static void main(String[] args) {
        int [] nums ={-2,3,-5,4,7,9};
        new Sort912LeetCode().sortArray(nums);
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}
