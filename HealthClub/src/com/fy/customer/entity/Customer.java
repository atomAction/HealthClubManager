package com.fy.customer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "customers")
public class Customer {

	

	private int c_id;
	private String phone;
	private String name;
	private double balance;

	private Date effectivedeadline;
	
	private CardType cardtype;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	@PrimaryKeyJoinColumn
	private CustomerFile customerfile;


	@OneToMany(mappedBy = "customers")
	private Set<Record> records = new HashSet<Record>();

	@Id
//	@GenericGenerator(name="systemUUID",strategy="org.hibernate.id.UUIDGenerator")
//	@GeneratedValue(generator="systemUUID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "c_id", nullable = false)
	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "balance", length = 32)
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@Column(name = "effective_deadline")
	public Date getEffectivedeadline() {
		return effectivedeadline;
	}

	public void setEffectivedeadline(Date effectivedeadline) {
		this.effectivedeadline = effectivedeadline;
	}
	
	@ManyToOne
	@JoinColumn(name = "cardtype",referencedColumnName="typename" )
	@Basic(fetch=FetchType.LAZY)
	public CardType getCardtype() {
		return cardtype;
	}

	public void setCardtype(CardType cardtype) {
		this.cardtype = cardtype;
	}
	
	

	@Override
	public String toString() {
		return "Customer [c_id=" + c_id + ", phone=" + phone + ", name=" + name + ", balance=" + balance
				+ ", effectivedeadline=" + effectivedeadline + ", cardtype=" + cardtype + "]";
	}

	public Customer( String phone, String name, double balance, Date effectivedeadline) {
		super();
		this.phone = phone;
		this.name = name;
		this.balance = balance;
		this.effectivedeadline = effectivedeadline;
	}

	public Customer() {
		super();
	}

	
}
