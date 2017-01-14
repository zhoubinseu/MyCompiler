package SyntaxParser;

import LexBasedOnDFA.DFAOperator;
import LexBasedOnDFA.DFATag;
import LexBasedOnDFA.DFAToken;
import LexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by zhoubin on 2017/1/14.
 */
public class Parser {
    private Stack<DFAToken> stack = new Stack<>();
    private Hashtable parseTable = new Hashtable();
    private void error(){
        System.out.print("syntax error");
        System.exit(-1);
    }
    private void reserve(String index, CFG cfg){
        parseTable.put(index, cfg);
    }
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
        stack.push(new DFAToken(DFATag.END));
        stack.push(new DFAToken(DFATag.S));

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
        int ip = 0;//point to first token
        DFAToken X = stack.peek();

        while (X.tag != DFATag.END){
            if(ip>=tokens.size()){
                error();
            }
            if(X.tag == tokens.get(ip).tag){
                stack.pop();
                ip++;
            }
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
            else{
                error();
            }
            X = stack.peek();
        }

    }
}
