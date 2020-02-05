package com.hcoder.clothingstoremanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Warehouse;

@Repository
public class UserDAOImpl implements UserDAO {

	private EntityManager entityManager;

	@Autowired
	public UserDAOImpl(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public List<Bill> getAllBills() {
	
		Session session = entityManager.unwrap(Session.class);

		Query<Bill> query = session.createQuery("from Bill");

		List<Bill> bills = query.getResultList();

		return bills;
		
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

		Session session = entityManager.unwrap(Session.class);

		Query<Incoming> query = session.createQuery("from Incoming");

		List<Incoming> incomings = query.getResultList();

		return incomings;
	}

	@Override
	public Incoming getIncomingByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToWarehouse(Warehouse warehouse) {

		Session session = entityManager.unwrap(Session.class);
		session.save(warehouse);
	}

	@Override
	public List<Warehouse> getAllWarehouse() {

		Session session = entityManager.unwrap(Session.class);

		List<Warehouse> items = session.createQuery("from Warehouse").getResultList();

		return items;
	}

	@Override
	public void AddIncoming(Incoming incoming) {

		Session session = entityManager.unwrap(Session.class);

		session.save(incoming);
	}

	@Override
	public void addBill(Bill theBill) {

		Session session = entityManager.unwrap(Session.class);

		session.save(theBill);

	}

	@Override
	public Warehouse getWarehouseById(int id) {

		Session session = entityManager.unwrap(Session.class);

		Warehouse warehouse = session.get(Warehouse.class, id);

		return warehouse;
	}

	@Override
	public void updateWarehouseQuantity(Warehouse warehouse) {

		Session session = entityManager.unwrap(Session.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + warehouse.getQuantity());
		session.update(warehouse);
	}

}
