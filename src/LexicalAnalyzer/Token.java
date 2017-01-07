package LexicalAnalyzer;

/**
 * Created by zhoubin on 2016/12/20.
 */
public class Token {
    public final int tag;
    public Token(int t){
        tag = t;
    }

    @Override
    public String toString() {
        return "["+tag+"]";
    }
}
