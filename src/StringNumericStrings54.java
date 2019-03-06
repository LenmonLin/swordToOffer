public class StringNumericStrings54 {

    /**
     *
     * 题目描述
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123",
     * "3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     *
     * 解题思路：
     * 从测试用例来看，表示的字符串有三类：整数、浮点数和科学计数法表示的数值。
     * 那么基本思路就是根绝这三种类型进行判断：对于整数比较容易判断，只要字符串没有出现非0到9的字符，
     * 就可以跳过，否则返回错误；对于浮点数，因为可能会出现多个小数点的情况，所以如果展开判断会很复杂，
     * 只要考虑是正确的情况就可以——即只出现一个小数点的情况，至于小数点后面的字符串则与判断整数是一样得思路；
     * 对于科学计数法表示的数值，无非就两类一种是e一种是E，至于e或者E后面的字符串的判断也是与整数的判断是一样的。
     * 而且，注意到，如果e或者E后面没有数字的话是不被允许的。
     */

    public boolean isNumeric(char[] str) {
            if(str == null || str.length == 0) return false;
            //首先如果两边有空格的话需要先去掉空格
            int index = 0;
            int length = str.length;
            while(index < length && str[index] == ' ') index++;
            if(index >= length) return false;
            while(str[length - 1] == ' ') length--;
            //第一步：判断是否是一个整数
            //如果第一位是+或者-号的时候
            if(str[index] == '+' || str[index] == '-') index++;
            if(index >= length) return false;
            //如果后面是数字的话就跳过
            while(index < length && str[index] >= '0' && str[index] <= '9') index++;
            if(index == length) return true;
            int index2 = index;
            //第二步：判断是否还有小数部分
            if(str[index] == '.'){
                index++;
                if(index == length) return true;
                index2 = index;
                while(index < length && str[index] >= '0' && str[index] <= '9') index++;
                if(index == index2) return false;
                if(index == length) return true;
            }

            //第三步：判断是否有科学计数法
            if(str[index] == 'e' || str[index] == 'E'){
                index++;
                if(index == length) return false;
                if(str[index] == '+' || str[index] == '-') index++;
                index2 = index;
                while(index < length && str[index] >= '0' && str[index] <= '9') index++;
                if(index == index2) return false;
                if(index == length) return true;
            }
            return false;
        }

        public static void main(String[] args) {
            String s = "1.2.3";
            boolean b = new StringNumericStrings54().isNumeric(s.toCharArray());
            System.out.println(b);
        }
}
