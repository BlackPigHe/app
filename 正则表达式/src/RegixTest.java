import java.util.ArrayList;
import java.util.Set;
import java.util.*;


public class RegixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ipGuanli();
	}
	public static void ipGuanli(){
		String ip="192.168.10 196.1.2.3 10.2.3.4 2.6.7.85";
		//将ip排序
		//1，将每一段全部加2个零,替换
		 ip=ip.replaceAll("(\\d+)", "00$1");
		 System.out.println(ip);
		//将每段变成3位数
		 ip=ip.replaceAll("0*(\\d{3})", "$1");
		 System.out.println(ip);
		 //进行自然排序
		 String[] srr=ip.split(" ");
		 TreeSet<String> ts=new TreeSet<String>();
		 for(String s:srr){
			 ts.add(s);
		 }
		 for(String s:ts){
			 System.out.println(s.replaceAll("0*(\\d+)", "$1"));
			 
		 }
	}

}
