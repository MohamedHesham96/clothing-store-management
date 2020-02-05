package com.hcoder.clothingstoremanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcoder.clothingstoremanagement.dao.UserDAO;
import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill getBillByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
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
	public Incoming getIncomingByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warehouse> getAllWarehouse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddIncoming(Incoming incoming) {

		userDAO.AddIncoming(incoming);
	}

}
