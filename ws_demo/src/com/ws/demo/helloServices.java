package com.ws.demo;


import javax.jws.WebService;
import javax.xml.ws.Endpoint;
@WebService
public class helloServices {
	
	public String sayHello(String name){
		return "hello"+name;
	}

	
	public static void main(String[] args) {
		Endpoint.publish("http://192.168.43.244:10000/hello", new helloServices());
	}

}
