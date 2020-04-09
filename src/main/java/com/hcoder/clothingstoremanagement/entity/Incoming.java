package com.hcoder.clothingstoremanagement.entity;

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

	@Column(name = "current_quantity")
	int currentQuantity;

	@Column(name = "piece_price")
	int piecePrice;

	@Column(name = "date")
	String date;

	@Column(name = "item")
	String item;

	@Column(name = "trader")
	String trader;

	@Column(name = "total")
	int total;

	@Column(name = "trade_price")
	int tradePrice;

	public Incoming() {

	}

	public Incoming(int quantity, int piece_price, String item, String trader, int total, int trade_price) {
		this.quantity = quantity;
		this.piecePrice = piece_price;
		this.item = item;
		this.trader = trader;
		this.total = total;
		this.tradePrice = trade_price;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String gettrader() {
		return trader;
	}

	public void settrader(String trader) {
		this.trader = trader;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPiecePrice() {
		return piecePrice;
	}

	public void setPiecePrice(int piecePrice) {
		this.piecePrice = piecePrice;
	}

	public int getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(int tradePrice) {
		this.tradePrice = tradePrice;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

}
