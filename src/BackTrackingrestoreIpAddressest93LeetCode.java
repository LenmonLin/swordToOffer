import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * 思路：用回溯比较直白，一个数字一个数字的遍历，满足条件添加进IP地址格式中。不
 * 满足就回溯。
 * @author LemonLin
 * @Description :BackTrackingrestoreIpAddressest93LeetCode
 * @date 20.1.21-11:51
 * 思路：参考：https://leetcode-cn.com/problems/generate-parentheses/solution/
 * hui-su-suan-fa-by-liweiwei1419/
 * 这题细节很多，要想明白很多细节。首先拿到题目，第一反应是回溯解法。回溯题目有以下
 * 几个点需要思考：
 * 1、怎么回溯？画递归图解决。
 * 2、具体回溯的循环代码，几层循环。
 * 3、回溯函数的参数选哪些，承接第二问。
 * 4、递归出口（满足添加下）？
 * 5、剪枝出口
 *
 * 1、25525511135  画出分支可以看得出，最多三个分支，因为ip地址的某一小段最多只能
 * 是三位数，然后可以发现层数必须是4，否则也不满足条件。大概能知道是怎么搞的了。
 * 2、一般想的外层循环指的是树高，内层循环指的是每个节点的每个分支。树高一般来说是给定
 * 的字符串的长度，所以一般都遍历给定字符串长度。但是要的结果肯定不是一整颗树，是满
 * 足题目要求的树的某一部分。所以就要想方设法剪枝，这里就是第五步的剪枝出口，剪枝有两种
 * 情况的剪枝，一种用剪枝出口，满足if条件，直接continue略过，一种就是满足非剪枝条件下
 * 弄一个if语句，所有的遍历放在这个if语句中操作，然后递归操作就在这个if中处理。同时当
 * 到达满足题目要求的树的那一部分时，应该把结果保存起来，这个就是第四步的递归出口。
 * 本题来说，暴力大树就是最大层数为字符串长度，当然这个路径是不满足条件的要剪枝。
 * 满足条件的大树就是4层高的树，4层树高，并不意味着只需要遍历4个字符，所有的字符
 * 还是需要遍历，只是每个节点容纳的数个数不一样。树高一层循环，即隐形外循环。
 * 内循环就是每个节点最多只有三个分支。每个分支节点中的字符个数从1，增加到3.
 * 最后的结果是满足题目要求的路径节点组合在一起，得出的ip地址。
 * 3、外层关于层数的隐形循环的参数需要两个，一个是常规的遍历给定字符串的下标index。
 * 还需要一个额外的变量depth对层数进行判断。这是本题的特殊的点。一个记录单个路径的ArrayList，
 * 一个记录总结果的ArrayList。
 * 4、遍历到最后index ==s.length同时4==depth。
 * 5、这个就多了，本题有效的ip地址的范围是
 * 0.0.0.0~255.255.255.255
 *bug1:
 * 输入:
 * "0000"
 * 输出
 * []
 * 预期结果
 * ["0.0.0.0"]
 * bug2:
 * "010010"
 * 输出
 * ["0.1.0.010","0.1.00.10","0.1.001.0","0.10.0.10","0.10.01.0","0.100.1.0",
 * "01.0.0.10","01.0.01.0","01.00.1.0","010.0.1.0"]
 * 预期结果
 * ["0.10.0.10","0.100.1.0"]
 */
public class BackTrackingrestoreIpAddressest93LeetCode {
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        if (len > 12 || len < 4) {
            return result;
        }
        backTracking(s,0,0,path,result);
        return result;
    }

    /**
     * @param s 传入的目标字符串
     * @param index 遍历字符串的下标
     * @param depth 树的高度，这是本题和一般回溯题的区别，本题一个节点可以容纳很多
     *              个数，所以除了index变量还需要depth变量。一般回溯题一个节点容纳一个数，
     *              这样就只需要index变量代表树高即可。
     * @param path  某个满足IP地址规则的IP地址
     * @param result 所有满足IP地址规则的IP地址
     */
    private void backTracking(String s, int index, int depth, ArrayList<String> path,
                              ArrayList<String> result){
        //这里为啥是index==s.length和depth==4,因为index ==s.length()-1时还需要
        // 判断操作，比如下面的for循环代码还没执行，所以要等超过了这个边界才能添加
        // 到result里面，我这个也是debug的时候才发现的。
        if (index==s.length()&&depth==4){
            result.add(String.join(".",path));
            return;
        }
        //这个循环的下标，index，和判断条件index+3是本题的一个精髓的地方。细品
        for (int i=index;i<index+3;i++){
            //这个判断其实可以放在for循环和i<index+3&&，但是这样代码不清晰，就放在了这里
            if(depth>3){
                break;
            }
            //这个剪枝条件处理了大部分分支，提高了速度
            if (depth==0&&s.length()-1-i>9||
                    depth==1&&s.length()-1-i>6||
                    depth==2&&s.length()-1-i>3||
                    depth==3&&s.length()-1-i>0){
                continue;
            }
            //这里是让i+1不越界，所以加多了一个判断。因为需要用到i+1.
            if (i+1<=s.length()){
                String  temp = s.substring(index,i+1);
                if (isVaildIP(temp,depth)){
                    path.add(temp);
                    backTracking(s,i+1,depth+1,path,result);
                    path.remove(path.size()-1);
                }
            }
        }
    }
    private boolean isVaildIP(String s,int depth){
        int digits =0;
        for (int i=0;i<s.length();i++){
            digits = (s.charAt(i)-'0')+digits*10;
            //处理bug1:本题的测试用例可以让第一段数字为0
            //第一个分节点的IP地址不能为0
//            if (depth==0&&digits==0){
//                return false;
//            }
            //处理bug2,这里处理的很巧妙，位数大于1的IP段，首位不能为0
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false;
            }
            if (digits>255||digits<0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s =
                "010010";
        System.out.println(new BackTrackingrestoreIpAddressest93LeetCode().restoreIpAddresses(s));
    }
}
