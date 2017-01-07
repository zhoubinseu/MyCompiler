package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 表示标识符或关键字
 */
public class DFAWord extends DFAToken{
    public final String value;
    public DFAWord(int t, String v) {
        super(t);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
