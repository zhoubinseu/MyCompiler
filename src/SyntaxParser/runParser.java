package SyntaxParser;

import LexBasedOnDFA.DFALexer;
import LexBasedOnDFA.DFAToken;
import LexBasedOnDFA.LexInput;

import java.util.ArrayList;

/**
 * Created by zhoubin on 2017/1/14.
 */
public class runParser {
    public static void main(String[] args){
        ArrayList<DFAToken> tokens = new ArrayList<>();
        LexInput.userInput();
        System.out.println(LexInput.string);
        DFALexer dfaLexer = new DFALexer();
        while (LexInput.pos<LexInput.string.length()){
            DFAToken token = dfaLexer.scan();
            if(token!=null){
                tokens.add(token);
            }
        }
        //输出词法单元序列
        if(tokens.size() == 0){
            System.out.println("Invalid input");
        }else {
            System.out.println("tokens:-----------------------");
            for(int i = 0; i < tokens.size();i++){
                System.out.println(tokens.get(i));
            }
        }
        System.out.println("top-down:-----------------------");
        Parser parser = new Parser();
        parser.parse(tokens);

    }
}
