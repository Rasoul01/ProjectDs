package project2;

import models.Expression;
import tools.CustomException;

public interface ExpressionService {

    Expression formatDetection(Expression expression);

    boolean isOperator(char c);

    boolean isOperand(char c);

    int getPrecedence(char operator);

    String infixToPostfix(Expression expression) throws CustomException;

    String infixToPrefix(Expression expression) throws CustomException;

    String postfixToInfix(Expression expression) throws CustomException;

    String prefixToInfix(Expression expression) throws CustomException;

    String postfixToPrefix(Expression expression) throws CustomException;

    String prefixToPostfix(Expression expression) throws CustomException;

    double performOperation(char operator, double operand1, double operand2);

    double evaluateExpression(Expression expression) throws CustomException;

    boolean checkExpression(Expression expression);



}
