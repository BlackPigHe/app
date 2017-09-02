import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class getFile {
	public static void main(String[] args) throws Exception {
		File f=new File("C:\\Users\\Administrator\\Desktop\\web.xml");
		BufferedReader bufr=new BufferedReader(new FileReader(f));
		String regex="[h][t][t][p]://\\w+(\\.\\w+)+(/\\w+)+";
		Pattern pattern = Pattern.compile(regex);
		String line=null;
		while((line=bufr.readLine())!=null){
			System.out.println(line);
			Matcher m = pattern.matcher(line);
			while(m.find()){
				System.out.println(m.group());
			}
		}
	}

}
