package com.fy.lesson.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fy.common.entity.MaterialEnums.Status;
import com.fy.customer.entity.Customer;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Room;

/**
 * 
 * 排课实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "lessons")
public class Lesson {

	private int id;
	private Course courseType; // 课程类型编号
	private Instructor instructor;
	private Room room;
	private Date starttime; // 授课开始时间
	private Date endtime; // 授课结束时间
	private Set<Customer> students = new HashSet<Customer>(); // 已报名的学员
	private Status status; // 当前课程状态

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "number" )
	@Basic(fetch=FetchType.EAGER)
	public Course getCourseType() {
		return courseType;
	}

	public void setCourseType(Course courseType) {
		this.courseType = courseType;
	}


	
	@ManyToOne(targetEntity = Instructor.class)
	@JoinColumn(name = "instructor" )
	@Basic(fetch=FetchType.EAGER)
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	
	@ManyToOne(targetEntity = Room.class)
	@JoinColumn(name = "room" )
	@Basic(fetch=FetchType.LAZY)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
	@Column
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	@Column
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

//	@ElementCollection(fetch = FetchType.EAGER, // 加载策略
//			targetClass = Customer.class ) // 指定元素中集合的类型
//	@CollectionTable(name = "LE_ST_INFO" )
//	@CollectionTable(name = "LE_ST_INFO",indexes = {@Index(name="students_c_id", columnList="students_c_id")} )// 指定集合生成的表
//	@Column(unique=false)
	
	@ManyToMany(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinTable(  name="lesson_customer_Info",                    //中间表的名字
            	joinColumns= {@JoinColumn(name="id")},        //外键的字段
            	inverseJoinColumns= {@JoinColumn(name="c_id")})    //反转控制字段的名字
	public Set<Customer> getStudents() {
		return students;
	}

	public void setStudents(Set<Customer> students) {
		this.students = students;
	}

	@Enumerated(EnumType.STRING)
	@Column
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Lesson(Course courseType, Instructor instructor, Room room, Date starttime) {
		super();
		this.courseType = courseType;
		this.instructor = instructor;
		this.room = room;
		this.starttime = starttime;
	
	}

	public Lesson() {
		super();
	}

	
}
