package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 表示整数
 */
public class DFANum extends DFAToken{
    public final int value;
    public DFANum(int v) {
        super(DFATag.NUM);
        value = v;
    }

    @Override
    public String toString() {
        return "["+super.tag+","+value+"]";
    }
}
