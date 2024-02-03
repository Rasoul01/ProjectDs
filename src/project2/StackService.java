package project2;

import models.Stack;

public interface StackService {

    void push(Stack stack,Object data);

    Object pop(Stack stack);

    Object peek(Stack stack);

    boolean isEmpty(Stack stack);

}
