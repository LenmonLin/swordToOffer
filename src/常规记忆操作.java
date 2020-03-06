import java.util.*;

/**
 * @author LemonLin
 * @Description :常规记忆操作
 * @date 19.9.7-20:10
 */
public class 常规记忆操作 {
    public static void main(String[] args) {
        //位操作
//        new 常规记忆操作().bit();
        //list操作
        new 常规记忆操作().list();
//        //hashmap操作
//        new 常规记忆操作().hashmap();
//        //数学运算
//        new  常规记忆操作().math();
//        //关于队列操作
//        new 常规记忆操作().queue();
//        //关于string的操作
//        new 常规记忆操作().string();
//        //关于stringBuilder的操作
//        new 常规记忆操作().stringBuilder();
//        // 关于栈的操作
//        new 常规记忆操作().stack();
//        //堆的实现,包括最大堆和最小堆
//        new 常规记忆操作().heap();
    }

    //位操作
    public void bit(){
        int num1 =2;
        int num2 =3;
        //2 =======>0010
        //3 =======>0011
        //2^3就为0001，结果就是1
        System.out.println("^(亦或运算) ，针对二进制，相同的为0，不同的为1");
        System.out.println(num1^num2);
        /**
        *&（与运算） 针对二进制，只要有一个为0，就为0
         */
        System.out.println(num1&num2);
        /**
         * <<表示左移移，不分正负数，低位补0；
         * >>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
         *  >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为
         *  负数，则右移后高位同样补0
         */
        //获取某个数最后一位是1还是0，只要将这个数和1与即可；
        System.out.println(num1&1);

        //左边存a,右边存b
        int a=1;
        int b=1;
        System.out.println((a<<1)+b);
    }
    //list操作
    public void list(){
        //对数组进行排序
        int[] numbers={10,9,8,7,4,5,2,1};
        //结果：10 2  4   5  7  8  9  1，也就是toIndex最后一位没排，不包括toIndex
        Arrays.sort(numbers,1,numbers.length-1);
//        Arrays.sort(numbers);
        for (int i=0;i<numbers.length;i++){
            System.out.println("numbers"+numbers[i]);
        }
        int[][] intervals={{2,3},{4,5},{6,7},{8,9},{1,10}};
        //对二维数组中的第一个数进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //复制ArrayList,arrayList2复制了ArrayList1的
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        ArrayList arrayList2 = new ArrayList(arrayList1);

        //ArrayList转二维数组：
        ArrayList<int[]> result = new ArrayList<>();
        int left =0;
        int right =2;
        int [] temp;
        for(int i=0;i<right-left;i++){
             temp= new int[right-left];
            temp[i] = left;
            result.add(temp);
        }
        int[] [] out = new  int[result.size()][];
        //dp是最后结果
        int[][] dp = result.toArray(out);
    }
    //hashmap操作
    public  void hashmap(){
        //hashmap 遍历key
        HashMap<String,Integer> hashMap = new HashMap();
        for (String test3:hashMap.keySet()){
            hashMap.get(test3);
        }

        //把hashmap中的value值以列表ArrayList的形式返回：
        Map<Integer, List<String>> map=new HashMap<>();
        List<List<String>> result = new ArrayList<>(map.values()) ;
    }

    //数学运算
    public void math(){

        //   /号结果等于得到的整数（商的整数），%号结果等于余数
        System.out.println(13/5);

        //这里用Math.ceil 进一法来取右边的数，比如0,1两个的平均数取1。
        int left=0;
        int right =1;
        int mid = (int)Math.ceil(((double) left+right)/2);
        System.out.println(mid);

        //异或某个数字
        int temp =12;
        int temp2=123;
        temp ^= temp2;
        //向右移位某个数字
        temp2=temp2>>1;
        //与某个数字
        temp2=temp2&1;

        //Integer的最小值为0x80000000也就是-2^31次方。
        int n=Integer.MIN_VALUE;
    }

    //关于队列操作
    public void queue(){
        //队列操作,取队头元素linkedList.peekFirst()，出队linkedList.pollFirst(),
        //这里add,remove方法是List接口的;offer，poll方法是Queue接口的
        //linkedList即是List也是Queue
        LinkedList linkedList = new LinkedList();
        linkedList.peekFirst();
        linkedList.pollFirst();
        linkedList.offer("进队");

        /**
         * 链表操作，输出结果是tou2 tou wei wei2
         */
        LinkedList linkedList1 = new LinkedList();
        linkedList1.addFirst("tou");
        linkedList1.addFirst("tou2");
        linkedList1.addLast("wei");
        linkedList1.addLast("wei2");
        for (Object s12: linkedList1){
            System.out.println(String.valueOf(s12));
        }
    }
    //关于stringBuilder的操作
    public void stringBuilder(){
        //stringbuilder转换为字符串
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.toString();
        //stringBuilder反转操作
        stringBuilder.reverse();
        //清空stringBuilder，这个方法效率高
        stringBuilder.delete(0,stringBuilder.length());
    }

    //关于String的操作
    public  void string(){
        //字符串转换为字符数组(目的是为了方便改动字符串中的字符)
        String string = "hello";
        char [] chars = string.toCharArray();
        //字符数组再转回字符串
        String.valueOf(chars);
        //字符串分隔（输入ip地址123.23.24.123，把每个分号分隔的数字拿出来进行判断）
        String IP  = "123.23.24.123";
        String[] strings = IP.split("\\.");
        //去除字符串两边的空格
        String test1 = "  hello   ";
        test1.trim();
        //判断一个字符是否为空格
        String s="hello world";
        for (int i=0;i<s.length();i++) {
            //取出字符串某个下标值
            Character.isSpaceChar(s.charAt(i));
        }
        //判断字符串是否为空字符串
        String needle = "helo";
        needle.isEmpty();
        //处理前导0，把0010改成10;
        String s1 ="0010";
        //^表示开头匹配，0+表示匹配一个或者多个
        s1=s1.replaceAll("^(0+)", "");
        System.out.println("处理前导0，把0010改成10========"+s1);
        //字符串子串，返回he,不包括右边的下标
        String s3="hello";
        System.out.println(s3.substring(0, 2));
        //判断字符串A中是否有子串B可以用Java中的字符串的indexOf方法，如果返回的不是-1，就有包含。
        String string1 = "hello";
        String string2= "el";
        //字符串子串下标
        System.out.println(string1.indexOf(string2));
        //空白匹配，在一个段落中，以一个或者多个空格进行分割
        String [] arr = string1.split("\\s+");
    }
     // 关于栈的操作
    public void stack(){
        Stack stack = new Stack();
        //进栈
        stack.push("进栈");//返回的是入栈的内容
        stack.add("进栈");//返回的是true或false
        //出栈
        stack.pop();//输出并删除栈顶元素
        stack.peek();//输出不删除栈顶元素
        //栈大小
        stack.size();
        //栈是否为空
        stack.isEmpty();
    }
    //最小堆的实现
    public void heap(){
        int[] a = {45,36,18,53,72,30,48,93,15,35};
        //1，默认实现的是最小堆，元素按照natural ordering排序（自然排序，例如，数字的从小到大）
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for(int i=0;i<a.length;i++) {
            minHeap.offer(a[i]);
        }
        while(!minHeap.isEmpty()) {
            System.out.print(minHeap.poll()+" ");
        }
        System.out.println();
        //输出（升序）：15 18 30 35 36 45 48 53 72 93

        //初始化大小，最大堆
        int k=11;
        PriorityQueue<Integer> maxHeap = new PriorityQueue(k,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
    }
}
