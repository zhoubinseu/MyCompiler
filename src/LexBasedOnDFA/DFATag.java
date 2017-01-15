package LexBasedOnDFA;

/**
 * Created by zhoubin on 2016/12/20.
 * 包含了词法分析中token的类型和语法分析中的非终结符以及$
 *
 * 词素的类型，分别有正整数、标识符、关键字（小写的if,else）、
 * 关系运算符（==,<,<=,>,>=）、赋值符号(=)、加（+）
 *
 * 产生式的符号包括S,A,B,F,P以及表示结尾的$
 *
 */
public class DFATag {
    public final static int
        NUM = 256, ID = 257,
        IF = 258, ELSE = 259,
        ASSIGN = 260, ADD = 261,
        RELOP = 262,
        S = 263, A = 264,
        B = 265, F = 266,
        P = 267, END = 268;
}
