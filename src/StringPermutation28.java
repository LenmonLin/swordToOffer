import java.util.ArrayList;
/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出
 * 由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * 解题思路:
 *      这是个递归求解的问题。递归算法有四个特性：（1)必须有可达到的终止条件，否则
 *      程序将陷入死循环；（2）子问题在规模上比原问题小；（3）子问题可通过再次递归
 *      调用求解；（4）子问题的解应能组合成整个问题的解。对于字符串的排列问题。如果
 *      能生成n - 1个元素的全排列，就能生成n个元素的全排列。对于只有1个元素的集合，
 *      可以直接生成全排列。全排列的递归终止条件很明确，只有1个元素时。
 *      所以进入循环：循环内第一步：把第一个字符独立出来，第一个字符和后面
 *      的所有字符交换，第二步，后面的字符又可以看作由一个字符和其他字符表示，这里
 *      就涉及到了递归，第三步，把第一步交换的字符换回来，这样才能循环的时候，重新
 *      执行第一步。
 *难点在于怎么获得每个字符的下标进行变化：
 *  答，可以使用string.tocharArray()方法把字符串转换位字符数组
 *  bug:牛客网判别：
 *  用例:abc
 * 对应输出应该为:["abc","acb","bac","bca","cab","cba"]
 *你的输出为:["abc","acb","bac","bca","cba","cab"]
 * 顺序不同，无法通过；
 * @author LemonLin
 * @Description :StringPermutation28
 * @date 2018/4/10-19:36
 */
public class StringPermutation28 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str != null && str.length() > 0) {
            recursionArrange(str.toCharArray(), 0, str.length() - 1, result);
        }
        return  result;
    }
    /*
     * 参数arrayA:给定字符串的字符数组
     * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
     * 参数end:字符串数组的最后一位
     * 函数功能：输出字符串数字的各个字符全排列
     */
    public void recursionArrange(char[] arrayA,int start,int end,ArrayList<String> stringArrayList){
        //递归出口
        if(start == end){
            String val = String.valueOf(arrayA);
            //这个判断必须加，否则会有重复的字符串放入
            if (!stringArrayList.contains(val)) {
                stringArrayList.add(val);
            }
        }
        else{
            for(int i = start;i <= end;i++){
                //把第一个字符独立出来，第一个字符和后面的所有字符交换，
                swap(arrayA,i,start);
                //第二步，后面的字符又可以看作由一个字符和其他字符表示，这里就涉及到了递归，
                recursionArrange(arrayA,start+1,end,stringArrayList);
                //第三步，把第一步交换的字符换回来,变成原先的顺序才能下次循环的时候，
                // 重新执行第一步。
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
        ArrayList<String> permutation = test.Permutation(A);
        for (int i=0;i<permutation.size();i++){
            System.out.println(permutation.get(i));
        }
    }
}
