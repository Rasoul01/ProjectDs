package project2;

import models.Expression;

public interface ExpressionService {

    Expression formatDetection(Expression expression);

    boolean isOperator(char c);

    boolean isOperand(char c);

    int getPrecedence(char operator);

    String infixToPostfix(Expression expression);

    String infixToPrefix(Expression expression);

    String postfixAndPrefixToInfix(Expression expression);

    String postfixToPrefix(Expression expression);

    String prefixToPostfix(Expression expression);

    double performOperation(char operator, double operand1, double operand2);

    double evaluateExpression(Expression expression);


}
