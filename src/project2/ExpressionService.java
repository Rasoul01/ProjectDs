package project2;

import models.Expression;
import tools.CustomException;

public interface ExpressionService {

    Expression formatDetection(Expression expression);

    boolean isOperator(char c);

    boolean isOperand(char c);

    int getPrecedence(char operator);

    String infixToPostfix(Expression expression, boolean printStepByStep) throws CustomException;

    String infixToPrefix(Expression expression, boolean printStepByStep) throws CustomException;

    String postfixToInfix(Expression expression, boolean printStepByStep) throws CustomException;

    String prefixToInfix(Expression expression, boolean printStepByStep) throws CustomException;

    String postfixToPrefix(Expression expression, boolean printStepByStep) throws CustomException;

    String prefixToPostfix(Expression expression, boolean printStepByStep) throws CustomException;

    double performOperation(char operator, double operand1, double operand2);

    double evaluateExpression(Expression expression, boolean printStepByStep) throws CustomException;

    boolean checkExpression(Expression expression);

}
