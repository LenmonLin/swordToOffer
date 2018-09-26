import java.util.ArrayList;
import java.util.List;

/**
 *难点：1、数字转换为string，直接i+"";java中会自动转换
 * 2、i%3==0&&i%5==0这个条件要放在(i%3==0的前面
 *
 * @author LemonLin
 * @Description :FizzBuzz918
 * @date 2018/3/1-21:28
 */
public class FizzBuzz918 {
    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public List<String> fizzBuzz(int n) {
        // write your code here
        List<String> fizzBuzz =new ArrayList<String>();
        for (int i=1;i<=n;++i){
            if (i%3==0&&i%5==0){
                fizzBuzz.add("fizz buzz");
            }else if (i%5==0){
                fizzBuzz.add("buzz");

            }else if (i%3==0){
                fizzBuzz.add("fizz");
            }else {
                fizzBuzz.add(i+"");
            }
        }
        return fizzBuzz;
    }

    //测试代码
    public static void main(String[] args) {
        FizzBuzz918 fizzBuzz918 = new FizzBuzz918();
        int n=15;
        List<String> test1 = fizzBuzz918.fizzBuzz(n);
        for (Object list:test1){
            System.out.println(list);
        }
    }
}
