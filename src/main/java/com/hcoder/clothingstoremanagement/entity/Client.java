package com.hcoder.clothingstoremanagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "drawee")
	int drawee;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "phone")
	String phone;
		
	@OneToMany( mappedBy = "client", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ClientRecord> clientRecords;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getDrawee() {
		return drawee;
	}
	public void setDrawee(int drawee) {
		this.drawee = drawee;
	}
	
	
}
