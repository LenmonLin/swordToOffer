import java.util.ArrayList;

/**
 * @author LemonLin
 * @Description :StringPermutation28
 * @date 2018/4/10-19:36
 *
 * 基本思路:
 *      把第一个字符独立出来，第一步，第一个字符和后面的所有字符交换，
 *      第二步，后面的字符又可以看作由一个字符和其他字符表示;
 *难点在于怎么获得每个字符的下标进行变化：
 *  答，可以使用string.tocharArray()方法把字符串转换位字符数组
 */
public class StringPermutation28 {

    public ArrayList<String> Permutation(String str) {
        return  new ArrayList<>();
    }

    /*
     * 参数arrayA:给定字符串的字符数组
     * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
     * 参数end:字符串数组的最后一位
     * 函数功能：输出字符串数字的各个字符全排列
     */
    public void recursionArrange(char[] arrayA,int start,int end){
        if(end <= 1)
            return;
        if(start == end){
            for(int i = 0;i < arrayA.length;i++)
                System.out.print(arrayA[i]);
            System.out.println();
        }
        else{
            for(int i = start;i <= end;i++){
                swap(arrayA,i,start);
                recursionArrange(arrayA,start+1,end);
                swap(arrayA,i,start);
            }
        }

    }
    //交换数组m位置和n位置上的值
    public void swap(char[] arrayA,int m,int n){
        char temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }

    public static void main(String[] args){
        StringPermutation28 test = new StringPermutation28();
        String A = "abc";
        char[] arrayA = A.toCharArray();
        test.recursionArrange(arrayA,0,arrayA.length-1);
    }
}
