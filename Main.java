public class Main {
    public static void main(String[] args) throws InterruptedException {
        Yard yard = new Yard(10, 9);
        Thread operation = new Thread(yard);
        operation.start();
        operation.join();
    }
}
