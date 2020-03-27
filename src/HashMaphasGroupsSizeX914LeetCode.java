/**
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * 示例 1：
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 * 提示：
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 * @author LemonLin
 * @Description :HashMaphasGroupsSizeX914LeetCode
 * @date 20.3.27-19:22
 * 思路：第一次，遍历数组，用hashmap或者数组，记录每个数字出现的次数，
 * 第二遍：记录出现次数最小的次数。
 * 第三遍遍历，如果所有的出现的次数都能被最小的次数整除，返回true，否则返回false。
 * 纠正：不能光除以最小的数，应该是求所有的数的最大公约数，这里用除以所有最小次数
 * 以内的数来处理。题解大多数用求多个数的最大公约数解决：
 * gcd(a,b,c) = gcd(gcd(a,b),c)
 * bug1:
 * 输入:
 * [1]
 * 输出
 * true
 * 预期结果
 * false
 */
public class HashMaphasGroupsSizeX914LeetCode {
    public boolean hasGroupsSizeX(int[] deck) {
        //特殊情况的bug1
        if (deck.length==1)return false;
        //这里用数组存出现的次数。
        int [] hashmap = new int[10001];
        //记录出现的最小的次数。
        int min=Integer.MAX_VALUE;
        //第一次遍历记录数字出现的次数
        int count = 0;
        for (int i =0;i<deck.length;i++){
            hashmap[deck[i]]++;
        }
        //第二次遍历记录非0的数字，以及最小的次数
        for (int i=0;i<deck.length;i++){
            if (hashmap[deck[i]]!=0){
                count++;
            }
            if (hashmap[deck[i]]<min){
                min = hashmap[deck[i]];
            }
        }
        int tempCount =count;
        //第三次遍历看是否能整除，以最小的次数为范围，是否所有的数能整除最小的数以内的数。
        for (int j=2;j<=min;j++){
            for (int i=0;i<deck.length;i++){
                if (hashmap[deck[i]]!=0&&hashmap[deck[i]]%j==0){
                        tempCount--;
                }
            }
            if (tempCount==0){
                return true;
            }
            tempCount = count;
        }
        return false;
    }

    public static void main(String[] args) {
        int [] deck ={1,1,1,1,2,2,2,2,2,2};
        System.out.println(new HashMaphasGroupsSizeX914LeetCode().hasGroupsSizeX(deck));
    }
}
