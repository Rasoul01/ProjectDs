package project2;

import models.Stack;
import tools.CustomException;

public interface StackService {

    void push(Stack stack,Object data);

    Object pop(Stack stack) throws CustomException;

    Object peek(Stack stack) throws CustomException;

    boolean isEmpty(Stack stack);

}
