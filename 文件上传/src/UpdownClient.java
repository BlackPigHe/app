import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class UpdownClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Socket s=new Socket("192.168.186.1",10007);
		BufferedReader bufr=new BufferedReader(new FileReader("D:/hehe.txt"));
		
		PrintWriter pw=new PrintWriter(s.getOutputStream(), true);
		String line=null;
		while((line=bufr.readLine())!=null){
			pw.write(line);
		}
	}

}
