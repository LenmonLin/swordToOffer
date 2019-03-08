package common;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author LemonLin
 * @Description :MakeTreelink
 * @date 2019/3/8-16:50
 *  *
 *  *                             A
 *  *                   B                    C
 *  *              D       E           F        G
 *  *                 H  I    J            K  L    M
 *  *              N
 */
public class MakeTreelink {

    public TreeLinkNode MakeLikedList(){
        TreeLinkNode a= new TreeLinkNode('A');
        TreeLinkNode b= new TreeLinkNode('B');
        TreeLinkNode c= new TreeLinkNode('C');
        TreeLinkNode d= new TreeLinkNode('D');
        TreeLinkNode e= new TreeLinkNode('E');
        TreeLinkNode f= new TreeLinkNode('F');
        TreeLinkNode g= new TreeLinkNode('G');
        TreeLinkNode h= new TreeLinkNode('H');
        TreeLinkNode i= new TreeLinkNode('I');
        TreeLinkNode j= new TreeLinkNode('J');
        TreeLinkNode k= new TreeLinkNode('K');
        TreeLinkNode l= new TreeLinkNode('L');
        TreeLinkNode m= new TreeLinkNode('M');
        TreeLinkNode n= new TreeLinkNode('N');
        a.left = b;a.right=c;
        b.left =d;b.right=e;b.parent=a;
        c.left =f;c.right=g;c.parent=a;
        d.right=h;d.parent=b;
        e.left =i;e.right=j;e.parent=b;
        f.right=k;f.parent=c;
        g.left =l;g.right=m;g.parent=c;
        h.left =n;h.parent=d;
        i.parent=e;
        j.parent=e;
        k.parent=f;
       l.parent=g;
        m.parent=g;
        n.parent=h;

        return a;
    }
}
