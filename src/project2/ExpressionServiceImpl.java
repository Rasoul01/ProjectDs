package project2;

import models.Expression;
import models.Stack;
import tools.CustomException;

import java.util.*;

public class ExpressionServiceImpl implements ExpressionService {

    StackService stackService = new StackServiceImpl();

    @Override
    public Expression formatDetection(Expression expression) {

        String exp = expression.getExpression();

        //  If it is guaranteed that input expression is in true form
        if (exp != null && !exp.isEmpty() && Character.isLetterOrDigit(exp.charAt(0)) || exp.charAt(0) == '(') {

            if (Character.isLetterOrDigit(exp.charAt(exp.length() - 1)) || exp.charAt(exp.length() - 1) == ')') {

                expression.setFormat("infix");

            } else {

                expression.setFormat("postfix");

            }

        } else {

            expression.setFormat("prefix");

        }
        System.out.println(expression.getExpression() + " is in " + expression.getFormat() + " format");
        return expression;

    }

    @Override
    public boolean isOperator(char c) {

        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');

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
            case '^':
                return 3;
        }

        return -1;

    }

    @Override
    public String infixToPostfix(Expression expression, boolean printStepByStep) throws CustomException {

        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();
        String infix = expression.getExpression();
        try {
            if (printStepByStep)
                System.out.println("* Converting to Postfix:");
            for (int i = 0; i < infix.length(); i++) {
                char currentChar = infix.charAt(i);
                if (isOperand(currentChar)) {
                    result.append(currentChar);
                    if (printStepByStep)
                        System.out.println(result);
                } else if (currentChar == '(') {
                    stackService.push(stack, currentChar);
                } else if (currentChar == ')') {
                    while (!stackService.isEmpty(stack) && (char) stackService.peek(stack) != '(') {
                        result.append(stackService.pop(stack));
                        if (printStepByStep)
                            System.out.println(result);
                    }
                    if (!stackService.isEmpty(stack) && (char) stackService.peek(stack) == '(') {
                        stackService.pop(stack);
                    }
                } else if (isOperator(currentChar)) {
                    while (!stackService.isEmpty(stack) && getPrecedence((char) stackService.peek(stack)) >= getPrecedence(currentChar)) {
                        result.append(stackService.pop(stack));
                        if (printStepByStep)
                            System.out.println(result);
                    }
                    stackService.push(stack, currentChar);
                }
            }
            while (!stackService.isEmpty(stack)) {
                result.append(stackService.pop(stack));
                if (printStepByStep)
                    System.out.println(result);
            }

            return result.toString();
        } catch (Exception e) {
            throw new CustomException("Not valid");
        }

    }

    @Override
    public String infixToPrefix(Expression expression, boolean printStepByStep) throws CustomException {

        try {
            if (printStepByStep)
                System.out.println("* Converting to Prefix:");
            Stack stack = new Stack();
            StringBuilder result = new StringBuilder();
            String infix = expression.getExpression();
            infix = new StringBuilder(infix).reverse().toString();

            for (int i = 0; i < infix.length(); i++) {
                char currentChar = infix.charAt(i);
                if (isOperand(currentChar)) {
                    result.append(currentChar);
                    if (printStepByStep)
                        System.out.println(result);
                } else if (currentChar == ')') {
                    stackService.push(stack, currentChar);
                } else if (currentChar == '(') {
                    while (!stackService.isEmpty(stack) && (char) stackService.peek(stack) != ')') {
                        result.append(stackService.pop(stack));
                        if (printStepByStep)
                            System.out.println(result);
                    }
                    if (!stackService.isEmpty(stack) && (char) stackService.peek(stack) == ')') {
                        stackService.pop(stack);
                    }
                } else if (isOperator(currentChar)) {
                    while (!stackService.isEmpty(stack) && getPrecedence((char) stackService.peek(stack)) >= getPrecedence(currentChar)) {
                        result.append(stackService.pop(stack));
                        if (printStepByStep)
                            System.out.println(result);
                    }
                    stackService.push(stack, currentChar);
                }
            }
            while (!stackService.isEmpty(stack)) {
                result.append(stackService.pop(stack));
                if (printStepByStep)
                    System.out.println(result);
            }
            return result.reverse().toString();
        } catch (Exception e) {
            throw new CustomException("Not valid");
        }
    }

    @Override
    public String postfixToInfix(Expression expression, boolean printStepByStep) throws CustomException {

        try {
            if (printStepByStep)
                System.out.println("* Converting to Infix:");
            Stack stack = new Stack();
            String exp = expression.getExpression();

            for (int i = 0; i < exp.length(); i++) {
                char currentChar = exp.charAt(i);

                if (isOperand(currentChar)) {
                    stackService.push(stack, String.valueOf(currentChar));

                } else {
                    String operand2 = (String) stackService.pop(stack);
                    String operand1 = (String) stackService.pop(stack);
                    String infixExpression = "(" + operand1 + currentChar + operand2 + ")";
                    if (printStepByStep)
                        System.out.println(infixExpression);
                    stackService.push(stack, infixExpression);
                }
            }
            return (String) stackService.pop(stack);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Not valid");
        }
    }

    @Override
    public String prefixToInfix(Expression expression, boolean printStepByStep) throws CustomException {

        try {
            if (printStepByStep)
                System.out.println("* Converting to Infix:");
            Stack stack = new Stack();
            String exp = expression.getExpression();

            for (int i = exp.length() - 1; i >= 0; i--) {
                char currentChar = exp.charAt(i);

                if (Character.isLetterOrDigit(currentChar)) {

                    stackService.push(stack, String.valueOf(currentChar));

                } else if (isOperator(currentChar)) {

                    String operand1 = (String) stackService.pop(stack);
                    String operand2 = (String) stackService.pop(stack);
                    String newExpression = "(" + operand1 + currentChar + operand2 + ")";
                    if (printStepByStep)
                        System.out.println(newExpression);
                    stackService.push(stack, newExpression);
                }
            }

            return (String) stackService.pop(stack);


        } catch (Exception e) {
            throw new CustomException("Not valid");
        }
    }


    @Override
    public String postfixToPrefix(Expression expression, boolean printStepByStep) throws CustomException {

        String infix = postfixToInfix(expression, false);
        Expression infixExpression = new Expression(infix);
        return infixToPrefix(infixExpression, printStepByStep);

    }

    @Override
    public String prefixToPostfix(Expression expression, boolean printStepByStep) throws CustomException {

        String infix = prefixToInfix(expression, false);
        Expression infixExpression = new Expression(infix);
        return infixToPostfix(infixExpression, printStepByStep);
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
    public double evaluateExpression(Expression expression, boolean printStepByStep) throws CustomException {

        try {
            Stack stack = new Stack();
            String postfixExpression = expression.getExpression();
            for (char token : postfixExpression.toCharArray()) {
                if (Character.isDigit(token)) {
                    stackService.push(stack, (double) (token - '0'));
                } else if (isOperator(token)) {
                    double operand2 = (double) stackService.pop(stack);
                    double operand1 = (double) stackService.pop(stack);
                    double result = performOperation(token, operand1, operand2);
                    if (printStepByStep)
                        System.out.printf("%s %s %s = %s%n", operand1, token, operand2, result);
                    stackService.push(stack, result);
                }
            }
            return (double) stackService.pop(stack);
        } catch (Exception e) {
            throw new CustomException("Not valid");
        }
    }

    @Override
    public boolean checkExpression(Expression expression) {

        formatDetection(expression);
        char[] tempArray = expression.getExpression().toCharArray();
        for (int i = 0; i < tempArray.length; i++) {
            if (Character.isLetter(tempArray[i])) {
                tempArray[i] = '1';
            }
        }
        Expression tempExp = new Expression(new String(tempArray), expression.getFormat());

        if (tempExp.getFormat() == "infix") {
            try {
                Expression exp = new Expression(infixToPostfix(tempExp, false));
                evaluateExpression(exp, false);
            } catch (CustomException e) {
                return false;
            }
        } else if (tempExp.getFormat() == "prefix") {
            try {
                Expression exp = new Expression(prefixToPostfix(tempExp, false));
                evaluateExpression(exp, false);
            } catch (CustomException e) {
                return false;
            }

        } else if (tempExp.getFormat() == "postfix") {
            try {
                evaluateExpression(tempExp, false);
            } catch (CustomException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double evaluatePhrasalExpression(Expression expression) throws CustomException {

        String exp = expression.getExpression();
        List<Character> charArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        HashMap<Character, Character> map = new HashMap<>();

        for (char c : exp.toCharArray()) {
            charArrayList.add(c);
        }
        for (char c : charArrayList) {
            if (Character.isLetter(c) & !map.containsKey(c)) {
                System.out.println("Enter your desired number for char " + c);
                char num = scanner.nextLine().charAt(0);
                map.put(c, num);
            }
        }

        for (char c : charArrayList) {
            if (Character.isLetter(c)) {
                resultString.append(map.get(c));
            } else
                resultString.append(c);
        }

        expression.setExpression(resultString.toString());
        return evaluateExpression(expression, true);
    }
}

