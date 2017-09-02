package org.lanqiao.entity;

public class Student {
	private String name;
	private int id;
	private String major;
	private String sex;
	private String grade;
	private int age;
	private String school;
	private String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Student(String name, int id, String major, String sex, String grade,
			int age, String school, String time) {
		this.name = name;
		this.id = id;
		this.major = major;
		this.sex = sex;
		this.grade = grade;
		this.age = age;
		this.school = school;
		this.time = time;
	}
	public Student() {
	}
	@Override
	public String toString() {
		return name+"\t" + id + "\t" + sex+ "\t" + age + "\t" + major + "\t" + school+ "\t" + grade + "\t" + time;
	}
	
}
