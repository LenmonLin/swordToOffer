/**
 * @author LemonLin
 * @Description :Probability43
 * @date 2018/8/28-11:10
 *
 * 题目：n个骰子点数和及各自出现的概率
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为S。输入n，打印出S的所有可能的值出现的概率。
 *
 * 解题思路：使用动态规划的解决方式：
 * 1、确定问题的解的表达式：
 * 2、将最终问题的构造成上一阶段问题的解，即递推公式
 *
 * 第一步：确定问题解的表达式：  种类数 f(diceNumber,sum) ：diceNumber表示为输入的骰子数，sum表示点数之和
 *
 * 第二步：确定状态转移方程，包括初始状态。
 * n个骰子点数和为s的种类数只与n-1个骰子的和有关，因为一个骰子有六个点数，当第n个骰子点数为1的话，那么
 * f(diceNumber,sum)=f(diceNumber-1,sum-1);当第n-1个骰子点数为2的话，那么f(diceNumber,sum)=f(diceNumber-1,sum-2)
 * 以此内推；所以f(diceNumber,sum)=f(diceNumber-1,sum-1)+f(diceNumber-1,sum-2)+f(diceNumber-1,sum-3)+
 * f(diceNumber-1,sum-4)+f(diceNumber-1,sum-5)+f(diceNumber-1,sum-6);
 *
 *初始状态下f(1,1)=f(1,2)=f(1,3)=f(1,4)=f(1,5)=f(1,6)=1
 */
public class Probability43 {

    /**
     *@Description
     *@params  diceNumber表示为输入的骰子数，sum表示点数之和,返回结果为种类数
     *@author LemonLin
     *@date  2018/9/1
     */
    public int getSumCount(Integer diceNumber,Integer sum){

        //判断异常输入
        if (diceNumber<1||sum<diceNumber||sum>diceNumber*6){
            return 0;
        }

        if (diceNumber==1){
            return 1;
        }
        Integer sumCount =0;
        sumCount=getSumCount(diceNumber-1,sum-1)+getSumCount(diceNumber-1,sum-2)
                +getSumCount(diceNumber-1,sum-3)+getSumCount(diceNumber-1,sum-4)
                +getSumCount(diceNumber-1,sum-5)+getSumCount(diceNumber-1,sum-6);
        return sumCount;
    }


    public static void main(String[] args) {

        /**
         * 骰子的个数5
         */
        int Count=2;
        Probability43 probability43 = new Probability43();
        for (int n=6*Count;n>=2;n--) {
            System.out.println("骰子数为"+Count+"===="+"和为"+n+"的个数为"+
                    probability43.getSumCount(Count, n));
        }

    }
}
