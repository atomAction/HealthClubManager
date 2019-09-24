package com.fy.customer.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 客户卡类型实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "card_types")
@Embeddable
public class CardType  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int t_id;
	private String typename;
	private double discount;
	private int effectivetime;
	private double price;

	@OneToMany(mappedBy = "card_types")
	private Set<Customer> customers = new HashSet<Customer>();

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	@Column
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column
	public int getEffectivetime() {
		return effectivetime;
	}

	public void setEffectivetime(int effectivetime) {
		this.effectivetime = effectivetime;
	}

	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
