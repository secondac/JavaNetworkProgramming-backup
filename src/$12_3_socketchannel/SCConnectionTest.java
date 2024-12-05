package $12_3_socketchannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class SCConnectionTest {
    private static int PORT = 8080;

    public static void main(String[] args) throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(ia, PORT);
        SocketChannel sc = SocketChannel.open();

        sc.configureBlocking(false);
        System.out.println("Is ConnectionPending 1: " + sc.isConnectionPending());
        sc.connect(isa);
        System.out.println("Is ConnectionPending 2: " + sc.isConnectionPending());
        sc.finishConnect();
        System.out.println("Is ConnectionPending 3: " + sc.isConnectionPending());

        System.out.println("Is Connected: " + sc.isConnected());
        System.out.println("Is Blocking Mode: " + sc.isBlocking());

    }
}
