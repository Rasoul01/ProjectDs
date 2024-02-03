package project2;

import models.Expression;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpressionMain {

    static ExpressionService expressionService = new ExpressionServiceImpl();

    public static void main(String[] args) {
         expressionMain();
    }

    public static void expressionMain(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to load data from file? 1:yes || 2:not");
        int inputChoice = scanner.nextInt();
        scanner.nextLine();
        if (inputChoice==1){
           List<String> exps = FileHelper.fileHelper("test.txt");
           for (String exp:exps){
               Expression expression = new Expression(exp);
               expressionService.formatDetection(expression);
               if (expression.getFormat()=="infix"){
                    System.out.println("postfix form: ");
                    String postfixExpression = expressionService.infixToPostfix(expression);
                    System.out.println(postfixExpression);
                    System.out.println("prefix form: ");
                    System.out.println(expressionService.infixToPrefix(expression));
                    Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
                    System.out.println(expressionService.evaluateExpression(toBeEvaluated));

               } else if (expression.getFormat()=="postfix") {
                   System.out.println("infix form: ");
                   System.out.println(expressionService.postfixAndPrefixToInfix(expression));
                   System.out.println("prefix form: ");
                   System.out.println(expressionService.postfixToPrefix(expression));
                   System.out.println(expressionService.evaluateExpression(expression));

               } else if (expression.getFormat() == "prefix") {
                   System.out.println("postfix form: ");
                   String postfixExpression = expressionService.prefixToPostfix(expression);
                   System.out.println(postfixExpression);
                   System.out.println("postfix form: ");
                   System.out.println(expressionService.prefixToPostfix(expression));
                   Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
                   System.out.println(expressionService.evaluateExpression(toBeEvaluated));
               }

           }
        }
        if (inputChoice==2) {
            System.out.println("Enter your desired expression: ");
            String exp = scanner.nextLine();
                Expression expression = new Expression(exp);
                expressionService.formatDetection(expression);
                if (expression.getFormat()=="infix"){
                    System.out.println("postfix form: ");
                    String postfixExpression = expressionService.infixToPostfix(expression);
                    System.out.println(postfixExpression);
                    System.out.println("prefix form: ");
                    System.out.println(expressionService.infixToPrefix(expression));
                    Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
                    System.out.println(expressionService.evaluateExpression(toBeEvaluated));

                } else if (expression.getFormat()=="postfix") {
                    System.out.println("infix form: ");
                    System.out.println(expressionService.postfixAndPrefixToInfix(expression));
                    System.out.println("prefix form: ");
                    System.out.println(expressionService.postfixToPrefix(expression));
                    System.out.println(expressionService.evaluateExpression(expression));

                } else if (expression.getFormat() == "prefix") {
                    System.out.println("postfix form: ");
                    String postfixExpression = expressionService.prefixToPostfix(expression);
                    System.out.println(postfixExpression);
                    System.out.println("postfix form: ");
                    System.out.println(expressionService.prefixToPostfix(expression));
                    Expression toBeEvaluated = new Expression(postfixExpression,"postfix");
                    System.out.println(expressionService.evaluateExpression(toBeEvaluated));
                }
            }

         }
}
