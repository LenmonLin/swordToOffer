/**
 * @author LemonLin
 * @Description :QuickSort
 * @date 2018/3/24-15:33
 */
public class QuickSortByLemonLin {


    public void sort(int arr[],int low,int high){
        int l=low;
        int h=high;
        //这个一定要加，反正l+1成为越界数组
        if (l<h) {
            //这里的temp也可以设置成全局变量
            int temp = arr[l];

            while (l < h) {

                //第一个先选用的是低位的数组为参考对象，则从右边比较起来
                while (l < h && temp <= arr[h]) h--;
                if (l < h) {
                    arr[l] = arr[h];
                    l++;
                }

                while (l < h && arr[l] <= temp) l++;

                if (l < h) {
                    arr[h] = arr[l];
                    h--;
                }
            }

            arr[l] = temp;
            //以上完成了第一趟的排序

            //递归对temp左边的元素进行排序,l<=h一定要加，否则一直循环无法退出
            if (l <= h) sort(arr, low, l - 1);
            //递归对temp右边的元素进行排序,l<=h一定要加，否则一直循环无法退出
            if (l <= h) sort(arr, l + 1, high);
        }
    }




    //测试代码
    public static void main(String[] args) {
        QuickSortByLemonLin quickSortByLemonLin = new QuickSortByLemonLin();

        int array [] = {49,38,65,97,76,13,27,49};
        quickSortByLemonLin.sort(array,0,array.length-1);

        for (int i=0 ;i<array.length;++i){
            System.out.println(array[i]);
        }
    }
}
