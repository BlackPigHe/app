import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(11001);
		final Socket socket = ss.accept();
		final String ip = socket.getInetAddress().getHostAddress();
		/*new Thread(new Runnable() {

			public void run() {
				try {
					PrintWriter pw = new PrintWriter(
							socket.getOutputStream(),true);
					pw.println("你好客户端");
				} catch (Exception e) {
					System.out.println("异常");
					e.printStackTrace();
				}
			}

		}).start();*/
		PrintWriter pw = new PrintWriter(
				socket.getOutputStream(),true);
		pw.println("<font color='red'>你好客户端</font>");
		socket.close();
		ss.close();
	}

}
