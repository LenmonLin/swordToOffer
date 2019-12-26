import common.MakeTree;
import common.TreeNodeCommon;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 树的层次遍历：
 * 建议看一下浙江大学的数据结构对应章节，讲的很清晰，醍醐灌顶
 *  * https://www.icourse163.org/learn/ZJU-93001?tid=1003997005#/learn/content?type=detail&id=1007588486&cid=1009151984
 *
 *  思考：怎么打印出null是个问题。
 * @author LemonLin
 * @Description :TreeLevelOrderByLemonLin
 * @date 19.8.21-16:01
 */
public class TreeLevelOrderByLemonLin {
    public void TreeLevelOrderByLemonLin(TreeNodeCommon root){
        Queue<TreeNodeCommon> queue = new LinkedList();
        //先放入根节点
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNodeCommon treeNodeCommon = queue.poll();
            System.out.println(treeNodeCommon.data);
            if (treeNodeCommon.left!= null){
                queue.offer(treeNodeCommon.left);
            }
            if (treeNodeCommon.right!=null){
                queue.offer(treeNodeCommon.right);
            }
        }
    }
    public static void main(String[] args) {
        /**
         * 新建一颗树      8
         *                  6       10
         *                null   7    9    11
         */
        TreeNodeCommon treeNodeCommon = new MakeTree().makeTree();
        new TreeLevelOrderByLemonLin().TreeLevelOrderByLemonLin(treeNodeCommon);
    }
}
