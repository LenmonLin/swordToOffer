/**
 *
 * 题目描述
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个
 * 旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 解题思路：
 * 1、考虑用二分法的两个指针的方法：时间复杂度比用顺序遍历法的好，边界条件是两个指针相邻了
 * 2、考虑特殊情况下是旋转数组本来就是全递增的情况
 * 3、考虑到10111这种情况，只能用顺序遍历法了，因为两个指针的所指向的数都相等无法比较大小
 * @author LemonLin
 * @Description :MinNumberInRotatedArray08
 * @date 2018/2/24-21:14
 */
public class ArrayMinNumberInRotatedArray08 {


    public int minNumberInRotateArray(int [] array) {
        int index1=0;
        //注意这里数组长度比数组最大下标多一个一
        int index2=array.length-1;
        int minIndex =index1;

// 如果数组中第一个数字小于最后一个数字，表明该数组时排序的
        while (array[index1]>=array[index2]){
            //退出循环的条件
            if (index2-index1 == 1){
//             不直接在结果返回index2,再用minIndex过渡一下，是考虑本身就是顺序数组的情况下，得返回index1的情况
                minIndex = index2;
                break;
            }
            minIndex = (index1+index2)/2;
            //如果下标为index1,index2,indexMid指向的三个数相等，
//            则只能顺序查找
            if (array[index1]==array[index2]&&array[minIndex]==array[index1]){
                return MinInOrder(array,index1,index2);
            }
            //这里可以加<=
            if (array[index1]<=array[minIndex]){
                index1=minIndex;
            }else if (array[index2]>=array[minIndex]){
                index2=minIndex;
            }
        }
        return array[minIndex];
    }


    //考虑10111情况下只能顺序查找
    public int MinInOrder(int[] array,int index1,int index2){
        int min=array[index1];
        for (int i=index1;i<array.length;++i){
            if (array[i]<min){
                min = array[i];
            }
        }
        return min;
    }

    //测试代码

    public static void main(String[] args) {
        int[] test1 ={3,4,5,1,2};
        int[] test2 ={1,0,1,1,1};
        int[] test3 ={1,2,3,4,5};

        ArrayMinNumberInRotatedArray08 arrayMinNumberInRotatedArray08 = new ArrayMinNumberInRotatedArray08();
        int minOut = arrayMinNumberInRotatedArray08.minNumberInRotateArray(test3);
        System.out.println(minOut);
    }
}
