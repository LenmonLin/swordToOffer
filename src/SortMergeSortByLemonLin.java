/**
 * @author LemonLin
 * @Description :MergeSortByLemonLin
 * @date 2018/4/7-16:49
 *
 * 归并排序算法
 * 递归方法：
 *  算法思想：分而治之，先将整块数组不断的一分为二，然后先把小块的数组排好序，之后在合并的进行排序
 *  过程中需要用到两个数组，一个输入的数组存放原来的数据和排好序的数据，一个临时数组，用来临时存放
 *  排好序的各个阶段，最后把全部排好序的内容复制到输入的数组中；
 *  同时需要三个坐标记录，start,记录待排序的数组的开始位置，End记录待排序数组的结束位置，mid，记录
 *  一分为二的坐标。
 *
 *  时间复杂度是O(nlogn)，利用了分而治之的办法，其中分O(logn),治O(n)
 */
public class SortMergeSortByLemonLin {

    //归并排序函数的统一出口。
    public void MergeSort(int [] arra){
        //这里建立临时数组的原因，而不在Msort函数中新建的原因是为了节省开销，只新建一次，而不用
        //调用Msort一次就新建一次
        int [] temp = new int[arra.length];
        MSort(arra,temp,0,arra.length-1);
    }

    //递归的核心函数
    public void MSort(int [] result,int [] temp,int left,int right){
        int mid=0;

        if (left<right){
            //确定一分为二的分界点,mid是左边数组的末尾元素
            mid=(left+right)/2;
            //递归解决左边
            MSort(result,temp,left,mid);
            //递归解决右边的小块数组
            MSort(result,temp,mid+1,right);
            //合并已经排好序的左右两边的数组，这里之所以去mid+1,是为了temp[tempLeft++]=result[mid++];
            //方便，否则取mid,result中的mid不能直接++,坐标不对
            MergeS(result,temp,left,mid+1,right);
        }
    }

    //这是合并的过程，将两小块排好序的数组，合并到一个大的临时数组中，并把这个临时排好序的数组的内容复制
    //到输入数组中
    // 将有序的result[left]~A[mid-1]和result[mid]~result[right]归并成一个有序序列 */
    //这里mid可以看成第二小块数组的起始位置
    public void MergeS(int [] result,int[] temp,int  left,int mid,int right){

        //第一小块数组的终点
        int leftEnd=mid-1;
        //数组所存的数据的个数，方便后面临时数组从后往前复制数据到result数组中
        int count=right-left+1;

        //临时数组的起始位置
        int tempLeft=left;

        while (left<=leftEnd&&mid<=right){
            //将第一小块的数组数据复制到临时数组中去
            if (result[left]<result[mid]){
                temp[tempLeft++] = result[left++];
            }else {
                //将第二小块的数组数据复制到临时数组中去
                temp[tempLeft++]=result[mid++];
            }
        }

        //当第二小块数组数据复制完时，直接复制剩下的第一小块的数组数据
        while (left<=leftEnd){
            temp[tempLeft++]=result[left++];
        }

        while (mid<=right){
            temp[tempLeft++]=result[mid++];
        }

        //将临时数组的数据复制到result数组中去
        //注意这里用了一个很巧妙的利用数据个数从右边开始将数据复制，因为左边起始位置left,已经变化了，
        //不能使用
        for (int i =0;i<count;i++,right--){
            result[right]=temp[right];
        }
    }

    public static void main(String[] args) {
        SortMergeSortByLemonLin sortMergeSortByLemonLin = new SortMergeSortByLemonLin();
        int [] test = {2,4,3};
        sortMergeSortByLemonLin.MergeSort(test);
        for (int i= 0;i<test.length;i++){
            System.out.println(test[i]);
        }

    }
}
