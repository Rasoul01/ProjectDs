package project2;

import models.Expression;
import tools.CustomException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ExpressionMain {

    static ExpressionService expressionService = new ExpressionServiceImpl();

    public static void main(String[] args) throws IOException, CustomException {
         expressionMain();
    }

    public static void expressionMain() throws IOException, CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to load data from file? 1:yes || 2:not");
        int inputChoice = scanner.nextInt();
        scanner.nextLine();
        if (inputChoice == 1) {
            List<String> exps = FileHelper.fileHelper("test.txt");
            for (int i = 0; i < exps.size(); i++) {
                String exp = exps.get(i);
                Expression expression = new Expression(exp);
                System.out.println("==================================================");
                if (expressionService.checkExpression(expression)) {
                    if (Objects.equals(expression.getFormat(), "infix")) {
                        infixFormMethods(expression);
                    } else if (Objects.equals(expression.getFormat(), "postfix")) {
                        postFixFormMethods(expression);
                    } else if (Objects.equals(expression.getFormat(), "prefix")) {
                        prefixFormMethods(expression);
                    }
                } else {
                    System.out.println(exp + " is Not a valid expression!");
                    System.out.println("Enter the correct expression: ");
                    String newExp = scanner.nextLine();
                    FileHelper.EditLineInFile("test.txt", exp, newExp);
                    System.out.println("File has been successfully edited.");
                    exps.set(i, newExp);
                    i--;
                }
            }
        }
        if (inputChoice == 2) {
            System.out.println("Enter your desired expression: ");
            String exp = scanner.nextLine();
            Expression expression = new Expression(exp);
            if (expressionService.checkExpression(expression)) {
                if (Objects.equals(expression.getFormat(), "infix")) {
                    infixFormMethods(expression);
                } else if (Objects.equals(expression.getFormat(), "postfix")) {
                    postFixFormMethods(expression);
                } else if (Objects.equals(expression.getFormat(), "prefix")) {
                    prefixFormMethods(expression);
                }
            } else System.out.println(exp + " is Not a valid expression!");
        }
    }

    public static void infixFormMethods(Expression expression) throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        String postfixExpression = expressionService.infixToPostfix(expression, true);
        System.out.println("POSTFIX FORM: " + postfixExpression);
        System.out.println("--------------------------------------------------");
        System.out.println("PREFIX FORM: " + expressionService.infixToPrefix(expression, true));
        System.out.println("--------------------------------------------------");
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice == 1) {
            Expression toBeEvaluated = new Expression(postfixExpression, "postfix");
            System.out.println("Do you want to put number as the letter? 1:yes || 2:not");
            int inputChoice2 = scanner.nextInt();
            double result;
            if (inputChoice2 == 1)
                result = expressionService.evaluatePhrasalExpression(toBeEvaluated);
            else
                result = expressionService.evaluateExpression(toBeEvaluated, true);

            System.out.println("\nRESULT: " + result);
        }

    }

    public static void postFixFormMethods(Expression expression) throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("INFIX FORM: " + expressionService.postfixToInfix(expression, true));
        System.out.println("--------------------------------------------------");
        System.out.println("PREFIX FORM: " + expressionService.postfixToPrefix(expression, true));
        System.out.println("--------------------------------------------------");
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice == 1) {
            System.out.println("Do you want to put number as the letter? 1:yes || 2:not");
            int inputChoice2 = scanner.nextInt();
            double result;
            if (inputChoice2 == 1)
                result = expressionService.evaluatePhrasalExpression(expression);
            else
                result = expressionService.evaluateExpression(expression, true);

            System.out.println("\nRESULT: " + result);
        }

    }

    public static void prefixFormMethods(Expression expression) throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        String postfixExpression = expressionService.prefixToPostfix(expression, true);
        System.out.println("POSTFIX FORM: " + postfixExpression);
        System.out.println("--------------------------------------------------");
        System.out.println("INFIX FORM: " + expressionService.prefixToInfix(expression, true));
        System.out.println("--------------------------------------------------");
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice == 1) {
            Expression toBeEvaluated = new Expression(postfixExpression, "postfix");
            System.out.println("Do you want to put number as the letter? 1:yes || 2:not");
            int inputChoice2 = scanner.nextInt();
            double result;
            if (inputChoice2 == 1)
                result = expressionService.evaluatePhrasalExpression(toBeEvaluated);
            else
                result = expressionService.evaluateExpression(toBeEvaluated, true);

            System.out.println("\nRESULT: " + result);
        }

    }

}

