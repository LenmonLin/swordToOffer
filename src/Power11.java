/**
 * 主要考虑的是怎么处理0和负指数的问题，常规的正指数比较简单
 * 计算机内表示小数时会有误差，判断两个小数是否相等，只能判断它们之差的绝对值是不是在一个很小的范围内
 * @author LemonLin
 * @Description :Power11
 * @date 2018/2/25-19:57
 */
public class Power11 {
    public double Power(double base, int exponent) {

        if (equal(base,0.0)&&exponent <0){
            return 0.0;
        }

        //负数的情况要求导
        double result =0.0;
        int temp = exponent;
        if (exponent<0){
            temp = -exponent;
        }
        result =PowerWithUnsignedExponent(base,temp);
        if (exponent<0){
            result =1.0/result;
        }
        return  result;


    }


//    指数是正数的时候，正常思路
    public double PowerWithUnsignedExponent(double base,int exponent){

        double result = 1.0;

        for (int i =1;i<=exponent;++i){
            result *= base;
        }
        return result;
    }
//判断两个小数是否相等
    public boolean equal(double num1,double num2){
        if ((num1-num2)<0.00000001&&(num1-num2)>-0.0000000001){
            return true;
        }else
            return  false;
    }

    //测试代码
    public static void main(String[] args) {
        double num1=2.0;
        int num2=-2;
        Power11 power11 = new Power11();
        double result=power11.Power(num1,num2);
        System.out.println(result);
    }
}
