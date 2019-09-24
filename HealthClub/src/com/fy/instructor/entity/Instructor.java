package com.fy.instructor.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fy.lesson.entity.Lesson;

/**
 * 教练实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "instructors")
public class Instructor {

	private int jobnumber; // 工号
	private String name; // 姓名
	private String sex; // 性别
	private String photo; // 靓照
	private double basicsalary; // 底薪

	private Course courses; // 可授课程

	private ProfessionRank professionranks; // 专业职级
	
	@OneToMany(mappedBy = "instructors" )
	private Set<Lesson> lessonList = new HashSet<Lesson>();

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(int jobnumber) {
		this.jobnumber = jobnumber;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column
	public double getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(double basicsalary) {
		this.basicsalary = basicsalary;
	}

	
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "courses" )
	@Basic(fetch=FetchType.LAZY)
	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	@ManyToOne(targetEntity = ProfessionRank.class)
	@JoinColumn(name = "rankname" )
	@Basic(fetch=FetchType.LAZY)
	public ProfessionRank getProfessionranks() {
		return professionranks;
	}

	public void setProfessionranks(ProfessionRank professionranks) {
		this.professionranks = professionranks;
	}

	public Instructor(int jobnumber, String name, String sex, String photo, double basicsalary, Course courses,
			ProfessionRank professionranks) {
		super();
		this.jobnumber = jobnumber;
		this.name = name;
		this.sex = sex;
		this.photo = photo;
		this.basicsalary = basicsalary;
		this.courses = courses;
		this.professionranks = professionranks;
	}

	public Instructor() {
		super();
	}
	
	

	
}
