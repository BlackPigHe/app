import jdk.internal.dynalink.beans.StaticClass;


public class LanmbdaTest {
	public static void main(String[] args){
		Runnable runnable=() - >{System.out.println("hello world");};
	}
	
}
