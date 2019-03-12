import java.util.HashMap;
import java.util.Map;

/**
 * @author LemonLin
 * @Description :MoreThanHalfNumber29
 * @date 2018/4/11-14:51
 *
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,
 * 2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 解题思路：
 * 考虑用hashMap记录数组中出现的次数;
 * 空间换时间
 */
public class ArrayMoreThanHalfNumber29 {
    public int MoreThanHalfNum_Solution(int [] array){
        Map<Integer,Integer> map = new HashMap<>();
        //初始化map中的value值为0
        int result = 0;
        for (int i= 0;i<array.length;++i){

            map.put(array[i],0);
        }
        //把key出现的次数进行累加
        for (int j=0;j<array.length;++j){
            map.put(array[j],map.get(array[j])+1);
            if(map.get(array[j])>array.length/2){
                result = array[j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] array={1,2,3,2,2,2,5,4,2};
        ArrayMoreThanHalfNumber29 arrayMoreThanHalfNumber29 = new ArrayMoreThanHalfNumber29();
        int result = arrayMoreThanHalfNumber29.MoreThanHalfNum_Solution(array);
        System.out.println(result);
    }
}
