import java.util.ArrayList;

/**
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * @author LemonLin
 * @Description :DesignNestedIterator341LeetCode
 * @date 20.5.21-11:56
 *思路：https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/javashi-yong-list-di-gui-by-tzfun/
 * 就是利用递归思路，在初始化迭代器的时候就直接把结果遍历出来存入结果数组中，递归
 * 遍历列表中的数据，是整数就放入List，不是则再递归遍历
 */
public class DesignNestedIterator341LeetCode {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */

    private ArrayList<Integer> result ;
    private int index;

//因为是设计类题目，很多直接提供的接口无法出现。
//    public NestedIterator(List<NestedInteger> nestedList) {
//        result = new ArrayList<>();
//        index =-1;
//        helper(nestedList);
//        //在next()方法中要先index++，所以用初始化为-1
//
//    }
//
//    private void helper (List<NestedInteger> nestedIntegerList){
//        for (NestedInteger tmp : nestedIntegerList) {
//            if (tmp.isInteger())
//                result.add(tmp.getInteger());
//            else
//                helper(tmp.getList());
//        }
//    }
//    @Override
//    public Integer next() {
//        if (this.hasNext())  {
//            index++;
//            return result.get(index);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean hasNext() {
//        if (index + 1 < result.size()){
//            return true;
//        }
//        return false;
//    }


    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
}
