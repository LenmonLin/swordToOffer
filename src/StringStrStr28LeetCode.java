/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle
 * 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的
 * indexOf() 定义相符。
 * 解题思路：
 *  使用两层循环暴力算法遍历，时间复制度为O(m*n)。固定needle[0]与每个haystack进行比较，如果
 *  等，needle和haystack都下标加一，继续比较，直到比较到needle长度结束，返回下标。
 * @author LemonLin
 * @Description :StringImplementstrStr
 * @date 2019/5/20-20:41
 *Implement strStr().
 *    Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part
 *    of haystack.
 */
public class StringStrStr28LeetCode {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty())return 0;
        int i=0;
        //考虑到haystack = "aab", needle = "abbab"
        if(needle.length()>haystack.length())return -1;
        for (int j=0;j<haystack.length();j++){
            //考虑到haystack = "mississippi", needle = "issipi";
            if (needle.length()>haystack.length()-j)return -1;
            if (needle.charAt(i)==haystack.charAt(j)){
                int index=j;
                while (needle.charAt(i) ==haystack.charAt(j)){
                    if (needle.length()-1==i) return index;
                    i++;
                    j++;
                }
                //考虑输入：haystack = "mississippi", needle = "issip";
                j=index;
                i=0;
            }
        }
        return -1;
    }

    //简化版，主体有三个指针，i指针是为了最后返回结果索引开头。j指针其实就是指向haystack，
    //然后再需要一个指针指向needle
    public int strStr2(final String haystack, final String needle) {
        if (needle.isEmpty()) return 0;

        //如果存在是子串的可能，那么长度只可能在这两者之差中一定会遇到相同的字符，
        // 如果在haystack上超过了两者之差还没碰到可能重复的字符，那么之后及时碰到
        // 了，在haystack上重复的部分也不够needle长
        int N = haystack.length() - needle.length() + 1;
        for (int i = 0; i < N; i++) {
            int j = i;
            int k = 0;
            while (j < haystack.length() && k < needle.length() &&
                    haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if (k == needle.length()) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "mississippi", needle = "issipi";
        StringStrStr28LeetCode stringImplementstrStr = new StringStrStr28LeetCode();
        int result = stringImplementstrStr.strStr(haystack, needle);
        System.out.println(result);
    }
}
