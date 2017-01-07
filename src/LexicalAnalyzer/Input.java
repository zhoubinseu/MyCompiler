package LexicalAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhoubin on 2016/12/24.
 */
public class Input {
    public static int pos = 0;
    public static String string;
    public static String userInput(){
        System.out.println("please input a character stream:");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String str="";
        try {
            str = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        string = str;
        return str;
    }
}
