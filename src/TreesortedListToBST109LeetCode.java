import java.util.ArrayList;
/**
*  * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
*  本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
* 示例:
* 给定的有序链表： [-10, -3, 0, 5, 9],
* 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
*     0
*    / \
*  -3   9
*  /   /
*-10  5
 * 解决思路：这题跟LeetCode108很像，不一样的就是存储的是链表形式的，所以考虑
 * 用空间换时间方案，把链表遍历存储到数组当中，然后就可以用LeetCode108的解法。
 *  * @author LemonLin
 * @Description :TreesortedListToBST109LeetCode
 * @date 2020/1/9-16:08
 */
public class TreesortedListToBST109LeetCode {
    //Definition for singly-linked list.
    public static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
    }
     //Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
    public TreeNode sortedListToBST(ListNode head) {
        //这里不用数组的原因是数组需要初始化长度，而这里刚开始无法确定数组长度，所以
        //这里用了ArrayList，而且ArrayList可以用size()获取长度。
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while(head!=null){
            nums.add(head.val);
            head = head.next;
        }
        return sortedArrayToBST(nums,0,nums.size()-1);
    }

    public TreeNode sortedArrayToBST(ArrayList nums,int left ,int right){
        if (left>right)
            return null;
        //这里的mid一定不能设置成全局变量，所有的递归函数需要的变量一般都设置在
        //递归函数内部比较合理
        int mid = (int)Math.ceil(((double) left+right)/2);
        TreeNode treeNode = new TreeNode((int)nums.get(mid));
        treeNode.left = sortedArrayToBST(nums,left,mid-1);
        treeNode.right = sortedArrayToBST(nums,mid+1,right);
        return treeNode;
    }

    public static void main(String[] args) {
        //[-10,-3,0,5,9]
        ListNode listNode0 = new ListNode(-10);
        ListNode listNode1 = new ListNode(-3);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(9);
        listNode0.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next =null;
        new TreesortedListToBST109LeetCode().sortedListToBST(listNode0);
    }
}
