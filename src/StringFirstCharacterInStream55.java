/**
 * 题目描述:
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两
 * 个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，
 * 第一个只出现一次的字符是"l"。
 *解题思路：
 * 利用hash表(数组)，先扫描一遍输入字符串，key值是出现的字符，value值记录出现的字符的次数，
 * 第二遍顺序扫描直接读出value值是1的字符。
 * 其实有更简单的方法：参考LeetCode387，用hashmap遍历两遍解决战斗，没有太多麻烦事
 *解题要点：
 * 1、ASCII 码转字符；http://blog.csdn.net/myweishanli/article/details/38924481,
 * 直接将这个字符转化为int型就可以得到ascii码值；
 * charc   =   'a '；
 * int  b   =  c； //字符的ascii码值
 * 字符转ascII 码:
 * int d =97;
 * char e = (char)d;
 * 2、256的数组，初始化为-1，当第一次出现赋值为在字符流中的位置，第二次出现更新为-2；
 * 考虑用hash表的时候，字符不要惯性的认为只有26个英文字符，还有大写，-、+等符号字符，
 * 所以根据字符的位数，应该是256位字符。
 * 3、扫描整个字符串，找出数组中大于等于0的值对应的字符；
 * 4、用到的数据结构是hash表，其实就是数组
 * 解题bug:
 *  #1 index 的初始化应该放在全局变量，初始化不能放在函数内，切记切记
 * #2 找最小值这个初始值必须设置最大的
 * #3  静态方法内部，不能直接调用非静态方法，可以通过new一个对象来实现调用非静态方法，
 * 非静态方法必须寄居在对象上才能使用
 *  @author LemonLin
 *  @Description :StringFirstCharacterInStream55
 *  @date 2019/5/8-15:27
 */
public class StringFirstCharacterInStream55 {
        int[] hash = new int[256];
        int index =0;  //#1
        //初始化hash表的值为-1
        StringFirstCharacterInStream55() {
            for(int i=0;i<256;i++){
                hash[i] = -1;
            }
        }
        //Insert one char from stringstream
        public void Insert(char ch){
            if (hash[ch]<0){
                hash[ch] = index;
            }else if(hash[ch]>= 0){
                hash[ch] = -2;
            }
            index++;
        }
        //return the first appearence once char in current stringstream
        public  char FirstAppearingOnce() {
            int minIndex = 256;//#2 找最小值这个初始值必须设置最大的
            char minChar ='#';
            //找出最小的大于等于0的数值，即第一次出现的不重复的数值；
            for (int i=0 ;i<256;i++){
                if(hash[i]>=0&&minIndex>hash[i]){
                    minIndex = hash[i];
                    minChar = (char) i;
                }
            }
            if(minIndex >=0){
              return minChar;
            }else
                return '#';
        }
        //#3  静态方法内部，不能直接调用非静态方法，可以通过new一个对象来实现调用非静态方法，
        // 非静态方法必须寄居在对象上才能使用
        public static void main(String[] args){
            StringFirstCharacterInStream55 a = new StringFirstCharacterInStream55();
            a.Insert('g');
            a.Insert('o');
            a.Insert('o');
            a.Insert('g');
            a.Insert('l');
            a.Insert('e');
            char output = a.FirstAppearingOnce();
            System.out.println(output);
        }
    }

