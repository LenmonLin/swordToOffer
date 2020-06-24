import java.util.LinkedList;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始
 * 往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将
 * 是 [1, 1, 1, 2, 1, 4, 6]。
 * 示例：
 * 输入：["StockSpanner","next","next","next","next","next","next","next"],
 * [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 * 提示：
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 * @author LemonLin
 * @Description :StackStockSpanner901LeetCode
 * @date 20.6.24-14:56
 * 思路：解释一下题目：有个难以理解的是，这题不是一次性返回数组[null,1,1,1,2,1,4,6]，
 * 而是每调用一次next(prices),就返回当前prices对应的价格值。
 * 参考：https://leetcode-cn.com/problems/online-stock-span/solution/dan-diao-zhan-tao-lu-xie-fa-you-hua-wei-guan-fang-/
 * 这道题利用了 单调增栈每次插入新数据时，会一次性将比自己小的元素全部排出 的特性
 * 这个特性正好和题目里的 价格小于或等于今天价格的最大连续日 是刚好吻合的
 * 问题在于，因为单调栈是要把元素都丢弃的，状态都被“折叠”了，我们会丢失长度，所以
 * 容易想到，我们需要cache一下之前栈内元素被折叠的长度，使用另一个同步栈来缓存
 */
public class StackStockSpanner901LeetCode {
    private LinkedList<Integer> stack;
    private LinkedList<Integer> cache;
    public StackStockSpanner901LeetCode() {
        stack = new LinkedList<>();
        //缓存价格值
        cache = new LinkedList<>();
    }

    public int next(int price) {
        int tempResult =1;
        //这里有等号，相等也要出栈
        while (stack.size()>0&&stack.peekLast()<=price){
            stack.removeLast();
            tempResult+=cache.removeLast();
        }
        stack.addLast(price);
        cache.addLast(tempResult);
        return tempResult;
    }

    public static void main(String[] args) {
        StackStockSpanner901LeetCode stack901 = new StackStockSpanner901LeetCode();
        int [] prices ={100, 80, 60, 70, 60, 75, 85};
        for (int i = 0; i <prices.length ; i++) {
            System.out.println(stack901.next(prices[i]));
        }
    }
}
