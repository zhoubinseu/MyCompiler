package SyntaxParser;

/**
 * 上下文无关文法的产生式类
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
