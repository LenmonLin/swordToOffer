/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * 例如，
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 * 实例：输入52
 * 输出："AZ"
 * @author LemonLin
 * @Description :MathconvertToTitle168LeetCode
 * @date 19.9.12-13:53
 * 思路：进制转换问题：当1到26字母时是一一对应的，53时，这里面有两个二十六，
 * 所以选B，减去两个26之后，剩1，所以选A，所以是BA
 * 本题看似简单，其实有一个小点自己想很难想明白，看了题解才有顿悟的感觉。
 *参考：https://leetcode-cn.com/problems/excel-sheet-column-title/solution/168-by-ikaruga/
 * 这道题的麻烦在于一个数对26取模范围在[0,25]，两边取闭区间，但是题目给的对应关
 * 系是1-26的，所以每次做取模运算之后-1。
 * 同时应该是从右边向左边置换，然后再翻转。不应该从左边向右边转换，会产生错误。
 */
public class MathconvertToTitle168LeetCode {

    public String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n!=0){
            n-=1;
            stringBuilder.append((char) ('A'+n%26));
            n = n/26;
        }
        return stringBuilder.reverse().toString();
    }
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
        int n = 52;
        System.out.println(new MathconvertToTitle168LeetCode().convertToTitle(n));
    }
}
