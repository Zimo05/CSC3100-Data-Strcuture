public class test {
    public int digit(int num, int d) {
        for (int i = 0; i < d; i++) {
            num /= 10;
        }
        return num % 10;
    }

    public static void main(String[] args) {
        int num = 123456;
        int d = 6;
        test t = new test();
        int result = t.digit(num, 6);
        System.out.println("The " + d + "th digit of " + num + " is: " + result);
    }
}