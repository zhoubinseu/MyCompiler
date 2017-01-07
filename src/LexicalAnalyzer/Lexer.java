package LexicalAnalyzer;

import java.util.Hashtable;

/**
 * Created by zhoubin on 2016/12/17.
 */
public class Lexer {
    public char peek;//记录当前位置的字符
    private Hashtable words = new Hashtable();//保存关键字
    /**
     * 以单词的字符串值作为key，将单词存入Hashtable
     * @param w
     */
    private void reserve(Word w){
        words.put(w.value, w);
    }
    public Lexer(){
        //初始化，先将关键字存进Hashtable中
        reserve(new Word(Tag.IF, "if"));
        reserve(new Word(Tag.ELSE, "else"));
    }

    public Token scan(){
        //剔除空白字符
        for(;Input.pos<Input.string.length();Input.pos++){
            peek = Input.string.charAt(Input.pos);
            if(peek == ' '||peek == '\t'||peek == '\n'){
                continue;
            }else{
                break;
            }
        }
        //识别操作符
        switch(peek){
            case '+':
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
                return new Operator(Tag.ADD, "+");
            case '=':
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
                if(Input.pos<Input.string.length()&&Input.string.charAt(Input.pos) == '='){
                    Input.pos++;
                    //peek = Input.string.charAt(Input.pos);
                    return new Operator(Tag.EQ, "==");
                }else{
                    return new Operator(Tag.ASSIGN, "=");
                }
            case '<':
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
                if(Input.pos<Input.string.length()&&Input.string.charAt(Input.pos) == '='){
                    Input.pos++;
                    //peek = Input.string.charAt(Input.pos);
                    return new Operator(Tag.LE, "<=");
                }else{
                    return new Operator(Tag.LT, "<");
                }
            case '>':
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
                if(Input.pos<Input.string.length()&&Input.string.charAt(Input.pos) == '='){
                    Input.pos++;
                    //peek = Input.string.charAt(Input.pos);
                    return new Operator(Tag.GE, ">=");
                }else{
                    return new Operator(Tag.GT, ">");
                }
        }
        //识别数字
        if(Character.isDigit(peek)){
            int v = 0;
            do{
                v = v*10+Character.digit(Input.string.charAt(Input.pos),10);
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
            }while (Input.pos<Input.string.length()&&Character.isDigit(Input.string.charAt(Input.pos)));
            return new Num(v);
        }
        //识别标识符或关键字
        if(Character.isLetter(peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(Input.string.charAt(Input.pos));
                Input.pos++;
                //peek = Input.string.charAt(Input.pos);
            }while(Input.pos<Input.string.length()&&Character.isLetterOrDigit(Input.string.charAt(Input.pos)));
            String s = b.toString();
            Word w = (Word)words.get(s);
            if(w != null){
                return w;
            }else {
                return new Word(Tag.ID, s);
            }
        }
        //其他字符直接返回
        Token token = new Token(peek);
        Input.pos++;
        return token;
    }
}
