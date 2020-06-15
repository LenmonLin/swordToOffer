import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *图片：https://leetcode-cn.com/problems/binary-watch/
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 示例：
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 提示：
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 * @author LemonLin
 * @Description :ArrayreadBinaryWatch401LeetCode
 * @date 20.5.27-20:35
 * 思路：参考：https://leetcode-cn.com/problems/binary-watch/solution/cjian-jian-dan-dan-de-ji-xing-dai-ma-jie-jue-wen-t/
 * 二重循环遍历所有可能的时间表示时转换为二进制情况下1的个数，如果1的个数等于给定
 * 传入的num就添加到结果当中去。第一层循环是时循环，0到11，第二层循环是分钟循环，
 * 0到59.
 */
public class ArrayreadBinaryWatch401LeetCode {
    public List<String> readBinaryWatch(int num) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i <=11 ; i++) {
            for (int j = 0; j <=59 ; j++) {
                String temp = null;
                if (count(i)+count(j)==num){
                    //分钟的0要特殊处理
                    if (j<=9){
                        temp = "0"+j;
                    }else {
                        temp = Integer.toString(j);
                    }
                    result.add(Integer.toString(i)+":"+temp);
                }
            }
        }
        return result;
    }

    //传入一个十进制的数，求这个这个数被换算成二进制数的时候，所包含的1有多少个
    private int count(int x){
        int result =0;
        while (x!=0){
            //求x的最右边一位是不是1
            result+= x &1;
            //x 右移一位
            x=x>>1;
        }
        return result;
    }
}
