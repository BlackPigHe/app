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
		// �Ӽ��̶�������
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));
		// ������д�������
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		// ��ȡ�ӷ��������ص�����
		BufferedReader bufr2 = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String line=null;
		while((line=bufr.readLine())!=null){
			if("over".equals(line))
				break;
			bufw.write(line);
			bufw.newLine();
			bufw.flush();
			//���������������ݵ�������
			
			String str = bufr2.readLine();
			System.out.println("����˷��ص�����"+str);
		}
		s.close();
	}

}
