import java.util.Arrays;
import java.util.Comparator;

/**
 * @author LemonLin
 * @Description :ArrayPrintMinNumber33
 * @date 2019/3/10-20:35
 *
 * 题目描述
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输
 * 入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 解题思路：
 *
 * 一个非常直观的解决大数问题的方法就是把数字转换成字符串。
 * 先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符串数组拼接出来。关键就是制定
 * 排序规则。
 * 排序规则是将要排序的其中两个string，进行拼接，因为如果int拼接会造成大数问题，所以将int先转换为string
 * 用String.valueOf(numbers[i])
 * a,b分别是数组中的两个元素；
 *  * 排序规则如下：
 *  * 若ab > ba 则 a > b，
 *  * 若ab < ba 则 a < b，
 *  * 若ab = ba 则 a = b
 *
 *  小知识点：
 *      数组中按照指定排序规则进行str排序的固定写法， c1.compareTo(c2)就是c1大返回负数，c2大返回正数
 *      string c1=s1+s2是指s1与s2互相拼接；
 *  Arrays.sort(str,new Comparator<String>(){
 *             @Override
 *             public int compare(String s1, String s2) {
 *                 String c1 = s1 + s2;
 *                 String c2 = s2 + s1;
 *                 return c1.compareTo(c2);
 *             }
 *         });
 *
 * 解题步骤：1、将int转换为string
 * 2、将string 进行排序
 * 3、将string数组转化为StringBuilder，然后输出sb.toString()
 */
public class ArrayPrintMinNumber33 {

    public String PrintMinNumber(int [] numbers) {
        if (numbers ==null ||numbers.length ==0){
            return "";
        }
        //1、转换
        int len = numbers.length;
        String[] str = new String[len];
        for (int i=0;i<len;i++){
            str[i] = String.valueOf(numbers[i]);
        }
        //2、排序
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 =o1+o2;
                String s2 = o2+o1;
                return s1.compareTo(s2);
            }
        });
        //3、把排序好的string数组转换为string字符串
        StringBuilder sb = new StringBuilder();
        for (int i =0;i<len;i++){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int [] test = {3,32,321};
        ArrayPrintMinNumber33 arrayPrintMinNumber33 = new ArrayPrintMinNumber33();
        String s = arrayPrintMinNumber33.PrintMinNumber(test);
        System.out.println(s);
    }
}
