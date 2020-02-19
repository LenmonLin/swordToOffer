#二刷出错题

##DFS
简单难度：
TreeisSameTree100LeetCode：
  递归出口的判断易错：
        if (p==null&&q==null)return true;
        if (p==null||q==null)return false;
        if (p.val !=q.val) return false;
TreeisSymmetric101LeetCode
   递归出口有一个地方容易出错：
         if (root1==null&&root2==null)return true;
        //这里是==null,不能用root1!=null||root2!=null
        if (root1==null||root2==null)return false;
        if (root1.val!=root2.val)return false;
   虽然是一棵树，要设想为两颗树，传入两个结点进行判断，所以设置辅助函数：
      public boolean helper(TreeNode root1,TreeNode root2){
