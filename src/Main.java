/**
 * @author LemonLin
 * @Description :Main
 * @date 2018/4/3-15:20
 */
import java.io.*;
import java.util.*;
public class Main
{
    public static void main(String args[]){

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);

        arrayList.remove(arrayList.size()-1);
        for (Object a:arrayList){
            System.out.println(a);
        }
    }

}
