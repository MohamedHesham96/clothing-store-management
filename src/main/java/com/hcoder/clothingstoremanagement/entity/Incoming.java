package com.hcoder.clothingstoremanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incoming")
public class Incoming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

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

	@Column(name = "total")
	int total;

	@Column(name = "trade_price")
	int trade_price;

	public Incoming(int quantity, int piece_price, String item, String store, int total, int trade_price) {
		this.quantity = quantity;
		this.piece_price = piece_price;
		this.item = item;
		this.store = store;
		this.total = total;
		this.trade_price = trade_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(int trade_price) {
		this.trade_price = trade_price;
	}

}
