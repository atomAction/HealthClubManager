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

/**
 * 
 * 房间类型实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="roomtypes")
public class Roomtype {

	private int number; // 房间类型编号
	private String name; // 房间类型名称

	@OneToMany(mappedBy = "roomtypes")
	private Set<Room> rooms = new HashSet<Room>();

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

}
