package assignment5.exercise1;

public class ServerSocketMain {

    public static void main(String... args) {

        MultiThreadServer server = new MultiThreadServer(9000);
        new Thread(server).start();

    }
}
