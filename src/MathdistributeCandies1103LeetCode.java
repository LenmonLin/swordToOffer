/**
 * 排排坐，分糖果。
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，
 * 依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），
 * 直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖
 * 果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情
 * 况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 * 示例 1：
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 * 示例 2：
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 * 提示：
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 * @author LemonLin
 * @Description :MathdistributeCandies1103LeetCode
 * @date 20.3.5-9:55
 * 思路：根据题意直白的翻译成代码即可。主要处理循环存放问题用i%num_people，还有
 * 一个处理剩下的candies的赋值问题，用一个if判断即可
 */
public class MathdistributeCandies1103LeetCode {
    public int[] distributeCandies(int candies, int num_people) {
        if (num_people<=0)return null;
        int [] result = new int[num_people];
        int i=0;
        int temp =1;
        while (candies>=0){
            if (candies<temp){
                result[i%num_people]+=candies;
                break;
            }else {
                result[i%num_people]+=temp;
            }
          candies-=temp;
          temp++;
          i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int candies =10;
        int num_people =3;
        int[] ints = new MathdistributeCandies1103LeetCode().
                distributeCandies(candies, num_people);
        for (int i=0;i<ints.length;i++){
            System.out.println(ints[i]);
        }

    }
}
