/**
 * @author LemonLin
 * @Description :HeapSortByLemonLin
 * @date 2018/4/3-21:26
 *
 * 堆排序算法
 *
 */
public class SortHeapSortByLemonLin {

    public void HeapSort(int [] L){

        //因为根据推排序特点，下标为0的点不能存放需要排序的数据，所以需要把数据0排除一下
        int lengthTranfer=(L.length-1);
        int i =0;
        /*这里容易想不通的是第一个for循环的i其实坐标为lengthTranfer/2,而第二for循环的起始坐标为
        lengthTranfer 这里应该注意第一for循环和第二循环的HeapAdjust的坐标不同，
        第一个for循环是从i调整到lengthTranfer,i变化；第二个for循环是从1到i-1,其中i-1是变化的。
        一个是HeapAdjust函数的第二个参数发生变化，一个是HeapAdjust函数的第三个参数发生变化
        * */

        //第一遍先建立大顶堆，这里第一遍的参数从i到lengthTranfer，然后i是非叶子结点开始，从下
//往上，从右到左,
        for (i=lengthTranfer/2;i>0;i--){
            HeapAdjust(L,i,lengthTranfer);
        }
//        第二遍开始交换根元素，并且重新建立大顶堆,
        for(i=lengthTranfer;i>1;i--){
            swap(L,1,i);
            //这里调整的时候，因为除了根结点之外，其他结点已经有序，所以只调整根节点，
            //始终以结点1为起始调整目标。
              HeapAdjust(L,1,i-1);
        }
    }
/*
*   temp临时记录父亲结点，
*   s记录父亲结点的坐标
*   max是记录的是最大的结点坐标
*   j记录的是孩子结点的坐标
*   本函数调整L[s...max]成为一个大顶堆
* */
    public void HeapAdjust(int [] L,int s,int max){

        int temp=L[s],j;
        for (j=2*s;j<=max;j*=2){
            //先判断左右结点那个大
            if (j<max&&L[j]<L[j+1]){
                j++;
            }
            //如果父结点比孩子结点中大的那个大，就退出循环
            if (temp>=L[j]){
                break;
            }
            //如果父结点比孩子结点中大的那个小，就交换
            L[s]=L[j];
//      因为堆排序还要把结点一直向下比较直到非叶子结点的最后一层，所以，s还是需要变化的
            s=j;
        }
        //因为可能执行到break那一步，那么j和s就不一样得，返回到s程序才能运行
        L[s]=temp;
//        L[j]=temp;
    }
//    交换数据
    public void swap(int [] L,int l,int k){
        int temp=L[l];
        L[l]=L[k];
        L[k]=temp;
    }
    public static void main(String[] args) {

        /*
        * 输入格式，第一个数字是数组的长度，接下来输入的数组第一个数字是废数据，之后的才有用
        *比如：
        * 10
        * 0 90 70 80 60 10 40 50 30 20
        * */
//        Scanner scanner = new Scanner(System.in);
//        int numberOfArray= scanner.nextInt();
//        int [] arra=new int[numberOfArray];
//
//        for (int i=0;i<arra.length;++i){
//            arra[i]=scanner.nextInt();
//        }

        int [] arra={0,90,70,80,60,10,40,50,30,20};
        SortHeapSortByLemonLin heapSortByLemonLin = new SortHeapSortByLemonLin();
        heapSortByLemonLin.HeapSort(arra);
        for (int i =1 ;i<arra.length;i++){
            System.out.println(arra[i]);
        }

    }
}
