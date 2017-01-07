package LexicalAnalyzer;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 表示标识符或关键字
 */
public class Word extends Token{
    public final String value;
    public Word(int t, String v) {
        super(t);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
