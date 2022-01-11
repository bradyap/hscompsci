import java.util.ArrayList;

public class MyStack<anyType> implements Stackable<anyType> {

    private int top;
    private ArrayList<anyType> stack;

    public MyStack() {
        top = -1;
        stack = new ArrayList<anyType>();
    }

    public void push(anyType item) {
        stack.add(item);
        top++;
    }

    public anyType pop() {
        if (top == -1) {
            return null;
        }

        anyType item = stack.get(top);
        stack.remove(top);
        top--;
        return item;
    }

    public anyType peek() {
        if (top == -1) {
            return null;
        }
        
        return stack.get(top);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
        top = -1;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < stack.size(); i++) {
            result += stack.get(i) + " ";
        }
        return result;
    }
}