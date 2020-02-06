package com.hcoder.clothingstoremanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
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

		Session session = entityManager.unwrap(Session.class);

		List<Client> clients = session.createQuery("from Client").getResultList();

		return clients;
	}

	@Override
	public List<ClientRecord> getClientRecords() {
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
	@Transactional
	public void updateWarehouseQuantity(Warehouse warehouse) {

		Session session = entityManager.unwrap(Session.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + warehouse.getQuantity());
		session.update(warehouse);
	}

	@Override
	public int getSpendingTotal() {

		Session session = entityManager.unwrap(Session.class);

		Query<Spending> query = session.createQuery("from Spending");

		List<Spending> spending = query.getResultList();

		int listSize = spending.size();
		int spendingTotal = 0;

		for (int i = 0; i < listSize; i++) {

			spendingTotal += spending.get(i).getMoney();
		}

		return spendingTotal;
	}

	@Override
	public int getIcomingTotal() {

		Session session = entityManager.unwrap(Session.class);

		List<Incoming> items = session.createQuery("from Incoming").getResultList();
		int listSize = items.size();

		int incomingTotal = 0;

		for (int i = 0; i < listSize; i++) {

			Incoming item = items.get(i);

			incomingTotal += item.getTotal();
		}

		return incomingTotal;
	}

	@Override
	public int getWarehouseTotal() {
		Session session = entityManager.unwrap(Session.class);

		List<Warehouse> items = session.createQuery("from Warehouse").getResultList();

		int listSize = items.size();

		int warehouseTotal = 0;

		for (int i = 0; i < listSize; i++) {

			Warehouse item = items.get(i);

			warehouseTotal += item.getQuantity() * item.getTradePrice();
		}

		return warehouseTotal;
	}

	@Override
	public List<Spending> getAllSpending() {

		Session session = entityManager.unwrap(Session.class);

		List<Spending> items = session.createQuery("from Spending").getResultList();

		return items;
	}

	@Override
	public int getClientsDraweeTotal() {

		Session session = entityManager.unwrap(Session.class);

		List<Client> clients = session.createQuery("from Client").getResultList();

		int listSize = clients.size();
		int draweeTotal = 0;

		for (int i = 0; i < listSize; i++) {

			draweeTotal += clients.get(i).getDrawee();
		}

		return draweeTotal;
	}

}
