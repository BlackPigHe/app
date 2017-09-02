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
		// �õ��ӿͻ��˴���������
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		// ���ͻ��˴�ȥ����
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		String line=null;
		while((line=bufr.readLine())!=null){
			if("over".equals(line))
				break;
			System.out.println("������ǿͻ��˵����ݣ�"+line);
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		
	}
}
