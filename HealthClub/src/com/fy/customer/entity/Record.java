package com.fy.customer.entity;

import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fy.common.entity.MaterialEnums.RecordTypes;

@Entity
@Table(name = "records")
public class Record {

	private int id;
	private Date date;
	private double amount;
	private RecordTypes recordType;

	private Customer customer;

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.STRING)
	@Column
	public RecordTypes getRecordType() {
		return recordType;
	}

	public void setRecordType(RecordTypes recordType) {
		this.recordType = recordType;
	}

	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customer" )
	@Basic(fetch=FetchType.LAZY)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Record(Date date, double amount, RecordTypes recordType, Customer customer) {
		super();
		this.date = date;
		this.amount = amount;
		this.recordType = recordType;
		this.customer = customer;
	}

	public Record() {
		super();
	}

	public Record(int id, Date date, double amount, RecordTypes recordType, Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.recordType = recordType;
		this.customer = customer;
	}

	
	
}
