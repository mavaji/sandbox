package shadowing;

public class C1 {
    void foo(int a) {
//        for (int a = 0; a < 5; a++) { }
    }
}

class C2 {
    int a = 0;
    { int a = 1; }
}

class C3 {
    { int a = 0; }
    { int a = 1; }
}

class C4 {
    {
        int a = 0;
//        for (int a = 0; a < 5; a++) { }
    }
}

class C5 {
    {
        for (int a = 0; a < 5; a++) { }
        int a = 0;
    }
}