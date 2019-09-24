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
 * 房间实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "rooms")
public class Room {

	private int number; // 房间编号

	private Roomtype roomtype; // 房间类型
	private int capacity; // 房间容量
	private String roomname; //房间的名字 用于辨识

	@OneToMany(mappedBy = "rooms" )
	private Set<Lesson> lessonList = new HashSet<Lesson>();
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@ManyToOne
	@JoinColumn(name = "roomtype")
	@Basic(fetch = FetchType.LAZY)
	public Roomtype getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(Roomtype roomtype) {
		this.roomtype = roomtype;
	}

	@Column
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Column
	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public Room(int number, Roomtype roomtype, int capacity, String roomname) {
		super();
		this.number = number;
		this.roomtype = roomtype;
		this.capacity = capacity;
		this.roomname = roomname;
	}

	public Room() {
		super();
	}

	public Room(Roomtype roomtype, int capacity, String roomname) {
		super();
		this.roomtype = roomtype;
		this.capacity = capacity;
		this.roomname = roomname;
	}
	
	
	

}
