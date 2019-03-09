import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :TreeSerialize62
 * @date 2019/3/9-20:22
 *
 * 题目描述
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 题意理解：
 * 所谓序列化指的是遍历二叉树为字符串；所谓反序列化指的是依据字符串重新构造成二叉树。
 * 解题思路：
 * 根据前序遍历规则完成序列化与反序列化。
 * 依据前序遍历序列来序列化二叉树，因为前序遍历序列是从根结点开始的。当在遍历二叉树时碰
 * 到Null指针时，这些Null指针被序列化为一个特殊的字符“#”。(牛客网平台要求)另外，结点之间
 * 的数值用逗号隔开。
 *
 * 解题bug:
 *        字符串中比较字符是否一样，应该用strings[index].equals("#")
 *        不能用strings[index] == "#"
 *
 *
 * 小知识点：
 * StringBuilder和StringBuffer类拥有的成员属性以及成员方法基本相同，区别是StringBuffer类的
 * 成员方法前面多了一个关键字：synchronized。这个关键字是在多线程访问时起到安全保护作用的,
 * 也就是说StringBuffer是线程安全的。
 *
 * String、StringBuilder、StringBuffer三者的执行效率：
 * 　　StringBuilder > StringBuffer > String
 *
 * String 与StringBuilder的区别，在
 * StringBuilder stringBuilder = new StringBuilder();
 *         for(int i=0;i<10000;i++){
 *             stringBuilder.append("hello");
 *         }
 *  代码中StringBulider 只new了一个对象，而如果换成String，就相当于new了10000个对象
 */
public class TreeSerialize62 {


    String Serialize(TreeNodeCommon root) {

        StringBuilder sb = new StringBuilder();
        if (root == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.data+",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();

    }

    //计算变量，作为下标，字符串是用String[]来存储
    int index =-1;
    TreeNodeCommon Deserialize(String str) {
        index++;
        String[] strings = str.split(",");
        TreeNodeCommon treeNodeCommon = null;

        //这里不能用strings[index] != "#"
        if (!strings[index].equals("#")){
            treeNodeCommon = new TreeNodeCommon(Integer.valueOf(strings[index]));
            treeNodeCommon.left = Deserialize(str);
            treeNodeCommon.right = Deserialize(str);
        }
        return treeNodeCommon;
    }

    public static void main(String[] args) {

    }
}
