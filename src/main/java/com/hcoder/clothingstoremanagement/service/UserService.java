package com.hcoder.clothingstoremanagement.service;

import java.util.List;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Result;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Trader;

public interface UserService {

	// Bill Methods
		public void addBill(Bill theBill);

		public List<Bill> getAllBills();

		public List<Bill> getBillsByDate(String date);

		public void payOffAmount(Client client);

		public Bill deleteBill(int id);

		// Client Methods

		public void deleteClient(int id);

		public int getClientsDraweeTotal();

		public Client getClientById(int id);

		public List<Client> getAllClients();

		public void saveClient(Client client);

		// Client Records Method
		public List<Bill> getClientBills(int clientId);
		
		// Incoming Methods
		public List<Incoming> GetIncomingsByDate(String date);

		public List<Incoming> getIncomingsByTraderName(String traderName);

		public List<Incoming> getAllIncoming();
		
		public List<Incoming> getAllAvailableIncoming();

		public void AddIncoming(Incoming incoming);

		public int getIcomingTotal();
	
		public void deleteIncoming(int id);

		public Incoming getIncomingById(int id);

		public int getIncomingTotalByTraderName(String traderName);


		// Warehouse Methods
		public int getWarehouseTotal();

		public void updateIncomingCurrentQuantity(Incoming incoming);

		//public Warehouse getWarehouseById(int id);
		
		
		// Spending Methods
		public int getSpendingTotal();

		public int getSpendingTotalToday();

		public void deleteSpending(int id);

		public int getSpendingTotalByDate(String date);

		public void makeSpendingOpertaion(Spending spending);

		public List<Spending> getSpendingsByDate(String date);

		public List<Spending> getAllSpending();

		// Traders
		public void deleteTrader(int id);

		public Trader getTraderById(int id);

		public void updateIncomingTraderName(String traderName, String newTraderName);

		public List<Trader> getAllTraders();

		public List<Trader> getAllTradersWithRemaining();

		public List<Trader> getAllTradersWithoutRemaining();

		public void saveTrader(Trader trader);

		public int[] getTradersTotals();

		public Trader getTraderByName(String name);

		// SUMMRY
		public List<Result> groupByMonths();


}
