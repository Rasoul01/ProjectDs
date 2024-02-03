package models;

public class Expression {

    String expression;

    String format;

    public Expression(String expression, String format) {
        this.expression = expression;
        this.format = format;
    }

    public Expression() {
    }

    public Expression(String exp) {
        this.expression = exp;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
