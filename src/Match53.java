/**
 * @author LemonLin
 * @Description :Match53
 * @date 2019/3/5-15:06
 *
 *题目描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（包含0次）。在本题中，匹配是指字符串的所有
 * 字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"
 * 和"ab*a"均不匹配
 *
 *
 * 解题思路：
 *      总体情况是，str是需要匹配的字符串，一个pattern是需要匹配的模式，分别用
 *      strIndex,patternIndex,来比较字符。
 *      需要用到递归的思路来对数组下标进行匹配。
 *
 *      匹配规则上：
 *     首先，考虑特殊情况：
 *          1>str 和pattern都为空，返回true
 *          2>当str不空，而pattern空了，返回false（因为这样，就无法
 *             匹配成功了,而如果str空了，pattern非空，还是可能匹配成
 *             功的，比如pattern是“a*a*a*a*”,由于‘*’之前的元素可以出现0次，
 *             所以有可能匹配成功）
 *     之后就开始匹配第一个字符，这里有两种可能：匹配成功或匹配失败。但考虑到
 *     pattern下一个字符可能是‘*’， 这里我们分两种情况讨论：pattern下一个字符
 *     为‘*’或不为‘*’：
 *           1>pattern下一个字符不为‘*’：这种情况比较简单，直接匹配当前字符。如果
 *             匹配成功，继续匹配下一个；如果匹配失败，直接返回false。注意这里的
 *             “匹配成功”，除了两个字符相同的情况外，还有一种情况，就是pattern的
 *             当前字符为‘.’,同时str的当前字符不为‘\0’。
 *           2>pattern下一个字符为‘*’时，稍微复杂一些，因为‘*’可以代表0个或多个。
 *             这里把这些情况都考虑到：(''与'.*'是匹配的这个？)
 *                a>当‘*’匹配0个字符时，str当前字符不变，pattern当前字符后移两位，
 *                  跳过这个‘*’符号；
 *                b>当‘*’匹配1个或多个时，str当前字符移向下一个，pattern当前字符
 *                 不变。（这里匹配1个或多个可以看成一种情况，因为：当匹配一个时，
 *                 由于str移到了下一个字符，而pattern字符不变，就回到了上边的情况a；
 *                 当匹配多于一个字符时，相当于从str的下一个字符继续开始匹配）
 *
 */
public class Match53 {

    public boolean match(char[] str, char[] pattern) {

        if (str==null && pattern ==null){
            return true;
        }

        int strIndex = 0;
        int patternIndex =0;
        return matchCore(str,strIndex,pattern,patternIndex);
    }

    public boolean matchCore(char[] str,int strIndex,char[] pattern,int patternIndex){

        //匹配成功的出口是，str匹配到了尾部，pattern匹配到了尾部
        if (strIndex == str.length && patternIndex == pattern.length){
            return  true;
        }

        //匹配失败的出口是，pattern先到了尾部，而str还没到
        if (strIndex!=str.length && patternIndex == pattern.length){
            return  false;
        }
        //这里要限定数组下标不能越界
        if (patternIndex+1<pattern.length&&pattern[patternIndex+1]=='*'){
            if (str[strIndex]!=pattern[patternIndex]){
                return matchCore(str,strIndex,pattern,patternIndex+2);
            }
            else {
                return matchCore(str,strIndex+1,pattern,patternIndex);
            }
        }

        if (str[strIndex]==pattern[patternIndex]||(pattern[patternIndex]=='.'&&strIndex != str.length)){
            return matchCore(str,strIndex+1,pattern,patternIndex+1);
        }else {
            //这里也是重要的出口
            return false;
        }
    }


    public static void main(String[] args) {
        Match53 match53= new Match53();
        char [] str={'1','2','2','3'};
        char [] pattern={'1','2','*','3'};
        boolean match = match53.match(str, pattern);
        System.out.println(match);
    }
}
