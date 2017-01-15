package SyntaxParser;

import LexBasedOnDFA.DFATag;
import LexBasedOnDFA.DFAToken;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * 根据已构造的LL（1）预测分析表进行语法分析
 * Created by zhoubin on 2017/1/14.
 */
public class Parser {
    private Stack<DFAToken> stack = new Stack<>();
    private Hashtable parseTable = new Hashtable();//保存LL(1)预测分析表
    private void error(){//打印出错信息并退出程序
        System.out.print("syntax error");
        System.exit(-1);
    }
    //向预测分析表中写入项
    private void reserve(String index, CFG cfg){
        parseTable.put(index, cfg);
    }
    //将从预测分析表中查找得到的产生式的右部符号加入栈
    private void addToStack(String str){
        switch (str){
            case "S":
                stack.push(new DFAToken(DFATag.S));
                break;
            case "A":
                stack.push(new DFAToken(DFATag.A));
                break;
            case "B":
                stack.push(new DFAToken(DFATag.B));
                break;
            case "F":
                stack.push(new DFAToken(DFATag.F));
                break;
            case "P":
                stack.push(new DFAToken(DFATag.P));
                break;
            case "num":
                stack.push(new DFAToken(DFATag.NUM));
                break;
            case "id":
                stack.push(new DFAToken(DFATag.ID));
                break;
            case "+":
                stack.push(new DFAToken(DFATag.ADD));
                break;
            case "=":
                stack.push(new DFAToken(DFATag.ASSIGN));
                break;
            case "relop":
                stack.push(new DFAToken(DFATag.RELOP));
                break;
            case "if":
                stack.push(new DFAToken(DFATag.IF));
                break;
            case "else":
                stack.push(new DFAToken(DFATag.ELSE));
                break;
        }
    }
    public Parser(){
        //初始化，栈顶是文法的开始符S，栈底为$
        stack.push(new DFAToken(DFATag.END));
        stack.push(new DFAToken(DFATag.S));
        //初始化LL(1)预测分析表
        reserve(DFATag.S+""+DFATag.ID, new CFG("S", "A"));
        reserve(DFATag.S+""+DFATag.IF, new CFG("S", "if B A else A"));
        reserve(DFATag.A+""+DFATag.ID, new CFG("A", "id = P"));
        reserve(DFATag.B+""+DFATag.NUM, new CFG("B", "F relop F"));
        reserve(DFATag.B+""+DFATag.ID, new CFG("B", "F relop F"));
        reserve(DFATag.F+""+DFATag.NUM, new CFG("F", "num"));
        reserve(DFATag.F+""+DFATag.ID, new CFG("F", "id"));
        reserve(DFATag.P+""+DFATag.NUM, new CFG("P", "F + F"));
        reserve(DFATag.P+""+DFATag.ID, new CFG("P", "F + F"));
    }
    public void parse(ArrayList<DFAToken> tokens){
        int ip = 0;//指向第一个未处理的token
        DFAToken X = stack.peek();

        while (X.tag != DFATag.END){//栈非空时进行
            if(ip>=tokens.size()){
                error();
            }
            if(X.tag == tokens.get(ip).tag){//栈顶符号与ip所指的符号相同，匹配一个符号
                stack.pop();
                ip++;
            }
            //栈顶符号为非终结符，且查表能查到一个产生式，输出产生式，栈顶弹出，将产生式右部入栈
            else if(parseTable.get(X.tag+""+tokens.get(ip).tag) != null){
                CFG cfg = (CFG)parseTable.get(X.tag+""+tokens.get(ip).tag) ;
                System.out.println(cfg);
                stack.pop();
                String right = cfg.right;
                String[] arr = right.split("\\s+");
                for(int i = arr.length-1;i>=0;i--){
                    addToStack(arr[i]);
                }
            }
            else{//其余情况说明出错
                error();
            }
            X = stack.peek();//令X=栈顶符号
        }

    }
}
