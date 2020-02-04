package com.hcoder.clothingstoremanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "gain")
	int gain;

	@Column(name = "trade_price")
	int trade_price;

	@Column(name = "quantity")
	int quantity;

	@Column(name = "piece_price")
	int piece_price;

	@Column(name = "date")
	Date date;

	@Column(name = "item")
	String item;

	@Column(name = "store")
	String store;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

	public int getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(int trade_price) {
		this.trade_price = trade_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPiece_price() {
		return piece_price;
	}

	public void setPiece_price(int piece_price) {
		this.piece_price = piece_price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getStore() {
		return store;

	}

	public void setStore(String store) {
		this.store = store;
	}

}
