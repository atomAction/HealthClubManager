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
 * 专业职级实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name="professionRanks")
//@Embeddable
public class ProfessionRank {

	private int profession_number;			//专业职级编号
	private String profession_name;			//专业名称
	private String rank_name;				//职级名称
	private double group_classfee;			//团课课时费
	private double personal_calssfee;		//私教课时费
	private float personal_calssfee_commission;		//私教课时提成比例
	
	@OneToMany(mappedBy = "professionRanks")
	private Set<Instructor> instructors = new HashSet<Instructor>();

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getProfession_number() {
		return profession_number;
	}

	public void setProfession_number(int profession_number) {
		this.profession_number = profession_number;
	}
	@Column
	public String getProfession_name() {
		return profession_name;
	}

	public void setProfession_name(String profession_name) {
		this.profession_name = profession_name;
	}
	@Column
	public String getRank_name() {
		return rank_name;
	}

	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}
	@Column
	public double getGroup_classfee() {
		return group_classfee;
	}
	
	public void setGroup_classfee(double group_classfee) {
		this.group_classfee = group_classfee;
	}
	@Column
	public double getPersonal_calssfee() {
		return personal_calssfee;
	}

	public void setPersonal_calssfee(double personal_calssfee) {
		this.personal_calssfee = personal_calssfee;
	}
	@Column
	public float getPersonal_calssfee_commission() {
		return personal_calssfee_commission;
	}

	public void setPersonal_calssfee_commission(float personal_calssfee_commission) {
		this.personal_calssfee_commission = personal_calssfee_commission;
	}

	

}
