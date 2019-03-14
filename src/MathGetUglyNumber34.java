import java.util.ArrayList;

/**
 * @author LemonLin
 * @Description :MathGetUglyNumber34
 * @date 2019/3/14-15:52
 * 题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质
 * 因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * 解题思路：暴力算法，从1开始遍历所有的数，每次都判断是不是丑数，这种解法作的比较多的无用运算是不是
 * 丑数的也进行了判断。
 * 有一种方法只记录丑数，然后按顺序选择。

 * 通俗易懂的解释：
 * 首先从丑数的定义我们知道，一个丑数的因子只有2,3,5，那么丑数p = 2 ^ x * 3 ^ y * 5 ^ z，换句话
 * 说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，那么我们从1开始乘以2,3,5，就得到2,3,5
 * 三个丑数，在从这三个丑数出发乘以2,3,5就得到4，6,10,6，9,15,10,15,25九个丑数，我们发现这种方法
 * 会得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。那么我们可以维护
 * 三个队列：
 * （1）丑数数组： 1
 * 乘以2的队列：2
 * 乘以3的队列：3
 * 乘以5的队列：5
 * 选择三个队列头最小的数2加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
 * （2）丑数数组：1,2
 * 乘以2的队列：4
 * 乘以3的队列：3，6
 * 乘以5的队列：5，10
 * 选择三个队列头最小的数3加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
 * （3）丑数数组：1,2,3
 * 乘以2的队列：4,6
 * 乘以3的队列：6,9
 * 乘以5的队列：5,10,15
 * 选择三个队列头里最小的数4加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
 * （4）丑数数组：1,2,3,4
 * 乘以2的队列：6，8
 * 乘以3的队列：6,9,12
 * 乘以5的队列：5,10,15,20
 * 选择三个队列头里最小的数5加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
 * （5）丑数数组：1,2,3,4,5
 * 乘以2的队列：6,8,10，
 * 乘以3的队列：6,9,12,15
 * 乘以5的队列：10,15,20,25
 * 选择三个队列头里最小的数6加入丑数数组，但我们发现，有两个队列头都为6，所以我们弹出两个队列头，
 * 同时将12,18,30放入三个队列；
 * 实现思路：
 * 我们没有必要维护三个队列，只需要记录三个指针显示到达哪一步；
 * 这里用到了求最小值的函数，同时需要比较下标。
 */
public class MathGetUglyNumber34 {

    public int GetUglyNumber_Solution(int index) {
        //index是表示求第几个丑数
        if (index<=0){
            return 0;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        //数组坐标为0的丑数是1
        arrayList.add(1);
        //分别用i2，i3,i5来记录三个队列的下标
        int i2=0,i3=0,i5=0;
        while (arrayList.size()<index){
            int ugly2=arrayList.get(i2)*2;
            int ugly3 = arrayList.get(i3)*3;
            int ugly5 = arrayList.get(i5)*5;
            //求这三个数最小的
            int min = Math.min(ugly2,Math.min(ugly3,ugly5));
            arrayList.add(min);
            if (min == ugly2)i2++;
            if (min == ugly3)i3++;
            if (min == ugly5)i5++;
        }
        return arrayList.get(index-1);
    }

    public static void main(String[] args) {
        MathGetUglyNumber34 mathGetUglyNumber34 = new MathGetUglyNumber34();
        int result = mathGetUglyNumber34.GetUglyNumber_Solution(4);
        System.out.println(result);
    }
}
