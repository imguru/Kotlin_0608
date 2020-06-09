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

    // Field X  => Backing Field가 없는 프로퍼티
    public String getFullName() {
        return "";
    }
    public void setFullName(String s) {

    }

    // Field O - name => 프로퍼티
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
