import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class TcpReceive {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(10005);
		Socket s = ss.accept();
		String name = s.getInetAddress().getHostName();
		System.out.println("ip:" + name);
		// 得到从客户端传来的数据
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		// 给客户端传去数据
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		String line=null;
		while((line=bufr.readLine())!=null){
			if("over".equals(line))
				break;
			System.out.println("你好我是客户端的数据："+line);
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		
	}
}
