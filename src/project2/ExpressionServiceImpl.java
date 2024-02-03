package project2;

import models.Expression;
import models.Stack;

import java.util.LinkedList;
import java.util.Objects;

public class ExpressionServiceImpl implements ExpressionService{

    StackService stackService = new StackServiceImpl();

    @Override
    public Expression formatDetection(Expression expression) {

        String exp = expression.getExpression();

        if ( exp != null && !exp.isEmpty() && Character.isLetterOrDigit(exp.charAt(0)) || exp.charAt(0) == '(' ){

            if (Character.isLetterOrDigit(exp.charAt(exp.length() - 1))){

                expression.setFormat("infix");

            }else {

                expression.setFormat("postfix");

            }

        }else {

            expression.setFormat("prefix");

        }

        System.out.println(expression.getExpression()+" is in "+expression.getFormat()+" format");
        return expression;

    }

    @Override
    public boolean isOperator(char c) {

        return (c == '+' || c == '-' || c == '*' || c == '/' || c=='^');

    }

    @Override
    public boolean isOperand(char c) {

        return (Character.isLetterOrDigit(c));

    }

    @Override
    public int getPrecedence(char operator) {

        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case  '^':
                return 3;
        }

        return -1;

    }

    @Override
    public String infixToPostfix(Expression expression) {

        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();
        String infix = expression.getExpression();

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);
            if (isOperand(currentChar)) {
                result.append(currentChar);
            } else if (currentChar == '(') {
                stackService.push(stack,currentChar);
            } else if (currentChar == ')') {
                while (!stackService.isEmpty(stack) && (char)stackService.peek(stack) != '(') {
                    result.append(stackService.pop(stack));
                }
                if (!stackService.isEmpty(stack) && (char) stackService.peek(stack) == '(') {
                    stackService.pop(stack);
                }
            } else if (isOperator(currentChar)) {
                while (!stackService.isEmpty(stack) && getPrecedence((char)stackService.peek(stack)) >= getPrecedence(currentChar)) {
                    result.append(stackService.pop(stack));
                }
                stackService.push(stack,currentChar);
            }
        }
        while (!stackService.isEmpty(stack)) {
            result.append(stackService.pop(stack));
        }

        return result.toString();

    }

    @Override
    public String infixToPrefix(Expression expression) {

        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();
        String infix = expression.getExpression();
        infix = new StringBuilder(infix).reverse().toString();

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);
            if (isOperand(currentChar)) {
                result.append(currentChar);
            } else if (currentChar == ')') {
                stackService.push(stack,currentChar);
            } else if (currentChar == '(') {
                while (!stackService.isEmpty(stack) && (char)stackService.peek(stack) != ')') {
                    result.append(stackService.pop(stack));
                }
                if (!stackService.isEmpty(stack) && (char) stackService.peek(stack) == ')') {
                    stackService.pop(stack);
                }
            } else if (isOperator(currentChar)) {
                while (!stackService.isEmpty(stack) && getPrecedence((char)stackService.peek(stack)) >= getPrecedence(currentChar)) {
                    result.append(stackService.pop(stack));
                }
                stackService.push(stack,currentChar);
            }
        }
        while (!stackService.isEmpty(stack)) {
            result.append(stackService.pop(stack));
        }
        return result.reverse().toString();
    }

    @Override
    public String postfixAndPrefixToInfix(Expression expression) {

        Stack stack = new Stack();
        String exp = expression.getExpression();

        for (int i = 0; i < exp.length(); i++) {
            char currentChar = exp.charAt(i);

            if (isOperand(currentChar)) {
                stackService.push(stack,String.valueOf(currentChar));

            } else {
                String operand2 = (String) stackService.pop(stack);
                String operand1= (String) stackService.pop(stack);
                String infixExpression = "(" + operand1 + currentChar + operand2 + ")";
                stackService.push(stack, infixExpression);
            }
        }
        return (String) stackService.pop(stack);
    }

    @Override
    public String postfixToPrefix(Expression expression) {

        String infix = postfixAndPrefixToInfix(expression);
        Expression infixExpression = new Expression(infix);
        return infixToPrefix(infixExpression);

    }

    @Override
    public String prefixToPostfix(Expression expression) {

        String infix = postfixAndPrefixToInfix(expression);
        Expression infixExpression = new Expression(infix);
        return infixToPostfix(infixExpression);
    }

    @Override
    public double performOperation(char operator, double operand1, double operand2) {

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public double evaluateExpression(Expression expression) {

        Stack stack = new Stack();
        String postfixExpression = expression.getExpression();
        for (char token : postfixExpression.toCharArray()) {
            if (Character.isDigit(token)) {
                stackService.push(stack,(double) (token - '0'));
            } else if (isOperator(token)) {
                double operand2 = (double) stackService.pop(stack);
                double operand1 = (double) stackService.pop(stack);
                double result = performOperation(token, operand1, operand2);
                stackService.push(stack,result);
            }
        }
        return (double) stackService.pop(stack);
    }


}

