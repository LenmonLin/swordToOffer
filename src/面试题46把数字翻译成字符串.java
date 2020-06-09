/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，
 * 11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，
 * 用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示：
 * 0 <= num < 231
 * @author LemonLin
 * @Description :面试题46把数字翻译成字符串
 * @date 20.6.9-19:38
 * 思路：参考LeetCode91的解法。
 */
public class 面试题46把数字翻译成字符串 {
    public int translateNum(int num) {
        //如果num==0要特殊判断，下面的tempNum!=0循环控制不住0
        if (num==0){
            return 1;
        }
        //看题解解答直接把num转换为string就可以调用length方法了
        //计算有几位
        int count =0;
        int tempNum = num;
        while (tempNum!=0){
            tempNum/=10;
            count++;
        }
        //把数字转换为数组保存
        int [] numArray = new int[count];
        int i=count-1;
        tempNum = num;
        while (tempNum!=0){
            numArray[i] = tempNum%10;
            tempNum/=10;
            i--;
        }
        int [] dp = new int[numArray.length+1];
        //这里的dp[0]=1是反推出来的，可能要记住这个特殊情况
        dp[0]=1;
        //默认传入的数字的第一位可以构成字符
        dp[1] =1;
        for (int j = 2; j <=numArray.length ; j++) {
            int first = numArray[j-2];
            int second = numArray[j-1];
            //如果first 和second单独能够被翻译，同时组合也能被翻译
            if (second<=9&&second>=0&&isVaild(first,second)){
                dp[j]= dp[j-2]+dp[j-1];
                //如果first 和second单独不能够被翻译，同时组合能被翻译
            }else if (isVaild(first,second)){
                dp[j] = dp[j-2];
                //如果first 和second单独能够被翻译，同时组合不能被翻译
            }else if (second<=9&&second>=0&&!isVaild(first,second)){
                dp[j] = dp[j-1];
                //如果first 和second单独不能够被翻译，同时组合不能被翻译
            }else {
                return 0;
            }
        }
        return dp[dp.length-1];
    }
    private boolean isVaild(int a,int b){
        if (a==0||a>3){
            return false;
        }
        if (a*10+b<=25){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        int num = 12258;
//        int num = 20;
//        int num = 27;
        int num = 0;
        System.out.println(new 面试题46把数字翻译成字符串().translateNum(num));
    }
}
