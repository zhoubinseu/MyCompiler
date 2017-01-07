package LexicalAnalyzer;

/**
 * Created by zhoubin on 2016/12/20.
 */
public class Operator extends Token{
    public final String value;
    public Operator(int t, String v) {
        super(t);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
