package LexBasedOnDFA;

import java.util.Hashtable;

/**
 * Created by zhoubin on 2017/1/6.
 */
public class DFALexer {
    private char peek;//记录当前待处理的字符
    private int state = 0;//状态值
    private Hashtable words = new Hashtable();//保存关键字
    private void error(){
        System.out.println("error happened");
        System.exit(-1);
    }
    private void reserve(DFAWord w){
        words.put(w.value, w);
    }

    /**
     * 读取pos所指的字符，然后将pos向后移动一位
     * @return
     */
    private char nextChar(){
        if(LexInput.pos<LexInput.string.length()){
            char peek = LexInput.string.charAt(LexInput.pos);
            LexInput.pos++;
            return peek;
        }else{
            return (char)-1;
        }
    }
    public DFALexer(){
        reserve(new DFAWord(DFATag.IF, "if"));
        reserve(new DFAWord(DFATag.ELSE, "else"));
    }

    /**
     * 分析并最终返回一个词法单元
     * @return
     */
    public DFAToken scan(){
        //reserve the value of integer lexeme
        int numValue = 0;
        //reserve the value of id lexeme
        StringBuffer idValueBuffer = new StringBuffer();
        //each scan starts at state 0
        state = 0;
        //剔除空白符
        for(;LexInput.pos<LexInput.string.length();LexInput.pos++){
            peek = LexInput.string.charAt(LexInput.pos);
            if(peek == ' '||peek == '\t'||peek == '\n'){
                continue;
            }else{
                break;
            }
        }

        while (true){
            switch (state){
                case 0:
                    peek = nextChar();
                    if(Character.isDigit(peek)){
                        numValue = numValue*10 + Character.digit(peek, 10);
                        state = 1;
                    }
                    else if(Character.isLetter(peek)){
                        idValueBuffer.append(peek);
                        state = 2;
                    }
                    else if(peek == '+') state = 3;
                    else if(peek == '=') state = 4;
                    else if(peek == '<') state = 6;
                    else if(peek == '>') state = 8;
                    else if(peek == (char)-1) return null;//若无此行，输入以空格结尾时程序出错
                    else error();
                    break;
                case 1://在某一状态无法继续跳转，可以返回时，若达到输入末尾，直接返回值，否则返回值并且将pos回退一位
                    peek = nextChar();
                    if(Character.isDigit(peek)){
                        numValue = numValue*10 + Character.digit(peek, 10);
                        state = 1;
                    }
                    else if(peek == (char)-1){
                        return new DFANum(numValue);
                    }else{
                        LexInput.pos--;
                        return new DFANum(numValue);
                    }
                    break;
                case 2:
                    peek = nextChar();
                    if(Character.isLetterOrDigit(peek)){
                        idValueBuffer.append(peek);
                        state = 2;
                    }
                    else if(peek == (char)-1){
                        String idValue = idValueBuffer.toString();
                        DFAWord w = (DFAWord)words.get(idValue);
                        if(w != null){
                            return w;
                        }else{
                            return new DFAWord(DFATag.ID, idValue);
                        }
                    }
                    else{
                        LexInput.pos--;
                        String idValue = idValueBuffer.toString();
                        DFAWord w = (DFAWord)words.get(idValue);
                        if(w != null){
                            return w;
                        }else{
                            return new DFAWord(DFATag.ID, idValue);
                        }
                    }
                    break;
                case 3:
                    return new DFAOperator(DFATag.ADD, "+");
                case 4:
                    peek = nextChar();
                    if(peek == '=') state = 5;
                    else if(peek == (char)-1){
                        return new DFAOperator(DFATag.ASSIGN, "=");
                    }
                    else{
                        LexInput.pos--;
                        return new DFAOperator(DFATag.ASSIGN, "=");
                    }
                    break;
                case 5:
                    return new DFAOperator(DFATag.RELOP, "==");
                case 6:
                    peek = nextChar();
                    if(peek == '=') state = 7;
                    else if(peek == (char)-1){
                        return new DFAOperator(DFATag.RELOP, "<");
                    }
                    else {
                        LexInput.pos--;
                        return new DFAOperator(DFATag.RELOP, "<");
                    }
                    break;
                case 7:
                    return new DFAOperator(DFATag.RELOP,"<=");
                case 8:
                    peek = nextChar();
                    if(peek == '=') state = 9;
                    else if(peek == (char)-1){
                        return new DFAOperator(DFATag.RELOP,">");
                    }
                    else {
                        LexInput.pos--;
                        return new DFAOperator(DFATag.RELOP, ">");
                    }
                    break;
                case 9:
                    return new DFAOperator(DFATag.RELOP, ">=");
            }
        }
    }

}
