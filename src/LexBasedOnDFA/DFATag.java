package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 词素的类型，分别有正整数、标识符、关键字（小写的if,else）、运算符（==,<,<=,>,>=,+）、赋值符号(=)
 */
public class DFATag {
    public final static int
        NUM = 256, ID = 257,
        IF = 258, ELSE = 259,
        ASSIGN = 260, ADD = 261,
        RELOP = 262;
}
