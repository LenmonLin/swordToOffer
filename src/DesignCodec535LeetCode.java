import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/prob
 * lems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 * 要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。你的加密和解密算法
 * 如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且
 * 这个TinyURL可以用解密方法恢复成原本的URL。
 * @author LemonLin
 * @Description :DesignCodec535LeetCode
 * @date 20.5.15-15:49
 * 思路：参考评论：JAVA实现： tinyurl 格式： http://tinyurl.com/ + 6位随机码(4e9iAK)
 * 使用哈希表map, 加密的时候生成随机 tinyurl, 若 tinyurl 在 map中不存在, 则以 tinyurl
 * 作为 key, url 作为value; 解密时, 通过 tinyurl 作为 key, 即可找到作为 value 的 url.
 */
public class DesignCodec535LeetCode {
    Map<String, String> map = new HashMap<String, String>();
    static final String INDEX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String TINYURL_PREFIX = "http://tinyurl.com/";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        char[] chs = new char[6];
        while(true){
            for(int i = 0; i < 6; i++){
                chs[i] = INDEX.charAt((int)(Math.random()*62));
            }
            String shortUrl = TINYURL_PREFIX + new String(chs);
            if(!map.containsKey(shortUrl)){
                map.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}
