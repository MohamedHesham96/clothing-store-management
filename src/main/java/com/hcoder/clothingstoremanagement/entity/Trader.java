package com.hcoder.clothingstoremanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trader")
public class Trader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	//@Pattern(regexp = "[ ء-ي]+", message = "ادخل الاسم بشكل صحيح")
	@Column(name = "name")
	String name;

	@Column(name = "payed")
	int payed;

	@Column(name = "remaining")
	int remaining;

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

	public int getPayed() {
		return payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

}
