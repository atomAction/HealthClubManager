package com.fy.instructor.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fy.lesson.entity.Lesson;

/**
 * 课程实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "courses")
public class Course {

	private int number; // 课程编号
	private String name; // 课程名称

	private int profession_number; // 授课资格专业职级编号
	private int roomtype_number; // 课程所需房间类型编号
	private int coursetime; // 单次授课时间
	private double coursefee; // 单次授课费用
	private String freecardtype; // 可免费的客户卡类型
	private boolean ispersonal; // 是否为私教课程
	
	@OneToMany(mappedBy = "courses")
	private Set<Lesson> lessonSet = new HashSet<Lesson>();
	
	@OneToMany(mappedBy = "courses")
	private Set<Instructor> instructorSet = new HashSet<Instructor>();
//	@ManyToOne
//	private Instructor instructor;

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public int getProfession_number() {
		return profession_number;
	}

	public void setProfession_number(int profession_number) {
		this.profession_number = profession_number;
	}

	@Column
	public int getRoomtype_number() {
		return roomtype_number;
	}

	public void setRoomtype_number(int roomtype_number) {
		this.roomtype_number = roomtype_number;
	}

	@Column
	public int getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(int coursetime) {
		this.coursetime = coursetime;
	}

	@Column
	public double getCoursefee() {
		return coursefee;
	}

	public void setCoursefee(double coursefee) {
		this.coursefee = coursefee;
	}

	@Column
	public String getFreecardtype() {
		return freecardtype;
	}

	public void setFreecardtype(String freecardtype) {
		this.freecardtype = freecardtype;
	}

	@Column
	public boolean isIspersonal() {
		return ispersonal;
	}

	public void setIspersonal(boolean ispersonal) {
		this.ispersonal = ispersonal;
	}





}
