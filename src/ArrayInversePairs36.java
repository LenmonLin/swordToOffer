/**
 * @author LemonLin
 * @Description :ArrayInversePairs36
 * @date 2019/3/11-17:10
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这
 * 个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 输入描述:题目保证输入的数组中没有的相同的数字
 * 数据范围：
     * 	对于%50的数据,size<=10^4
     * 	对于%75的数据,size<=10^5
     * 	对于%100的数据,size<=2*10^5
    测试用例输出结果比较大，对每次返回的count mod(1000000007)求余
 *
 * 	解题思路：
 *      如果是得到一个数字，一个一个从前往后比较的话，需要的时间复杂度是O(n^2),利用归并排序可以把时间
 *      复杂度降到O(nlogn);
 *      利用归并排序的思想：先把数组分割成子数组，先统计出子数组内部的逆序对的数目，然后再统计出两个相邻
 *      子数组之间的逆序对的数目。在统计逆序对的过程中，还需要对数组进行排序。
 *
 */
public class ArrayInversePairs36 {
    public int InversePairs(int [] array) {

        if (array==null||array.length==0){
            return 0;
        }
        //记录逆序对
        int count =0;

        int [] temp =new int[array.length];
        count = Msort(array, temp, 0, array.length - 1)%1000000007;

        return count;
    }

    //归并排序主程序
    public int Msort(int []result,int [] temp,int left,int right){

        if (left == right){
            return 0;
        }

        int countLeft=0;
        int countRight =0;
        int count =0;
        //mid为第一个数组的末尾元素
        int mid = (left+right)/2;
        if (left<right){
            countLeft =Msort(result,temp,left,mid)%1000000007;
            countRight =Msort(result,temp,mid+1,right)%1000000007;
            count =Merge(result,temp,left,mid+1,right)%1000000007;
        }
        return count+countLeft+countRight;
    }

    //归并排序分而治之的合并的过程,与归并排序不同的是，复制从右边开始复制
    public int Merge(int []result,int[] temp,int left,int mid,int right){
        //传入的mid是mid+1,即右边数组的第一个元素的坐标
        int count =0;
        int leftEnd = mid-1;

        int tempRight = right;
        int countResult = right-left+1;

        while (left<=leftEnd&&mid<=right){
            //这里result[leftEnd]不能写mid-1,因为mid是不变的，leftEnd是变化的
            if (result[right]<result[leftEnd]){
                //因为左边最后一个大于右边最后一个就大于右边的全部
                count +=(right-mid+1)%1000000007;
                temp[tempRight--]= result[leftEnd--];
            }else {

                temp[tempRight--]= result[right--];
            }
        }

        while (left<=leftEnd){
            temp[tempRight--]=result[leftEnd--];
        }
        while (mid<=right){
            temp[tempRight--]=result[right--];

        }

        for (int i=0;i<countResult;i++,left++){
            result[left]=temp[left];
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayInversePairs36 arrayInversePairs36 = new ArrayInversePairs36();
        int []test ={7,5,6,4};
        int result = arrayInversePairs36.InversePairs(test);
        System.out.println(result);
    }
}
