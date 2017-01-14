基于有限状态机实现词法分析器，自动机为FA.PNG
自顶向下进行语法分析
RE:
digit --> [0-9]
letter --> [A-Za-z]
relop --> <|>|<=|>=|==
assign --> =
add --> +
num --> digit+
id --> letter(letter|digit)*

CFG:
S --> A|if B A else A
B --> F relop F
A --> id = P
P --> F + F
F --> num|id
