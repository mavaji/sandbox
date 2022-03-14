package autoboxing;

public class NullFromInt {
    public static int hummm(int x, int y) {
        return x < y ? x : null;
    }

    public static void main(String[] args) {
        System.out.println(hummm(1, 2));
        System.out.println(hummm(2, 1));
    }
}
