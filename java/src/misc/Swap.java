package misc;

public class Swap {

    public static void main(String[] args) {
        Swap s = new Swap();
        Integer x = 4, y = 5;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        s.swap(x, y);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        s.makeNull(x, y);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        s.setValue(x, y);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }

    public void setValue(Integer a, Integer b) {
        a = 15;
        b = 12;
    }

    public void makeNull(Integer a, Integer b) {
        a = null;
    }

    public void swap(Integer a, Integer b) {
        System.out.println("---enter swap---");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        Integer temp = a;
        System.out.println("temp = " + temp);
        a = b;
        b = temp;

        System.out.println("---------------");

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        ;
        System.out.println("---exit swap---");
    }
}
