package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class StockApi {
	public final static String HOST = "download.finance.yahoo.com";
	public final static int PORT = 80;
	
	public static Socket yahoo;
	private static PrintWriter out;
	private static BufferedReader in;

	public static void start() throws IOException {
		yahoo = new Socket();
		yahoo.connect(addr, 10000);
		out = new PrintWriter(yahoo.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(yahoo.getInputStream()));
	}

	public static void close() throws IOException {
		yahoo.close();
		out.close();
		in.close();
	}
}
