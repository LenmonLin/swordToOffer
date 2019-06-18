/**
 * @author LemonLin
 * @Description :StringStudentAttendanceRecordI
 * @date 19.6.17-22:59
 * You are given a string representing an attendance record for a student. The record only
 * contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A'
 * (absent) or more than two continuous 'L' (late).
 * You need to return whether the student could be rewarded according to his attendance record.
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
 * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
 * 思路：比较简单，用hashmap的key可以直接记录字母，value记录出现的次数
 * 或者用两个变量来记录：A,L的出现次数即可
 */
public class StringStudentAttendanceRecordI {
    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int numA=0;
        boolean flag = false;
        for (int i=0;i<chars.length;i++){
            if (chars[i]=='A'){
                numA++;
            }
            if ((i+2)<chars.length&&chars[i]=='L'&&chars[i+1]=='L'&&chars[i+2]=='L'){
                flag =true;
            }
        }
        if (numA<=1&&flag==false){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s="PPALLL";
        System.out.println(new StringStudentAttendanceRecordI().checkRecord(s));
    }
}
