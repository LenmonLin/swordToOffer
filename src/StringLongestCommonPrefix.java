/**
 * @author LemonLin
 * @Description :StringLongestCommonPrefix
 * @date 19.6.10-16:06
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * All given inputs are in lowercase letters a-z.
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 思路：基本思路是用二维数组进行比较，把输入想象成a=["flower",
 *                                                                                 "flow",
 *                                                                                 "flight"]
 * 然后遍历数组中的每个元素，同时遍历每个元素下标进行比较。比如a[0].[0]=a[1][0]=a[2][0]
 */
public class StringLongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0)return "";
        //计算字符串数组中最小的字符串长度
        int minLength =strs[0].length();
        for (int k=0;k<strs.length;k++){
            if (minLength>strs[k].length()){
                minLength = strs[k].length();
            }
        }
        //j表示数中的每个字符串元素，i表示字符串元素的每个字符下标
        int i=0;
        boolean flag = false;
        for (;i<minLength;i++){
            for (int j =0;j<strs.length-1;j++){
                if (strs[j].charAt(i)!=strs[j+1].charAt(i)){
                    flag = true;
                    break;
                }
            }
            if (flag == true)break;
        }
        if (i==0)return "";
        return strs[0].substring(0,i);
    }

    public static void main(String[] args) {
        String[] test =new String[]{""};
        System.out.println(new StringLongestCommonPrefix().longestCommonPrefix(test));
    }
}
