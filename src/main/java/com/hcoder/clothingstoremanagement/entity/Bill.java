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
	int tradePrice;

	@Column(name = "quantity")
	int quantity;

	@Column(name = "piece_price")
	int piecePrice;

	@Column(name = "date")
	String date;

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

	public int getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(int tradePrice) {
		this.tradePrice = tradePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPiecePrice() {
		return piecePrice;
	}

	public void setPiecePrice(int piecePrice) {
		this.piecePrice = piecePrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String string) {
		this.date = string;
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
