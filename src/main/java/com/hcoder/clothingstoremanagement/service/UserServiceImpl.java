package com.hcoder.clothingstoremanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcoder.clothingstoremanagement.dao.UserDAO;
import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Result;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<Bill> getAllBills() {

		return userDAO.getAllBills();
	}

	@Override
	public List<Client> getAllClients() {

		return userDAO.getAllClients();
	}

	@Override
	public List<Incoming> GetAllIncoming() {

		return userDAO.GetAllIncoming();
	}

	@Override
	public List<Warehouse> getAllWarehouse() {

		return userDAO.getAllWarehouse();
	}

	@Override
	public void AddIncoming(Incoming incoming) {

		userDAO.AddIncoming(incoming);
	}

	@Override
	public void addToWarehouse(Warehouse warehouse) {

		userDAO.addToWarehouse(warehouse);
	}

	@Override
	public void addBill(Bill theBill) {

		userDAO.addBill(theBill);
	}

	@Override
	public Warehouse getWarehouseById(int id) {

		Warehouse warehouse = userDAO.getWarehouseById(id);

		return warehouse;
	}

	@Override
	public void updateWarehouseQuantity(Warehouse warehouse) {

		userDAO.updateWarehouseQuantity(warehouse);
	}

	@Override
	public int getSpendingTotal() {

		int spendingTotal = userDAO.getSpendingTotal();

		return spendingTotal;
	}

	@Override
	public int getIcomingTotal() {

		return userDAO.getIcomingTotal();
	}

	@Override
	public int getWarehouseTotal() {

		return userDAO.getWarehouseTotal();
	}

	@Override
	public List<Spending> getAllSpending() {

		return userDAO.getAllSpending();
	}

	@Override
	public int getClientsDraweeTotal() {

		return userDAO.getClientsDraweeTotal();
	}

	@Override
	public Client getClientById(int id) {

		return userDAO.getClientById(id);
	}

	@Override
	public void saveClientRecord(ClientRecord clientRecord) {

		userDAO.saveClientRecord(clientRecord);
	}

	@Override
	public void makeSpendingOpertaion(Spending spending) {

		userDAO.makeSpendingOpertaion(spending);
	}

	@Override
	public void saveClient(Client client) {

		userDAO.saveClient(client);
	}

	@Override
	public List<Spending> getSpendingsByDate(String date) {

		return userDAO.getSpendingsByDate(date);
	}

	@Override
	public List<Incoming> GetIncomingsByDate(String date) {

		return userDAO.GetIncomingsByDate(date);
	}

	@Override
	public List<Bill> getBillsByDate(String date) {

		return userDAO.getBillsByDate(date);
	}

	@Override
	public void payOffAmount(Client client) {

		userDAO.payOffAmount(client);
	}

	@Override
	public ClientRecord getClientRecordById(int id) {

		return userDAO.getClientRecordById(id);
	}

	@Override
	public int getSpendingTotalToday() {

		return userDAO.getSpendingTotalToday();
	}

	@Override
	public List<Trader> getAllTraders() {

		return userDAO.getAllTraders();
	}

	@Override
	public void saveTrader(Trader trader) {

		Trader currentTrader = userDAO.getTraderById(trader.getId());

		if (!(currentTrader == null)) {

			userDAO.updateIncomingTraderName(currentTrader.getName(), trader.getName());

			userDAO.saveTrader(trader);

		} else {

			userDAO.saveTrader(trader);

		}
	}

	@Override
	public int[] getTradersTotals() {

		return userDAO.getTradersTotals();
	}

	@Override
	public List<Trader> getAllTradersWithRemaining() {

		return userDAO.getAllTradersWithRemaining();
	}

	@Override
	public List<Trader> getAllTradersWithoutRemaining() {

		return userDAO.getAllTradersWithoutRemaining();
	}

	@Override
	public Trader getTraderById(int id) {

		return userDAO.getTraderById(id);
	}

	@Override
	public int getIncomingTotalByTraderName(String traderName) {

		return userDAO.getIncomingTotalByTraderName(traderName);
	}

	@Override
	public List<Incoming> getIncomingsByTraderName(String traderName) {

		return userDAO.getIncomingsByTraderName(traderName);
	}

	@Override
	public Trader getTraderByName(String name) {

		return userDAO.getTraderByName(name);
	}

	@Override
	public int getSpendingTotalByDate(String date) {

		return userDAO.getSpendingTotalByDate(date);
	}

	@Override
	public List<Result> groupByMonths() {

		return userDAO.groupByMonths();
	}

	@Override
	public void deleteClient(int id) {

		userDAO.deleteClient(id);
	}

	@Override
	public void deleteTrader(int id) {

		userDAO.deleteTrader(id);
	}

	@Override
	public void updateIncomingTraderName(String traderName, String newTraderName) {

		userDAO.updateIncomingTraderName(traderName, newTraderName);

	}

	@Override
	public void deleteSpending(int id) {

		userDAO.deleteSpending(id);
	}

}
