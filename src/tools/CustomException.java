package tools;

public class CustomException extends Exception {

    private String reason;


    public CustomException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
