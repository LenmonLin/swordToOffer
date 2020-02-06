import java.util.ArrayList;
/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOES
 * IIGEDHN"。请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *思路：第一反应是构造一个二维数组，它有 x 和 y 两个方向，然后按照题目要求的顺序
 * 储存元素。但事实上横方向是不用考虑的，只要模仿竖方向的变换规律向每一行压入元
 * 素即可，这样也方便通过直接组合每一行形成结果。
 * @author LemonLin
 * @Description :StringZigZagConversion6LeetCode
 * @date 19.7.12-23:42
 * 思路：参考：https://leetcode-cn.com/problems/zigzag-conversion/solution/
 * zzi-xing-bian-huan-by-jyd/
 * 主要想法是用numRows个StringBuilder存储每一行，顺序遍历字符串，把字符串存入
 * 对应行，怎么找对应行，这里用一个很巧妙的思路，设置flag=-1。当i==nuRows-1
 * 或者i==0时，flag=-flag，i+=flag ；
 * 比如LEET，numRows等于3，遍历LEET：
 * i=0,L存入第零行，
 * i+flag,E存入第一行
 * i+flag,E存入第二行，
 * 当i==nuRows-1或者i==0时，就变成i-1,T就存入第一行了，以此类推。
 * 有个小问题，怎么设置numRows个StringBuilder：
 * 用ArrayList存储StringBuilder，不同下标代表不同StringBuilder。
 * bug1:
 * ""
 * 1
 * java.lang.StringIndexOutOfBoundsException: String index out of range: 0
 */
public class StringZigZagConversion6LeetCode {
    public String convert(String s, int numRows) {
        //解决bug1
        if(numRows < 2) return s;
        ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            stringBuilders.add(new StringBuilder());
        }
        int flag =-1;
        int i=0;
        stringBuilders.get(0).append(s.charAt(0));
        for (int j=1;j<s.length();j++){
            if (i==0 || i==numRows-1){
                flag=-flag;
            }
            i+=flag;
            stringBuilders.get(i).append(s.charAt(j));
        }
        StringBuilder result = new StringBuilder();
        for (int j=0;j<numRows;j++){
            result.append(stringBuilders.get(j));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s ="LEETCODEISHIRING";
        int numRows = 3;
        System.out.println(new StringZigZagConversion6LeetCode().
                convert(s, numRows));
    }
}
