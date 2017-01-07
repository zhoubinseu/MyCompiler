package LexicalAnalyzer;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 表示整数
 */
public class Num extends Token{
    public final int value;
    public Num(int v) {
        super(Tag.NUM);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
