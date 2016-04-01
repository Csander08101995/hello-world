import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Саня on 01.04.2016.
 */
public class Laba {

    private static final String PATH_READ = "D://t1.txt";
    private static final String PATH_WRITE = "D://t2.txt";
    private static final String PATTERN_1 = "\\s{1,}";
    private static final String PATTERN_1_1 = "";
    private static final String PATTERN_2 = "@{2,}";
    private static final String PATTERN_2_2 = "@";
    private static final String PATTERN_3 = "\\.{2,}";
    private static final String PATTERN_3_3 = ".";
    private static final String PATTERN_4 = "-";
    private static final String PATTERN_4_4 = "";
    private static final String PATTERN_5 = "(\\d)(\\d{2,3})(\\d{3})(\\d{2})(\\d{2})";
    private static final String PATTERN_5_5 = "$1 ($2) $3-$4-$5";
    private static final String PATTERN_6 = "([A-Z])";
    private static final String PATTERN_6_6 = " $1";

    private static final String PATTERN_0 = "[a-z]{4,}";

    public static void main(){
        System.out.println("Hi");
        String str = getTex(PATH_READ);
        System.out.println(str);
        String res;
        res = changeText(str,PATTERN_1,PATTERN_2);
        System.out.println(res);
        writeText(PATH_WRITE,res);
    }
    private static String changeText(String str,String pat1,String pat2) {
        String res;
        res = str.replaceAll(PATTERN_1, PATTERN_1_1);
        String res1 = res.replaceAll(PATTERN_2, PATTERN_2_2);
        String res2 = res1.replaceAll(PATTERN_3, PATTERN_3_3);
        String res3 = res2.replaceAll(PATTERN_4,PATTERN_4_4);
        String res4 = res3.replaceAll(PATTERN_5,PATTERN_5_5);
        String res5 = res4.replaceAll(PATTERN_6,PATTERN_6_6);
        return res5;
    }
    private static String changeText1(String str,String pat1,String pat2){
        String res;
        res=str.replaceAll(pat1," ");
        Pattern pattern1 = Pattern.compile(pat2);
        Matcher mat1 = pattern1.matcher(res);
        String res1=null;
        String s;
        while (mat1.find()) {
            s = res.substring(mat1.start()-1,mat1.end());
            char[] chars = s.toCharArray();
            for(int i=0;i<chars.length-3;i=i+2){
                if((chars.length%2!=0)||((chars.length%2==0)&&(i<chars.length-4))) {
                    char t = chars[i + 2];
                    chars[i + 2] = chars[i + 3];
                    chars[i + 3] = t;
                }
            }
            String s1="";
            for(int i=0;i<chars.length;i++)
                s1+=chars[i];
            if(res1==null)
                res1 = res.replaceAll(s,s1);
            else
                res1 = res1.replaceAll(s,s1);
        }
        return res1;
    }



    private static void writeText(String path, String text){
        File file = new File(path);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try{
                out.println(text);
            }finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTex(String path){
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String s;
            while ((s = in.readLine()) != null){
                str.append(s + " \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}
