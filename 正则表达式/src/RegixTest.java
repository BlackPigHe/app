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
		//��ip����
		//1����ÿһ��ȫ����2����,�滻
		 ip=ip.replaceAll("(\\d+)", "00$1");
		 System.out.println(ip);
		//��ÿ�α��3λ��
		 ip=ip.replaceAll("0*(\\d{3})", "$1");
		 System.out.println(ip);
		 //������Ȼ����
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
