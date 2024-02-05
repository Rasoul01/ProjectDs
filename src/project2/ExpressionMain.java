package project2;

import models.Expression;
import tools.CustomException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ExpressionMain {

    static ExpressionService expressionService = new ExpressionServiceImpl();

    public static void main(String[] args) throws IOException, CustomException {
         expressionMain();
    }

    public static void expressionMain() throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to load data from file? 1:yes || 2:not");
        int inputChoice = scanner.nextInt();
        scanner.nextLine();
        if (inputChoice==1){
           List<String> exps = FileHelper.fileHelper("test.txt");
           for (String exp:exps) {
               Expression expression = new Expression(exp);
               if (expressionService.checkExpression(expression)) {
                   //  expressionService.formatDetection(expression);
                   if (Objects.equals(expression.getFormat(), "infix")) {
                       infixFormMethods(expression);
                   } else if (Objects.equals(expression.getFormat(), "postfix")) {
                       postFixFormMethods(expression);
                   } else if (Objects.equals(expression.getFormat(), "prefix")) {
                       prefixFormMethods(expression);
                   }
               }else System.out.println(exp+" is Not valid ");
           }
        }
        if (inputChoice==2) {
            System.out.println("Enter your desired expression: ");
            String exp = scanner.nextLine();
            Expression expression = new Expression(exp);
            if (expressionService.checkExpression(expression)) {
                expressionService.formatDetection(expression);
                if (Objects.equals(expression.getFormat(), "infix")) {
                    infixFormMethods(expression);
                } else if (Objects.equals(expression.getFormat(), "postfix")) {
                    postFixFormMethods(expression);
                } else if (Objects.equals(expression.getFormat(), "prefix")) {
                    prefixFormMethods(expression);
                }
            }else System.out.println(exp+" is Not valid ");
        }
    }

    public static void infixFormMethods(Expression expression) throws CustomException {

        Scanner scanner =  new Scanner(System.in);
        System.out.println("postfix form: ");
        String postfixExpression = expressionService.infixToPostfix(expression);
        System.out.println(postfixExpression);
        System.out.println("prefix form: ");
        System.out.println(expressionService.infixToPrefix(expression));
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice==1){
            Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
            System.out.println(expressionService.evaluateExpression(toBeEvaluated));
        }

    }

    public static void postFixFormMethods(Expression expression) throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("infix form: ");
        System.out.println(expressionService.postfixToInfix(expression));
        System.out.println("prefix form: ");
        System.out.println(expressionService.postfixToPrefix(expression));
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice==1){
            System.out.println(expressionService.evaluateExpression(expression));
        }

    }

    public static void prefixFormMethods(Expression expression) throws CustomException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("postfix form: ");
        String postfixExpression = expressionService.prefixToPostfix(expression);
        System.out.println(postfixExpression);
        System.out.println("infix form: ");
        System.out.println(expressionService.prefixToInfix(expression));
        System.out.println("Do you want to evaluate the expression? 1:yes || 2 :not");
        int inputChoice = scanner.nextInt();
        if (inputChoice==1){
            Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
            System.out.println(expressionService.evaluateExpression(toBeEvaluated));
        }

    }

}

