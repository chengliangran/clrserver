import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-02-08.
 */
public class test {
    public static void main(String[] args) {
         try {
            FileInputStream fis=new FileInputStream("d://test/record.txt");
             System.out.println(System.getProperty("user.dir"));
             System.out.println(fis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static  int getBytes(String input){
        char[] chars= input.toCharArray();

        String s="0123456789";
        System.out.println(s.substring(0));
        return chars.length;
    }
}
