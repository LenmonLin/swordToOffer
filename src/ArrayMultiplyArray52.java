/**
 * @author LemonLin
 * @Description :MultiplyArray52
 * @date 2019/3/5-10:43
 *
 * 题目描述
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中
 * B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 *
 * 解题思路：
 * 首先理解题意：B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]可见右边少了个A[i];
 *
 * 可以把B[i]看成是A[0]*A[1]*...*A[i-1]和A[i-1]*A[i+1]*...*A[n-1]两部分的乘积，因此可以画图如下：
 *  b[0]  1,a[1],a[2],a[3]
 *  b[1]  a[0],1,a[2],a[3]
 *  b[2]  a[0],a[1],1,a[3]
 *  b[3]  a[0],a[1],a[2],1
 *
 *  先计算下三角的每行乘积，再计算上三角的每行乘积，两者相乘，即是所求。
 */
public class ArrayMultiplyArray52 {
    public int[] multiply(int[] A) {

        int[] C= new int[A.length];
        C[0]=1;

        //先计算下三角的乘积
        for (int i=1;i<A.length;i++){
            C[i] = C[i-1]*A[i-1];
        }

        //计算上三角的乘积
        int[] D = new int[A.length];
        D[A.length-1]=1;
        for(int j= A.length-2;j>=0;j--){
            D[j] = D[j+1]*A[j+1];
        }

        //上三角和下三角相乘
        int[] B = new int[A.length];
        for (int k= 0;k<A.length;k++){
            B[k]=C[k]*D[k];
        }

        return B;
    }

    public static void main(String[] args) {
        ArrayMultiplyArray52 arrayMultiplyArray52 = new ArrayMultiplyArray52();
        int [] A={1,2,3};
        int[] B = arrayMultiplyArray52.multiply(A);

        for (int i= 0;i<B.length;i++){
            System.out.println(B[i]);
        }

    }
}
