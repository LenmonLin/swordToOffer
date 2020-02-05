/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 * @author LemonLin
 * @Description :BinarySearchsearchMatrix74LeetCode
 * @date 20.2.5-11:34
 * 思路:先对行进行二分查找，找出target可能存在哪一行，再对具体行进行二分查找，
 * 找出具体在哪一个位置。
 * bug1:
 * []
 * 0
 * java.lang.ArrayIndexOutOfBoundsException: 0
 * bug2:
 * [[]]
 * 1
 * java.lang.ArrayIndexOutOfBoundsException: 0
 */
public class BinarySearchsearchMatrix74LeetCode {
    public boolean searchMatrix(int[][] matrix, int target) {
        //解决bug1和bug2
        if (matrix==null||matrix.length==0||matrix[0].length==0)return false;
        int start =0;
        int end=matrix.length-1;
        int mid =0;
        while (start<=end){
            //与(start+end)/2写法对比，以下这样写可以防止start+end大数越界
            mid=start+(end-start)/2;
            if (matrix[mid][0]==target){
                return true;
            }else if (matrix[mid][0]<target){
                start = mid+1;
            }else if (matrix[mid][0]>target){
                end=mid-1;
            }
        }
        //不知道在哪一行，懒的想具体了。直接附近两行都找一下
        boolean midOne =false;
        boolean midTwo = false;
        boolean midThree = false;
        if (mid-1>=0){
            midOne=searchHelper(matrix[mid-1],target);
        }
        midTwo=searchHelper(matrix[mid],target);
        if (mid+1<=end){
            midThree =  searchHelper(matrix[mid+1],target);
        }
        return midOne||midTwo||midThree;
    }

    //在某一具体行查找数
    private boolean searchHelper(int [] nums,int target){
        int left =0;
        int right =nums.length-1;
        int mid =0;
        while (left<=right){
            mid = left+(right-left)/2;
            if (nums[mid]==target){
                return true;
            }else if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right=mid-1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [][] matrix ={
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target =13;
        System.out.println(new BinarySearchsearchMatrix74LeetCode().
                searchMatrix(matrix, target));
    }
}
