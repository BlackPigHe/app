import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpUpImageServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws  Exception{
		ServerSocket ss=new ServerSocket(10008);
		Socket s = ss.accept();
		InputStream inputStream = s.getInputStream();
		int len=0;
		byte[] data=new byte[1024];
		FileOutputStream fos=new FileOutputStream("2.png");
		while((len = inputStream.read(data))!=-1){
			fos.write(data,0,len);
		if(len!=-1){
			
			//System.out.println("数据为："+new String(data,0,len));
		}
			
		else 
			System.out.println("传输失败");
			
		}
		
		
	}

}
