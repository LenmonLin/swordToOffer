import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。
 * 示例：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC", "CCCCCAAAAA"]
 * @author LemonLin
 * @Description :SlidingwindowfindRepeatedDnaSequences187LeetCode
 * @date 20.5.18-5:30
 * 题意理解：看评论：题目的意思是编写一个函数来查找子串，这个子串长度为10，在原字
 * 符串中出现超过一次。
 * 思路参考：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/hua-dong-chuang-kou-ha-xi-by-powcai/
 * 用一个滑动窗口截取字符子串，存入HashMap或者HashSet中都可以，只要存在了，就添加
 * 进最后的返回结果中。
 * 注意，添加到返回结果里面的也要去重，所以选用HashSet，然后转行为List返回。
 * bug1:输入:
 * "AAAAAAAAAAA"
 * 输出
 * []
 * 预期结果
 * ["AAAAAAAAAA"]
 */
public class SlidingwindowfindRepeatedDnaSequences187LeetCode {
    public List<String> findRepeatedDnaSequences(String s) {
        //注意这里result必须用HashSet去重
        HashSet<String> result = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        //解决bug1，注意这里要添加=号，因为substring(a,b),b位置不会取到。
        for (int i=0;i+10<=s.length();i++){
            String temp = s.substring(i,i+10);
            if (visited.contains(temp)){
                result.add(temp);
            }else {
                visited.add(temp);
            }
        }
        //HashSet 复制到ArrayList中
        return new ArrayList<>(result);
    }
}
