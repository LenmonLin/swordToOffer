/**
 * @author LemonLin
 * @Description :LinkedListRandomListNodeClone26
 * @date 2019/3/13-11:08
 *
 * 题目描述
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个
 * 节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序
 * 会直接返回空）
 *
 * 解题思路：(分成三部分)
 *  1、在旧链表中创建新链表，此时不处理新链表的random指针；
 *       遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
 *  2、根据旧链表的random指向结点，初始化新链表的兄弟结点
 *        重新遍历链表，复制老结点的随机指针给新结点
 *  3、从旧链表中拆分得到新链表
 *         拆分链表，将链表拆分为原链表和复制后的链表
 *
 */

public class LinkedListRandomListNodeClone26 {
    //复杂链表结点
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;
        RandomListNode(int label) {
            this.label = label;
        }
    }
   //复制复杂链表主函数
    public RandomListNode Clone(RandomListNode pHead) {

        if (pHead==null){
            return null;
        }
        //遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面
        RandomListNode currentNode = pHead;
        while (currentNode!=null){
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            cloneNode.next = currentNode.next;
            currentNode.next = cloneNode;
            currentNode = cloneNode.next;
        }
        //重新遍历链表，复制老结点的随机指针给新结点
        currentNode = pHead;
        while (currentNode!= null){
            //注意这里有两个易错点，一个是必须判断currentNode.random==null
            //一个是应该将currentNode.random.next赋值给currentNode.next.random，而不
            // 是currentNode.random，这个容易想错
            if (currentNode.random == null){
                currentNode.next.random =null;
            }else {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }
        //拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead  = pHead.next;

        while (currentNode!= null){
            //注意这里cloneNode 要放在循环里面
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode .next;

            if (cloneNode .next ==null){
                cloneNode .next =null;
            }else {
                cloneNode .next = cloneNode .next.next;
            }
            currentNode= currentNode.next;
        }

        return pCloneHead;
    }

    public static void main(String[] args) {
        RandomListNode a= new RandomListNode(1);
        RandomListNode b= new RandomListNode(2);
        RandomListNode c= new RandomListNode(3);
        RandomListNode d= new RandomListNode(4);
        RandomListNode e= new RandomListNode(5);
        a.next = b;
        a.random=c;
        b.next = c;
        b.random = e;
        c.next=d;
        d.next=e;
        d.random =b;

        RandomListNode test = a;
        while (test!=null){
            System.out.println("===");
            System.out.println(test.label);
            System.out.println("=");
            test=test.next;
        }

        LinkedListRandomListNodeClone26 linkedListRandomListNodeClone26= new LinkedListRandomListNodeClone26();
        RandomListNode clone = linkedListRandomListNodeClone26.Clone(a);
        while (clone!=null){
            System.out.println(clone.label);
            clone = clone.next;
        }
    }
}
