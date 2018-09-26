import java.util.HashMap;

/**
 * @author LemonLin
 * @Description :GetNumberOfK38
 * @date 2018/5/8-15:58
 *
 * 基本思路是使用hashmap中的key来记录array中的数，使用value来记录出现的次数。
 * 不能用数组的原因是，如果数字很大，array={300},那么要申请一个300大小的数组，非常的浪费。
 *
 * hashmap中get取出的元素类型是object，需要强制转换为int型;
 */
public class GetNumberOfK38 {
    public int GetNumberOfK(int [] array , int k) {
        if (array==null){
            return 0;
        }
        HashMap hashMap = new HashMap();

        //这里flag的设置，是为了判断当k值不等于key时，需要返回0
        boolean flag = false;
        //先初始化hashMap
        for (int i=0;i<array.length;++i){
            if (array[i]==k) {
                flag = true;
            }
            hashMap.put(array[i],0);
        }
    //开始寻找，这里要注意put()中value是使用++i1,而不是i1++，如果用了会出现bug。原因是先放入i1,才加1.
        for (int i=0;i<array.length;++i){
            int i1 = (int)hashMap.get(array[i]);
            hashMap.put(array[i],++i1);
        }

        if(flag == false){
            return 0;
        }
        return (int)hashMap.get(k);
    }

    public static void main(String[] args) {
        int[]array = {3};
        GetNumberOfK38 getNumberOfK38 = new GetNumberOfK38();
        int iNumber = getNumberOfK38.GetNumberOfK(array, 3);
        System.out.println(iNumber);
    }
}
