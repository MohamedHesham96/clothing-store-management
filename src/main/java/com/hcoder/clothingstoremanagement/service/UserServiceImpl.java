package com.hcoder.clothingstoremanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcoder.clothingstoremanagement.dao.UserDAO;
import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
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
	public List<Client> getClientRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incoming> GetAllIncoming() {
		// TODO Auto-generated method stub

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

}
