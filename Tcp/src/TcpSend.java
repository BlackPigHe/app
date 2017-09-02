import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSend {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 10005);
		// OutputStream os = s.getOutputStream();
		// os.write("ge men lai le hehe ".getBytes());
		// os.close();
		// 从键盘读入数据
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));
		// 将数据写给服务端
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		// 获取从服务器返回的数据
		BufferedReader bufr2 = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String line=null;
		while((line=bufr.readLine())!=null){
			if("over".equals(line))
				break;
			bufw.write(line);
			bufw.newLine();
			bufw.flush();
			//将服务器返回数据的流读出
			
			String str = bufr2.readLine();
			System.out.println("服务端返回的数据"+str);
		}
		s.close();
	}

}
