import java.util.HashMap;
import java.util.Map;

/**
 * @author LemonLin
 * @Description :MoreThanHalfNumber29
 * @date 2018/4/11-14:51
 * 考虑用hashMap记录数组中出现的次数;
 * 空间换时间
 */
public class MoreThanHalfNumber29 {
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
        MoreThanHalfNumber29 moreThanHalfNumber29 = new MoreThanHalfNumber29();
        int result = moreThanHalfNumber29.MoreThanHalfNum_Solution(array);
        System.out.println(result);
    }
}
