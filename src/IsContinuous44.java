import java.util.Arrays;

/**
 * @author LemonLin
 * @Description :IsContinuous44
 * @date 2018/9/3-20:25
 *
 *
 * 题目：扑克牌顺子
 *
 * 题目：随机抽取扑克牌中的5张牌，判断是不是顺子，即这5张牌是不是连续的。
 * 其中A看成1，J看成11，Q看成12，K看成13，大小王可以看成任何需要的数字。
 *
 * 解题思路：
 * 其实就是判断取出的数是不是连续数组；
 * 步骤：
 * 1、对取出的数据进行排序：用Arrays.sort
 * 2、计算数据中包含的0的个数，0的个数可以替换非顺序的空白
 * 3、计算排好序的相邻数据之间的空缺总数，和2对比，0总数多，就算连续数组，否则不算
 * 4、注意异常情况，如果有非0数字重复出现，则该数组不是连续的，即有对子不能为顺子
 */
public class IsContinuous44 {

    public boolean isContinuous(int [] numbers) {
        Arrays.sort(numbers);

        /**
        测试
        for (int i=0;i<numbers.length;i++){
        System.out.println(numbers[i]);
        }
        */

        if (numbers.length==0){
            return false;
        }

        //统计0的个数
        int  numberOfZero =0;

        int blankOfEachOther =0;
        for (int i=0;i<numbers.length;i++){
            if (numbers[i]==0){
                numberOfZero++;
            }

            //统计空格数
            if (i!=numbers.length-1&&numbers[i]!=0) {
                //如果出现对子，则不可能是顺子
                if (numbers[i+1]==numbers[i]){
                    return false;
                }
                blankOfEachOther+=numbers[i+1]-numbers[i]-1;
            }
        }
        if (blankOfEachOther<=numberOfZero){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        IsContinuous44 isContinuous44 = new IsContinuous44();
        int[]test ={1,3,2,6,4};
        isContinuous44.isContinuous(test);
    }
}
