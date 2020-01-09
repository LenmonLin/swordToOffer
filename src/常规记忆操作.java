import java.util.*;

/**
 * @author LemonLin
 * @Description :常规记忆操作
 * @date 19.9.7-20:10
 */
public class 常规记忆操作 {
    public static void main(String[] args) {
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
        //   /号结果等于得到的整数（商的整数），%号结果等于余数
        System.out.println(13/5);
        //判断字符串A中是否有子串B可以用Java中的字符串的indexOf方法，如果返回的不是-1，就有包含。
        String string1 = "hello";
        String string2= "el";
        //字符串子串下标
        System.out.println(string1.indexOf(string2));
        //空白匹配，在一个段落中，以一个或者多个空格进行分割
        String [] arr = string1.split("\\s+");

        //hashmap 遍历key
        HashMap<String,Integer> hashMap = new HashMap();
        for (String test3:hashMap.keySet()){
            hashMap.get(test3);
        }

        //把hashmap中的value值以列表ArrayList的形式返回：
        Map<Integer, List<String>> map=new HashMap<>();
        List<List<String>> result = new ArrayList<>(map.values()) ;

        //stringbuilder转换为字符串
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.toString();
        //stringBuilder反转操作
        stringBuilder.reverse();

        //队列操作,取队头元素linkedList.peekFirst()，出队linkedList.pollFirst(),
        //这里add,remove方法是List接口的;offer，poll方法是Queue接口的
        //linkedList即是List也是Queue
        LinkedList linkedList = new LinkedList();
        linkedList.peekFirst();
        linkedList.pollFirst();
        linkedList.offer("进队");

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

        //对数组进行排序
        int[] numbers={12,21,124};
        Arrays.sort(numbers);
        for (int i=0;i<numbers.length;i++){
            System.out.println("numbers"+numbers[i]);
        }

        //这里用Math.ceil 进一法来取右边的数，比如0,1两个的平均数取1。
        int left=0;
        int right =1;
        int mid = (int)Math.ceil(((double) left+right)/2);
        System.out.println(mid);
    }


}
