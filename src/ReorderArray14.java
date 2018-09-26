/**
 *
 * 考虑可拓展性,应该抽离出判断条件，形成函数
 *
 * 基本思路是快速排序算法
 * @author LemonLin
 * @Description :ReorderArray
 * @date 2018/2/28-21:09
 */
public class ReorderArray14 {


    public void reOrderArray(int [] array) {

        if (array == null || array.length == 0){
            return;
        }

        int pBegin = 0;
        int pEnd = array.length-1;

        while (pBegin<pEnd){
            while (pBegin<pEnd && !isEven(array[pBegin])){
                pBegin++;
            }
            while (pBegin<pEnd&& isEven(array[pEnd])){
                pEnd--;
            }
            if (pBegin < pEnd){
                int temp=array[pBegin];
                array[pBegin]=array[pEnd];
                array[pEnd]=temp;
            }
        }

    }

    //判断是否是偶数，是返回true
    public boolean isEven(int x){
        if (x%2 == 0 )
            return true;
        else
            return false;
    }
    //测试代码
    public static void main(String[] args) {
        int [] array={1,2,3,4,5,6,7};
        ReorderArray14 reorderArray14 = new ReorderArray14();
        reorderArray14.reOrderArray(array);
        for (int i=0;i<array.length;++i){
            System.out.println(array[i]);
        }
    }
}
