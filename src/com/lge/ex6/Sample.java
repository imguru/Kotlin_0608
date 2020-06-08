package com.lge.ex6;

import java.io.*;

interface State extends Serializable {
}

interface View {
    State getCurrentState();

    void restoreState(State state);
}

class Button implements View {
    private int x;
    private int y;

    public Button(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Button{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // java.io.NotSerializableException: com.lge.ex6.Button
    // Inner Class(내부 클래스) - default
    //  : 외부 클래스에 대한 참조가 암묵적으로 존재한다.
    // Nested Class(중첩 클래스) - static
    //  : 외부 클래스에 대한 참조가 암묵적으로 존재하지 않는다.
    static class ButtonState implements State {
        int x;
        int y;

        ButtonState(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public State getCurrentState() {
        return new ButtonState(x, y);
    }

    @Override
    public void restoreState(State state) {
        ButtonState s = (ButtonState) state;
        this.x = s.x;
        this.y = s.y;
    }
}


public class Sample {
    public static void main(String[] args) throws Exception {
        Button button = new Button(30, 40);

        FileInputStream fis = new FileInputStream("button.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        State s = (State) ois.readObject();
        button.restoreState(s);

        System.out.println(button);

        /*
        Button button = new Button(10, 20);

        State s = button.getCurrentState();

        FileOutputStream fos = new FileOutputStream("button.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        */

    }
}

/*
interface Clickable {
    void click();

    // Java 8 에서는 인터페이스에 기본 구현을 제공하는 것을 허용합니다.
    //  => default method / defender method
    default void showOff() {
        System.out.println("Clickable!!");
    }
}

class Window implements Clickable {
    @Override
    public void click() {

    }
}


public class Sample {
    public static void main(String[] args) {

    }
}
*/