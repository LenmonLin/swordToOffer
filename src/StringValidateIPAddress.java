/**
 * @author LemonLin
 * @Description :StringValidateIPAddress
 * @date 19.6.11-0:00
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal
 * numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing
 * 16 bits. The groups are separated by colons (":"). For example, the address
 * 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading
 * zeros among four hexadecimal digits and some low-case characters in the address to upper-case
 * ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and
 * using upper cases).However, we don't replace a consecutive group of zero value with a single
 * empty group using two consecutive colons (::) to pursue simplicity. For example,
 * 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address. Besides, extra leading zeros
 * in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334
 * is invalid.
 *
 * Note: You may assume there is no extra space or special characters in the input string.
 * Example 1:
 * Input: "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 * Input: "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
 * IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，
 * 172.16.254.1； 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
 * IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  
 * 2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头
 * 的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有
 * 效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。然而，我们不能因为某个组的值为 0，而使用一个
 * 空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 * 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334
 * 是无效的。
 * 思路：基本就是取出来每个小段，然后进行条件的判断，无脑堆条件就OK了。
 * 首先对于IPv4，我们使用split正则表达式来截取两个点之间的字符串，我们还需要一个计数器cnt来记录我们已经
 * 截取了多少段，如果cnt大于4了，说明超过了4段，说明是不是正确的地址。如果取出的字符串为空，说明两个
 * 点连在一起了，也不对。再有就是如果字符串长度大于1，且第一个字符是0，也不对。由于IPv4的地址在0到255
 * 之间，所以如果字符串长度大于3，也不正确。下面我们检查每一个字符，如果有不是数字的字符，返回Neither。
 * 最后我们再把字符串转为数字，如果不在0到255之间就是非法的。最后的最后，我们要保证cnt正好为4，而且
 * 最后一个字符不能是点，统统满足以上条件才是正确的IPv4地址。
 *
 * 然后对于IPv6，我们也使用split正则表达式来截取两个冒号之间的字符串，我们同样需要计数器cnt来记录我们已经
 * 截取了多少段，如果cnt大于8了，说明超过了8段，说明是不是正确的地址。如果取出的字符串为空，说明两个
 * 冒号连在一起了，也不对。面我们检查每一个字符，正确的字符应该是0到9之间的数字，或者a到f，或A到F之间
 * 的字符，如果出现了其他字符，返回Neither。还要考虑单个字段长度大于5，最后的最后，我们要保证cnt正好为8，
 * 而且最后一个字符不能是冒号，统统满足以上条件才是正确的IPv6地址。
 */
public class StringValidateIPAddress {
    public String validIPAddress(String IP) {
        //排除空格输入
        if (IP.trim().equals(""))return "Neither";
        //排除最后一个字符是：或者.
        if (IP.substring(IP.length()-1,IP.length()).equals(":")||
                IP.substring(IP.length()-1,IP.length()).equals("."))return "Neither";
        String[] strings = IP.split("\\.");
        String[] stringsIPv6 = IP.split("\\:");
        if (strings.length == 4 && stringsIPv6.length != 8) {
            for (String s : strings) {
                if (s.length() > 3) return "Neither";
                if (s.length()==0) return "Neither";
                char[] chars = s.toCharArray();
                if (chars.length > 1 && chars[0] == '0') return "Neither";
                for (char c : chars) {
                    if (c - '0' < 0 || '9' - c < 0) return "Neither";

                }
                int change = Integer.parseInt(s);
                if (change < 0 || change > 255) return "Neither";
            }
            return "IPv4";
        } else if (strings.length != 4 && stringsIPv6.length == 8) {
            int j=1;
            for (int i=0;i< stringsIPv6.length;i++) {
                if (stringsIPv6[i].length() > 4) return "Neither";
                if (stringsIPv6[i].length()==0) return "Neither";
                char[] chars = stringsIPv6[i].toCharArray();
                for (int k=0;k< chars.length;k++) {
                    if ((chars[k] - '0' >=0 && '9' - chars[k] >= 0) || (chars[k] >='a' &&
                            chars[k] <= 'f' )|| (chars[k] >= 'A' && chars[k] <= 'F')){
                        continue;
                    }else {
                        return "Neither";
                    }
                }
            }
            return "IPv6";
        }
        return "Neither";
    }
    public static void main(String[] args) {
        String s ="";
        if (s.trim().equals("")) System.out.println("hh");;
        if (s.substring(s.length()-1,s.length()).equals(":")) System.out.println("hello");
        //System.out.println(new StringValidateIPAddress().validIPAddress(s));
    }
}
