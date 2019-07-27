/**
 *给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0
 * 和所有1都是组合在一起的。重复出现的子串要计算它们出现的次数。
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same
 * number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 * Example 1:
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 * "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 * Input: "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of
 * consecutive 1's and 0's.
 * Note:
 * s.length will be between 1 and 50,000.
 * s will only consist of "0" or "1" characters.
 * @author LemonLin
 * @Description :StringCountBinarySubstrings
 * @date 19.6.13-23:04
 * 思路：https://github.com/grandyang/leetcode/issues/696
 * 这道题给了我们一个二进制字符串，然后我们统计具有相同0和1的个数，且0和1各自都群组在一起(即0和1
 * 不能交替出现)的子字符串的个数，题目中的两个例子也很能说明问题。那么我们来分析题目中的第一个例子
 * 00110011，符合要求的子字符串要求0和1同时出现，那么当第一个1出现的时候，前面由于前面有两个0，
 * 所以肯定能组成01，再遇到下一个1时，此时1有2个，0有2个，能组成0011，下一个遇到0时，此时0的个
 * 数重置为1，而1的个数有两个，所以一定有10，同理，下一个还为0，就会有1100存在，之后的也是这样
 * 分析。这里需要用到三个变量，记录当前数字的数量cur,记录与当前数字不同的前面数字pre,这里要注意
 * 是与当前数字不同的数字，记录0和1数量相同情况下组成的群组的个数result。其中pre初始化为0，cur
 * 初始化为1，然后从第二个数字开始遍历，如果当前数字和前面的数字相同，则cur自增1，否则pre赋值为
 * cur，cur重置1。然后判断如果pre大于等于cur，res自增1
 */
public class StringCountBinarySubstrings696Leetcode {
    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int pre=0,cur=1,result=0;
        for (int i=1;i<chars.length;i++){
            if (chars[i-1]==chars[i]){
                cur++;
            }else {
                pre = cur;
                cur=1;
            }
            if (pre>=cur)result++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "10101";
        System.out.println(new StringCountBinarySubstrings696Leetcode().countBinarySubstrings(s));
    }
}
