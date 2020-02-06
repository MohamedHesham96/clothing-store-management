package com.hcoder.clothingstoremanagement.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

public interface UserDAO {

	public List<Bill> getAllBills();

	public Bill getBillByDate();

	public List<Client> getAllClients();

	public List<Client> getClientRecords();

	public List<Incoming> GetAllIncoming();

	public Incoming getIncomingByDate();

	public List<Warehouse> getAllWarehouse();

	public Warehouse getWarehouseById(int id);
	
	public void updateWarehouseQuantity(Warehouse warehouse);
	
	public void addToWarehouse(Warehouse warehouse);
	
	public void AddIncoming(Incoming incoming);

	public void addBill(Bill theBill);
	
	public int getSpendingTotal();

	public int getIcomingTotal();
	
	public int getWarehouseTotal();
}
