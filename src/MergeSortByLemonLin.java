/**
 * @author LemonLin
 * @Description :MergeSortByLemonLin
 * @date 2018/4/7-16:49
 */
public class MergeSortByLemonLin {

    public void MergeSort(int [] arra){
        MSort(arra,arra,1,arra.length);
    }
    public void MSort(int [] arra1,int [] arra2,int Start,int End){
        int mid=0;
        int []arra3= new int[End-Start+1];
        if (Start==End){
            arra2[Start]=arra1[Start];
        }
        else {
            mid=(Start+End)/2;
            MSort(arra1,arra3,Start,mid);
            MSort(arra1,arra3,mid+1,End);
            MergeS(arra1,arra3,Start,mid,End);
        }
    }
    public void MergeS(int [] arra1,int[] arra3,int  start,int mid,int end){
        int arra3Start,newArrayStart,tempCount;
        for (arra3Start=mid+1,newArrayStart=start;start<=mid&&arra3Start<=end;newArrayStart++){
            if (arra1[start]<arra1[newArrayStart]){
                arra3[newArrayStart]=arra1[start++];
            }else {
                arra3[newArrayStart]=arra1[arra3Start++];
            }
        }
    }
}
