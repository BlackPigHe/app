package org.lanqiao.entity;

public class Compare {
	private String name;
	private String city;
	private String grade;
	
	private String need;
	private int num;
	private String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Compare(String name, String city, String grade) {
		this.name = name;
		this.city = city;
		this.grade = grade;
	}
	public Compare(String name, String city, String need, int num, String time) {
		this.name = name;
		this.city = city;
		this.need = need;
		this.num = num;
		this.time = time;
	}
	public Compare() {
	}
	@Override
	public String toString() {
		return  name + "\t" + city + "\t" + need+ "\t" + num + "\t" + time ;
	}
}
