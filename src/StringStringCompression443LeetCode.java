/**
 * 给定一组字符，使用原地算法将其压缩。压缩后的长度必须始终小于或等于原数组长度。
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。在完成原地修改输入数组后，
 * 返回数组的新长度。
 * 进阶：
 * 你能否仅使用O(1) 空间解决问题？
 * 思路：注意题目说的是原地算法：即将压缩结果存在原chars[]数组中.
 * 使用双指针即可求解此题.这里我们称由相同字符组成的字符串为相同字符序列.
 * 指针newCharEnd指示已压缩的结果的末尾,sameStart指未压缩字符串的开头.
 * 遇到相同的字符,指针sameStart便向后滑动,直到遇到不同字符.指针sameStart滑动的距离即为相
 * 同字符序列的长度.
 * 记忆点：
 * 1、这里注意newCharEnd是指已压缩的，sameStart是指未压缩的，所以双指针是在不同的字符串上
 * 面的指针，别想成同一个字符串上面的双指针，同一个字符串上面的双指针处理不了末尾字符下标
 * 的越界问题，应该用不同字符串上面的指针对比才能够处理末尾字符下标的越界问题。
 * 2、像12数字转换为"12"字符串这种解法也要记住。
 * 3、一个是处理只有一个字符不记录数字，一个以上字符才记录数字的方法。
 * @author LemonLin
 * @Description :StringStringCompression
 * @date 19.6.10-22:59
 * Given an array of characters, compress it in-place.
 * The length after compression must always be smaller than or equal to the original array.
 * Every element of the array should be a character (not int) of length 1.
 * After you are done modifying the input array in-place, return the new length of the array.
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * Example 1:
 * Input:
 * ["a","a","b","b","c","c","c"]
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * Example 2:
 * Input:
 * ["a"]
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * Explanation:
 * Nothing is replaced.
 * Example 3:
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced
 * by "b12".Notice each digit has it's own entry in the array.
 * Note:
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */
public class StringStringCompression443LeetCode {
    public int compress(char[] chars) {
        int sameStart = 0;
        int newCharEnd =0;
        while (sameStart<chars.length){
            chars[newCharEnd] =chars[sameStart];
            newCharEnd++;
            //temp暂时记录重复字符的开始下标
            int temp = sameStart;
            while (sameStart<chars.length&&chars[sameStart]==chars[newCharEnd-1]){
                sameStart++;
            }
            //这里因为如果是单个字符，只需要保存字符本身，不需要压缩保存数据，
            //所以sameStart-temp>1表示至少是两个字符，进入if把数字转换为字符数组存储
            if (sameStart-temp>1){
                char[] change = String.valueOf(sameStart-temp).toCharArray();
                for (int k=0;k<change.length;k++){
                    chars[newCharEnd++]=change[k];
                }
            }
        }
        return newCharEnd;
    }

    public static void main(String[] args) {
        char[] cha= new char[]{'a','b','b','b'};
        System.out.println(new StringStringCompression443LeetCode().compress(cha));
    }
}
