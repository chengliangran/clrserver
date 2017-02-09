import java.io.InputStream;

/**
 * Created by Administrator on 2017-02-08.
 */
public class test {
    public static void main(String[] args) {
        getBytes("sad");

     }
    public static  int getBytes(String input){
        char[] chars= input.toCharArray();

        String s="0123456789";
        System.out.println(s.substring(0));
        return chars.length;
    }
}
