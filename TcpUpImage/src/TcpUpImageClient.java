import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class TcpUpImageClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket s=new Socket("192.168.186.1",10008);
		FileInputStream fis=new FileInputStream("1.png");
		OutputStream outputStream = s.getOutputStream();
		int len=0;
		byte[] data=new byte[1024];
		while((len=fis.read(data))!=-1){
			outputStream.write(data,0,len);
			//System.out.println("客户端："+new String(data,0,len));
			
		}
		s.shutdownOutput();
		InputStream inputStream = s.getInputStream();
		byte[] Sdata=new byte[1024];
		int num=0;
		while((num=inputStream.read(Sdata))!=-1){
			System.out.println("收到服务器端的数据："+new String(Sdata,0,num));
		}
		fis.close();
		s.close();
		
	}

}
