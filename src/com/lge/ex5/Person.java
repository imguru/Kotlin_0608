package com.lge.ex5;

public class Person {
    private String name;
    private boolean isMale;

    public Person(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getFullName() {
        return "";
    }

    public void setFullName(String s) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
