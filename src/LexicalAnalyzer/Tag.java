package LexicalAnalyzer;

/**
 * Created by zhoubin on 2016/12/20.
 *
 * 词素的类型，分别有正整数、标识符、关键字（小写的if,else）、运算符（==,<,<=,>,>=,+）、赋值符号(=)
 */
public class Tag {
    public final static int
        NUM = 256, ID = 257,
        IF = 258, ELSE = 259,
        ASSIGN = 260, EQ = 261,
        LT = 262, LE = 263,
        GT = 264, GE = 265,
        ADD = 266;
}
