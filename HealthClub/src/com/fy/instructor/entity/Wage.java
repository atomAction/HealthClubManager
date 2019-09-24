
	package com.fy.instructor.entity;




import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "wages")
public class Wage {

	private int id;
	private Instructor instructor;
	private double monthWage;
	private Date month;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="insid")
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	@Column
	public double getMonthWage() {
		return monthWage;
	}

	public void setMonthWage(double monthWage) {
		this.monthWage = monthWage;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
//	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Wage(Instructor instructor, double monthWage, Date month) {
		super();
		this.instructor = instructor;
		this.monthWage = monthWage;
		this.month = month;
	}

	public Wage() {
		super();
	}


	
	
	

}


