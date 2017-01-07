package LexicalAnalyzer;

import java.util.ArrayList;

/**
 * Created by zhoubin on 2016/12/6.
 */
public class test {
    public static void main(String[] args){
        ArrayList<Token> tokens = new ArrayList<>();
        //用户输入一行串，将串保存在一个字符串中
        Input.userInput();
        System.out.println(Input.string);
        //调用Lexer中的scan()，识别下一个token
        Lexer lexer = new Lexer();
        while(Input.pos<Input.string.length()){
            tokens.add(lexer.scan());
        }
        //输出词法单元序列
        for(int i = 0; i < tokens.size();i++){
            System.out.println(tokens.get(i));
        }

    }
}
