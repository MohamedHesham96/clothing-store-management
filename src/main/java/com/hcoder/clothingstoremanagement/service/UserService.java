package com.hcoder.clothingstoremanagement.service;

import java.util.List;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

public interface UserService {

	public List<Bill> getAllBills();

	public Bill getBillByDate();

	public Client getClientById(int id);
	
	public List<Client> getAllClients();

	public List<Client> getClientRecords();

	public List<Incoming> GetAllIncoming();

	public Incoming getIncomingByDate();

	public Warehouse getWarehouseById(int id);

	public void updateWarehouseQuantity(Warehouse warehouse);

	public void addToWarehouse(Warehouse warehouse);

	public List<Warehouse> getAllWarehouse();

	public void AddIncoming(Incoming incoming);

	void addBill(Bill theBill);

	public List<Spending> getAllSpending();

	public int getClientsDraweeTotal();

	public int getSpendingTotal();

	public int getIcomingTotal();

	public int getWarehouseTotal();

}
