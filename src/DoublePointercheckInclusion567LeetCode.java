/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * @author LemonLin
 * @Description :DoublePointercheckInclusion567LeetCode
 * @date 20.2.3-16:00
 * 思路：方法1：求s1的全排列，然后一个一个和s2比较。（据说会超时）
 * 方法2：遍历s2,先寻找到和s1的第一个字符相同的位置mark，然后s1的字符坐标下移一个，
 * 在s2的mark位置左边或者右边比较是否等于s1的字符坐标的下一个。如果等于。s1的字符
 * 坐标继续下移一个，和刚刚s2的mark位置左边相同就继续左移，右边相同就继续右移。
 * 直到s1遍历完毕。
 * 方法2不行，因为方法2只能验证s1是不是在s2中，不能验证s1的全排列是否在s2中。尴尬。
 * 参考了题解：
 * https://leetcode-cn.com/problems/permutation-in-string/solution/
 * zi-fu-chuan-de-pai-lie-by-leetcode/
 * 用一个26大小的数组记录s1中每个字符出现的频率。对于s2,设置一个大小和s1长度一样
 * 的滑动窗口s2Map，计算滑动窗口的字符出现的频率是否和s1中出现的字符频率一样即可。
 * 为什么不用hashMap记录s1字符出现的个数，是因为官方题解用hashMap超时了。
 * 怎么计算滑动窗口的字符出现的频率是否和s1中出现的字符频率一样；
 * 遍历两个s1Map数组和s2Map数组对比里面的数是不是都相等。
 * 这里的说的s1排列就是要注意，就是字符长度和s1一样但是每个字符出厂顺序可以不一样，
 * 比如s1="abc"， 那么acb,abc,bac,bca,cab,cba都是算s1的排列，所以这个总字符长度和
 * 每个字符出现的频率都要对应上才能称为相同的排列。两个元素缺一不可。这个每个字符
 * 出现的频率的对应，可以用遍历滑动窗口和s1数组来对比。这个字符长度可以通过固定s2Map
 * 滑动窗口的长度来控制。比如s1字符长度为3，那么s2Map的滑动窗口长度也必须固定为3；
 * 滑动窗口的长度本题怎么控制。本题控制的很巧妙，把s2Map的起始遍历坐标设置为s1.length()，
 * 那么滑动窗口就形成了(0,j) j=s1.length(),然后每次j++的时候，滑动窗口的左侧的字符频率
 * 都要--，所以这里可以看出，滑动窗口的右侧是j来控制。这里要注意s2Map不是滑动窗口，
 * 滑动窗口是0，j，这个坐标而已，窗口是坐标！！不要搞混了，这里很容易搞混。为什么
 * 这里的滑动窗口这么奇怪，一般不都有两个坐标，窗口左坐标，窗口右坐标，本题为啥只
 * 有一个窗口右坐标，原因是这个滑动窗口的大小是固定住的，不能变的，所以只需要一个
 * 坐标，加上长度控制，就可以。
 */
public class DoublePointercheckInclusion567LeetCode {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length()>s2.length())return false;
        int[] s1Map = new int[26];
        int[] s2Map = new int[26];
        //先把s1的所有字符频率都保存起来，这个时候s2Map也进行遍历，为滑动窗口的长
        // 度做准备
        for (int i=0;i<s1.length();i++){
            s1Map[s1.charAt(i)-'a']++;
            s2Map[s2.charAt(i)-'a']++;
        }
        //上一个循环的遍历，已经为滑动窗口提供了长度，这里只要把起始位置j设置为s1.length()
        // 即可，这样滑动窗口有了长度加上尾坐标。
        for (int j=s1.length();j<s2.length();j++){
            if (isMatch(s1Map,s2Map)){
                return true;
            }
            s2Map[s2.charAt(j)-'a']++;
            s2Map[s2.charAt(j-s1.length())-'a']--;
        }
        //最后循环跳出还要在比较一下，到达j==s2.length()-1,这个还没比较。
        return isMatch(s1Map,s2Map);
    }
    //遍历每个字符出现的次数是否相等。
    private  boolean isMatch(int[] s1Map,int [] s2Map){
        for (int i=0;i<26;i++){
            if (s1Map[i]!=s2Map[i]){
                return false;
            }
        }
        return true;
    }
    //自己写的方法2，有问题，没考虑周全。留作纪念
    public boolean checkInclusion2(String s1, String s2) {
        int j=0;
        int s2Mark=0;
        boolean flag =false;
        for (int i=0;i<s2.length();i++){
            if (s2.charAt(i)==s1.charAt(0)){
                flag=true;
                if (s1.length()==1){
                    return true;
                }
                s2Mark=i;
            }
            if (flag==true){
                if (s2Mark+1<=s2.length()-1&&s2.charAt(s2Mark+1)==s1.charAt(1)){
                        i=s2Mark+1;
                        j=1;
                        while (s1.length()-1-j>=0&&i<=s2.length()-1){
                            if (s2.charAt(i)==s1.charAt(j)){
                                i++;
                                j++;
                                if (j>s1.length()-1&&i<=s2.length()){
                                    return true;
                                }
                            }
                        }
                    }
                if (s2Mark-1>=0&&s2.charAt(s2Mark-1)==s1.charAt(1)){
                       i=s2Mark-1;
                       j=1;
                       while (s1.length()-1-j>=0&&i>=0){
                           if (s2.charAt(i)==s1.charAt(j)){
                               i--;
                               j++;
                               if (j>s1.length()-1&&i>=-1){
                                   return true;
                               }
                           }
                       }
                   }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1="ab";
        String s2="eidboaoo";
        System.out.println(new DoublePointercheckInclusion567LeetCode().
                checkInclusion(s1, s2));
    }
}
