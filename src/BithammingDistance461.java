/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * @author LemonLin
 * @Description :BithammingDistance461
 * @date 20.5.29-21:14
 * 思路：先对两个数进行异或操作，然后看看异或结果中有几个1，就是结果，怎么查看有
 * 几个1，可以判断末尾是不是奇数，然后再右移，如此循环。
 */
public class BithammingDistance461 {
    public int hammingDistance(int x, int y) {
        int temp = x^y;
        int result =0;
        while (temp!=0){
            if (temp %2==1){
                result+=1;
            }
            //右移一位。
            temp = temp>>1;
        }
        return result;
    }
}
