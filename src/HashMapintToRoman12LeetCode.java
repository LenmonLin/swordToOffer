/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写
 * 做  XXVII,即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写
 * 做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数
 * 值 4 。同样地，数字 9表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * @author LemonLin
 * @Description :MathintToRoman12LeetCode
 * @date 19.9.11-19:01
 * 思路：题本身不难，怎么写的优雅简洁是个难题。参考：
 * https://leetcode-cn.com/problems/integer-to-roman/solution/
 * tan-xin-suan-fa-by-liweiwei1419/
 * 另外关键点是使用hashmap，或者双数组，然后通过同一个下标变量进行关联。这样会使
 * 讨论情况变少，代码少了很多if else。但是这里其实用双数组代替hashmap更好，因为
 * hashmap是没有顺序的，本题需要从大数到小数排列。所以选用双数组。
 * 另外本题还有一个关键点，不用除法处理数，而是用while循环和减法代替除法，这样程序
 * 细节更容易控制。
 */
public class HashMapintToRoman12LeetCode {
    public String intToRoman(int num) {
        int [] digits={1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String [] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",
                "V", "IV", "I"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<digits.length;i++){
            while (num>=digits[i]){
               num-=digits[i];
               stringBuilder.append(romans[i]);
            }
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {

    }
}
