import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
* 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会
 * 出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
* 示例 1:
* 输入: S = "ababcbacadefegdehijhklij"
* 输出: [9,7,8]
* 解释:
* 划分结果为 "ababcbaca", "defegde", "hijhklij"。
* 每个字母最多出现在一个片段中。
* 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
* 注意:
* S的长度在[1, 500]之间。
* S只包含小写字母'a'到'z'。
 * @author LemonLin
 * @Description :GreedypartitionLabels763LeetCode
 * @date 20.3.15-19:55
 * 思路：https://leetcode-cn.com/problems/partition-labels/solution/
 * map-jia-tan-xin-jian-dan-you-xiao-shi-jian-fu-za-d/
 * 贪心算法：
 * 第一步：
 * 先遍历一遍，用字典存储每个字符最后的位置。
 * 第二步：
 * 从第一个字符开始遍历，每获取一个字符就将该字符最后一次出现的位置索引定为当前片段
 * 的最后位置，在达到该位置之前，继续寻找更靠后的最后位置，若达到最后位置之前都没
 * 有发现更靠后的最后位置，则将当前最后位置作为一个片段的末尾。
 */
public class GreedypartitionLabels763LeetCode {
    public List<Integer> partitionLabels(String S) {
        //特殊情况特殊处理
        if (S==null) return null;
        ArrayList<Integer> result = new ArrayList<>();
        //特殊情况特殊处理
        if (S.length()==0){
            return result;
        }
        HashMap<Character,Integer> hashMap = new HashMap<>();
        //第一遍遍历，先存储每个字符最后的位置。
        for (int i=0;i<S.length();i++){
            hashMap.put(S.charAt(i),i);
        }
        //第二遍，获取每个字符最后的位置。
        int cur=0;
        //为了记录长度，结果需要存入长度。
        int start =0;
        //临时的最后的位置
        int temp = hashMap.get(S.charAt(cur));
        //这里需要用while比for好控制，逻辑清晰一些
        while (cur<S.length()){
            while (cur<temp){
                //不断更新片段的最后能够到达的位置
                if (temp<hashMap.get(S.charAt(cur))){
                    temp = hashMap.get(S.charAt(cur));
                }
                cur++;
            }
            //循环退出的时候cur == temp ,所以此时的cur应该为上一个片段的最后一个位置
            //经过cur++操作，cur变成了下一个片段的起始位置
            cur++;
            if (cur<S.length()){
                //重新更新新片段的最后一个位置
                temp = hashMap.get(S.charAt(cur));
            }
            result.add(cur-start);
            //重新更新一下起点
            start=cur;
        }
        return result;
    }

    public static void main(String[] args) {
        String S = "";
        System.out.println(new GreedypartitionLabels763LeetCode().partitionLabels(S));
    }
}
