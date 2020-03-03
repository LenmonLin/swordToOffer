/**
* 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，
 * 将 B 合并入 A 并排序。
* 初始化 A 和 B 的元素数量分别为 m 和 n。
* 示例:
* 输入:
* A = [1,2,3,0,0,0], m = 3
* B = [2,5,6],       n = 3
* 输出: [1,2,2,3,5,6]
 * @author LemonLin
 * @Description :面试题1001合并排序的数组
 * @date 20.3.3-11:14
 * 思路：多用一个数组C[],空间换时间，然后如果A<B,就把A当前元素放入C，否则放入B
 * 当前元素,以此类推。如果一种一个放完了，把另一个还没放到C中的全部放到C末尾，最
 * 后把C复制到A中即可。
 */
public class 面试题1001合并排序的数组 {
    public void merge(int[] A, int m, int[] B, int n) {
        int [] C =new int[m+n];
        int i=0;
        int j=0;
        int k=0;
        while (i<m&&j<n){
            if (A[i]<B[j]){
                C[k] = A[i];
                k++;
                i++;
            }else {
                C[k] = B[j];
                k++;
                j++;
            }
        }
        while (i<m){
            C[k]=A[i];
            k++;
            i++;
        }
        while (j<n){
            C[k]=B[j];
            k++;
            j++;
        }
        for (int p=0;p<m+n;p++){
            A[p] = C[p];
        }
    }

    public static void main(String[] args) {
        int[] A ={1,2,3,0,0,0};
        int[] B ={4,5,6};
        int m =3;
        int n =3;
        new 面试题1001合并排序的数组().merge(A,m,B,n);
        for (int i =0;i<A.length;i++){
            System.out.println(A[i]);
        }
    }
}
