package LexBasedOnDFA;

import java.util.ArrayList;

/**
 * Created by zhoubin on 2017/1/6.
 */
public class runLexer {
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
            for(int i = 0; i < tokens.size();i++){
                System.out.println(tokens.get(i));
            }
        }

    }
}
