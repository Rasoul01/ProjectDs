package project5;

import models.Stack;
import project2.StackService;
import project2.StackServiceImpl;

import java.util.*;

public class StackPermutation {

    private static final StackService stackService = new StackServiceImpl();

    static List<Object> permute(LinkedList<Object> input) {
        return permute(input, new Stack(), new LinkedList<>());
    }

    static List<Object> permute(LinkedList<Object> input, Stack temp, LinkedList<Object> output) {

        List<Object> result = new ArrayList<>();

        // If input is empty, then permutation is done
        if (input.isEmpty()) {
            Stack tempCopy = new Stack();
            tempCopy.setStackList(new LinkedList<>(temp.getStackList()));
            LinkedList<Object> permute = new LinkedList<>(output);
            while (!stackService.isEmpty(tempCopy)) {
                permute.offer(stackService.pop(tempCopy));
            }
            result.add(permute);
        } else {
            if (!stackService.isEmpty(temp)) {
                // pop the item from temp and move it to output
                output.offer(stackService.pop(temp));

                result.addAll(permute(input, temp, output));

                // reset changes
                stackService.push(temp, output.removeLast());
            }
            // pop the item from input and move it to temp
            stackService.push(temp, input.poll());

            result.addAll(permute(input, temp, output));

            // reset changes
            input.push(stackService.pop(temp));
        }

        return result;
    }
}