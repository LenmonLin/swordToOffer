/**
 *给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，
 * 如果不存在则返回 -1。
 * 举个例子，A = "abcd"，B = "cdabcdab"。
 * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为
 * "abcdabcd"，B并不是其子串。
 *解题思路：
 * 判断字符串A中是否有子串B可以用Java中的字符串的indexOf方法，如果返回的不是-1，就有包含。
 * B要能成为A的字符串，那么A的长度肯定要大于等于B，所以当A的长度小于B的时候，我们可以先进
 * 行重复A，直到A的长度大于等于B，并且累计次数cnt。那么此时我们用indexOf来找，看B是否存在A
 * 中，如果存在直接返回cnt。如果不存在，我们再加上一个A，再来找，这样可以处理这种情况A="abc",
 * B="cab"，如果此时还找不到，说明无法匹配，返回-1.当给定A长度大于B时，有两种情况需要判断，
 * 一种是B非子串，返回-1，一种是A要复制一次，返回2;比如"aaaaaaaaaaaaaaaaaaaaaab"    "ba"
 * @author LemonLin
 * @Description :StringrepeatedStringMatch
 * @date 2019/6/9-11:26
 * Given two strings A and B, find the minimum number of times A has to be repeated such
 * that B is a
 * substring of it. If no such solution, return -1.
 * For example, with A = "abcd" and B = "cdabcdab".
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and
 * B is not a
 * substring of A repeated two times ("abcdabcd").
 * Note:
 * The length of A and B will be between 1 and 10000.
 */
public class StringrepeatedStringMatch686LeetCode {
    public int repeatedStringMatch(String A, String B) {
        int cnt =1;
        int Alength =A.length();
        int Blength = B.length();
        String changeA = A;
        if (Alength>=Blength){
            if (A.indexOf(B)!=-1){
                return 1;
            }else {
                //"aaaaaaaaaaaaaaaaaaaaaab"    "ba"
                changeA +=A;
                if (changeA.indexOf(B)!=-1){
                    return 2;
                }
                return -1;
            }
        }
        while (Alength<Blength){
            changeA +=A;
            Alength = changeA.length();
            cnt++;
        }
        if (changeA.indexOf(B) ==-1){
            changeA +=A;
            cnt++;
            if (changeA.indexOf(B)==-1){
                return -1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String test1 = "aaaaaaaaaaaaaaaaaaaaaab";
        String test2 = "ba";
        int result = new StringrepeatedStringMatch686LeetCode().repeatedStringMatch(test1,test2);
        System.out.println(result);
    }
}
