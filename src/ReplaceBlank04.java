/**
 * 总体思路：
 *      从后向前复制字符串；
 *      先遍历一遍字符串，记录空格的数量和字符串的长度，确定最后生成的字符串的长度
 *      java中遍历字符串使用的是charAt()方法
 * @author LemonLin
 * @Description :ReplaceBlank04
 * @date 2018/3/3-21:14
 */
public class ReplaceBlank04 {


    public int replaceBlank(String string, int length) {
        // write your code here
        int countBlank=0;
        for (int i=0;i<string.length();++i){
            if (string.charAt(i)==' '){
                countBlank++;
            }
        }
        char[] chars = new char[countBlank*2+length];

        //char[] a = string.toCharArray()
        //k是记录原字符串长度
        int k =string.length()-1;
        for (int j=chars.length-1;j>=0;--j){
            if (string.charAt(k)==' '){
                chars[j]='0';
                chars[j-1]='2';
                chars[j-2]='%';
                j-=2;
            }else {
                chars[j]=string.charAt(k);
            }
            --k;
        }

        return countBlank*2+length;
    }

    public static void main(String[] args) {
        String string = "Mr John Smith";
        ReplaceBlank04 replaceBlank04 = new ReplaceBlank04();
        int test=replaceBlank04.replaceBlank(string,13);
        System.out.println(test);
/*
        if (' '==' '){
            System.out.println("===");
        }else
            System.out.println("!=");*/
    }
}
