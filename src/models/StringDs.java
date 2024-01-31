package models;

public class StringDs {

    private char[] chars;

    private int size;

    private int capacity;

    public StringDs() {

        capacity = 10;
        chars = new char[10];
        size = 0;

    }


    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
