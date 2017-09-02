import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getMail {

	public static void main(String[] args) throws Exception {
		patternMail();
	}

	public static void patternMail() throws Exception {
		URL url = new URL("http://www.iteye.com/problems/76163");
		URLConnection openConnection =  url.openConnection();
		InputStream inputStream = openConnection.getInputStream();
		
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				inputStream,"utf-8"));
		
		String line=null;
		String reg="[a-zA-Z_]+@(\\.[a-zA-Z])+";
		String regex="[h][t][t][p]://\\w+(\\.\\w+)+(/\\w+)+";
		
		//拿到pattern对象
		Pattern pattern = Pattern.compile(reg);
		while((line=bufr.readLine())!=null){
				System.out.println(line);
			//在拿到匹配的对象Matches
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()){
					System.out.println(matcher.group());
					//System.out.println("hehe");
				}
				
		}
		
	}

}
