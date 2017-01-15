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
        //先执行词法分析
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
        }else {//输出token序列
            System.out.println("------------tokens------------");
            for(int i = 0; i < tokens.size();i++){
                System.out.println(tokens.get(i));
            }
        }
        //输出自上而下的递推序列
        System.out.println("------------top-down derivations------------");
        Parser parser = new Parser();
        parser.parse(tokens);

    }
}
