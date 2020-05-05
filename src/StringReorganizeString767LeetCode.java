import java.util.HashMap;
/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:S 只包含小写字母并且长度在[1, 500]区间内。
 *思路：观察发现在一个元素个数为3另一个元素个数为1时，就是这样ABAA，显然无法使相邻的字符不同；
 * 在一个元素个数为5，一个元素个数为2，一个元素个数为1的情况为ABABACAA，显然也不行，由此可得
 * 规律，如果最多的元素个数>=其他元素个数的总和加2，则无法构成题目要求，返回空字符串；
 * 所以考虑用hashmap统计每个字符出现的次数，再比较其中的value是否满足条件。
 * 生成字符串的方法有很多，本文采用的是写一个数量最多的元素，然后再写一个与之前元素不同的数量最多
 * 的元素，这样循环生成。
 * 具体解法：先遍历一遍s,设置一个maxCount记录次数最多的那个字符，然后用总次数减去这个maxCount
 * 来获取其他元素个数总和，判断是否能构成题目要求
 * 出现问题，生成字符串的时候，
 * @author LemonLin
 * @Description :StringReorganizeString767LeetCode
 * @date 19.7.12-23:03
 * 思路：自己的解法有bug，抛弃。
 * 参考：https://leetcode-cn.com/problems/reorganize-string/solution/zui-you-jie-fa-tong-guo-by-18520397110-2/
 * 当某个字符的出现次数大于字符总数的一半时，必然无法进行重构，返回空字符串。先
 * 将字符串中的每个字符按照26个字母的排序进行放置形成数组，出现次数作为数组的元素。
 * 遍历数组，将字符串中的字符按照奇数偶数放在新建的char数组中。将相同的字符个数小
 * 于字符串长度的一半的字符放在奇数下标位置，否则放在偶数下标位置。
 * 注意这里需要判断奇数位置是否大于字符串长度。
 * bug1:
 * 输入:
 * "vvvlo"
 * 输出
 * "lvovv"
 * 预期结果
 * "vlvov"
 * bug2:
* 输入:
* "aab"
* 输出
* "aab"
* 预期结果
* "aba"
 */
public class StringReorganizeString767LeetCode {

    public String reorganizeString2(String S) {
        if (S.length()==0||S.length()==1)return S;
        int[] count = new int[26];
        int countMax =0;
        //第一个循环统计字符串中字符出现的次数
        for (int i =0;i<S.length();i++){
            char temp = S.charAt(i);
            count[temp-'a']++;
            if (count[temp-'a']>countMax){
                countMax = count[temp-'a'];
            }
            //用Math.ceil(S.length()/2.0)的条件是类似aab要排除掉，所以设置平均数向右靠
            if (countMax>Math.ceil(S.length()/2.0)){
                return "";
            }
        }
        char [] result = new char[S.length()];
        int even = 0,odd = 1;
         for (int i=0;i<26;i++){
             //首先要放入奇数位置，不能先考虑偶数位置，因为偶数位置有可能是提供给频率占
             // 多数情况的字符，这里注意是可能，因为有可能情况是字符串中没有频率超过字
             // 符串长度一半的字符，这个时候如果先考虑偶数位，偶数位就被跳过了。代码变
             // 的复杂，但是奇数位就不一样，奇数位一定是放不超过字符串长度一半的字符，
             // 即使没有超过字符串长度一半的字符，也可以放满奇数位之后在放偶数位。同时
             // 这里注意S.length()/2+1有个加一很关键，是为了当遇到abbabbaaab的情况，
             // 这个时候a=5,b=5,应该要先放的奇数位的，如果放偶数位，则下一轮会发生数
             // 组越位。
            while (odd<S.length()&&count[i]!=0&&count[i]<S.length()/2+1){
                //把数字转换为字符
                result[odd] = (char)('a'+i);
                count[i]--;
                odd+=2;
            }
            while (count[i]!=0){
                result[even] = (char)('a'+i);
                count[i]--;
               even+=2;
            }
        }

         //toString、String.valueOf的区别：String.valueOf是静态方法能够自动判空（null）。
        return String.valueOf(result);
    }

    //自己的写法有bug，别看，复杂又无用
    public String reorganizeString(String S) {
        HashMap<Character,Integer> hashMap = new HashMap<>();
        String result =null;
        int maxCount=0;
        char maxChar=' ';
        boolean flag = false;
        for (char c:S.toCharArray()){
            if(hashMap.containsKey(c)){
                flag = true;
                hashMap.put(c,hashMap.get(c)+1);
                if (maxCount<hashMap.get(c)){
                    maxCount = hashMap.get(c);
                    maxChar =c;
                }
            }else {
                hashMap.put(c,1);
            }
        }
        int second = 0;
        char secondChar=' ';
        for (Character key :hashMap.keySet()){
            if (hashMap.get(key)>second&&key!=maxChar){
                second = hashMap.get(key);
                secondChar = key;
            }
        }
        if (flag == false){
            return S;
        }
        if (maxCount>= S.length()-maxCount+2){
            result ="";
        }else {
            int i=0;
            int j=0;
            char[] chars = new char[S.length()];
            while (hashMap.get(maxChar)!=0){
               chars[i]=maxChar;
               i+=2;
               hashMap.put(maxChar,hashMap.get(maxChar)-1);
           }
            while (i<=S.length()-1){
                if (hashMap.get(secondChar)!=0){
                    chars[i]=secondChar;
                    i+=2;
                    hashMap.put(secondChar,hashMap.get(secondChar)-1);
                }
            }
            while (i<=S.length()-1){

            }
            i=1;
            while (i<S.length()){
                if (S.charAt(j)!=maxChar){
                    chars[i]=S.charAt(j);
                    i+=2;
                }
                j++;
            }
            result = String.valueOf(chars);
        }
        return result;
    }

    public static void main(String[] args) {
        String S = "abbabbaaab";
        System.out.println(new StringReorganizeString767LeetCode().reorganizeString2(S));
    }
}
