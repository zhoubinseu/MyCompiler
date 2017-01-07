package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 */
public class DFAOperator extends DFAToken{
    public final String value;
    public DFAOperator(int t, String v) {
        super(t);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
