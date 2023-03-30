class Interpreter {
    int eval(ArvoreSintatica arv) {
        if (arv instanceof Num) {
            return ((Num) arv).num;
        } else if (arv instanceof Soma) {
            int arg1 = eval(((Soma) arv).arg1);
            int arg2 = eval(((Soma) arv).arg2);
            return arg1 + arg2;
        } else if (arv instanceof Sub) {
            int arg1 = eval(((Sub) arv).arg1);
            int arg2 = eval(((Sub) arv).arg2);
            return arg1 - arg2;
        } else if (arv instanceof Mult) {
            int arg1 = eval(((Mult) arv).arg1);
            int arg2 = eval(((Mult) arv).arg2);
            return arg1 * arg2;
        } else if (arv instanceof Div) {
            int arg1 = eval(((Div) arv).arg1);
            int arg2 = eval(((Div) arv).arg2);
            return arg1 / arg2;
        } else {
            throw new IllegalArgumentException("Expressão inválida: " + arv);
        }
    }
}