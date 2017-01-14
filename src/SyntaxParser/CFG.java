package SyntaxParser;

/**
 * Created by zhoubin on 2017/1/14.
 */
public class CFG {
    public String left;
    public String right;
    public CFG(String l, String r){
        left = l;
        right = r;
    }

    @Override
    public String toString() {
        return left+" --> "+right;
    }
}
