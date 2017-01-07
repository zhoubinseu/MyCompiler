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
            tokens.add(dfaLexer.scan());
        }

        for(int i=0;i<tokens.size();i++){
            System.out.println(tokens.get(i));
        }

    }
}
