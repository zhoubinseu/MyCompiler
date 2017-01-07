package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 */
public class DFAToken {
    public final int tag;
    public DFAToken(int t){
        tag = t;
    }

    @Override
    public String toString() {
        return "["+tag+"]";
    }
}
