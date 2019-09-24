package com.fy.customer.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户普通信息实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "customer_files")
public class CustomerFile {

	private int f_id;			
	private String sex;				//性别
	private String job;				//职业
	private Date birthday;			//出生年月
	private String aim;				//健身目标
	private int frequency;			//健身频率
	private String injuryhistory;	//伤病史
	private String hibit;			//生活习惯
	private double hight;			//身高
	private double wight;			//体重
	private double bmi;				//BMI值
	private double fatrate;			//体脂率
	private String heartfunction;	//心肺功能状况
	private Date recordtime;	//记录时间

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Customer customer;		

	
	
	@Id
	@Column(name = "f_id", nullable = false, unique = true)
	@GeneratedValue(generator = "myForeignGenerator")
	@GenericGenerator(name="myForeignGenerator",
	strategy = "foreign",
	parameters = @Parameter(name="property",value="customer"))
	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	@Column
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column
	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}
	@Column
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	@Column(name="injury_history")
	public String getInjuryhistory() {
		return injuryhistory;
	}

	public void setInjuryhistory(String injuryhistory) {
		this.injuryhistory = injuryhistory;
	}
	@Column
	public String getHibit() {
		return hibit;
	}

	public void setHibit(String hibit) {
		this.hibit = hibit;
	}

	@Column
	public double getHight() {
		return hight;
	}

	public void setHight(double hight) {
		this.hight = hight;
	}

	@Column
	public double getWight() {
		return wight;
	}

	public void setWight(double wight) {
		this.wight = wight;
	}

	@Column
	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	@Column
	public double getFatrate() {
		return fatrate;
	}

	public void setFatrate(double fatrate) {
		this.fatrate = fatrate;
	}

	@Column
	public String getHeartfunction() {
		return heartfunction;
	}

	public void setHeartfunction(String heartfunction) {
		this.heartfunction = heartfunction;
	}

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}

	public CustomerFile() {
		super();
	}

	
}
