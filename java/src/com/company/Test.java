package com.company;

public class Test {
    public static void main(String[] args) {
        var s = "";
        System.out.println(s.getClass().equals(Object.class));

        var t = new Test();
        System.out.println("t.y = " + t.y);
        t.f(4);
        System.out.println("t.y = " + t.y);

    }

    private int y;

    void f(Test this, int x) {
        this.y = x + 5;

    }
}
