package com.hcoder.clothingstoremanagement.dao;

import java.util.List;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

public interface UserDAO {

	// Bill Methods
	public void addBill(Bill theBill);

	public List<Bill> getAllBills();

	public List<Bill> getBillsByDate(String date);

	public void payOffAmount(Client client);

	// Client Methods
	public int getClientsDraweeTotal();

	public Client getClientById(int id);

	public List<Client> getAllClients();

	public void saveClient(Client client);

	// Client Records Method
	public ClientRecord getClientRecordById(int id);

	public void saveClientRecord(ClientRecord clientRecord);

	// Incoming Methods
	public List<Incoming> GetIncomingsByDate(String date);

	public List<Incoming> GetAllIncoming();

	public void AddIncoming(Incoming incoming);

	public int getIcomingTotal();

	// Warehouse Methods
	public int getWarehouseTotal();

	public void addToWarehouse(Warehouse warehouse);

	public void updateWarehouseQuantity(Warehouse warehouse);

	public Warehouse getWarehouseById(int id);

	public List<Warehouse> getAllWarehouse();

	// Spending Methods
	public int getSpendingTotal();
	
	public int getSpendingTotalToday();

	public void makeSpendingOpertaion(Spending spending);

	public List<Spending> getSpendingsByDate(String date);

	public List<Spending> getAllSpending();

	public List<Object> getGainByMonthes();

	
	// Traders
	public List<Trader> getAllTraders();
	public void saveTrader(Trader trader);

	
}
