package com.hcoder.clothingstoremanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
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

		Query<Bill> query = session.createQuery("from Bill order by date desc");

		List<Bill> bills = query.getResultList();

		return bills;

	}

	@Override
	public List<Bill> getBillsByDate(String date) {

		Session session = entityManager.unwrap(Session.class);

		Query<Bill> query = session.createQuery("from Bill where date = :theDate");

		query.setParameter("theDate", date);

		List<Bill> bills = query.list();

		return bills;
	}

	@Override
	public List<Client> getAllClients() {

		Session session = entityManager.unwrap(Session.class);

		List<Client> clients = session.createQuery("from Client order by drawee desc").getResultList();

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

		Query<Incoming> query = session.createQuery("from Incoming order by date desc");

		List<Incoming> incomings = query.getResultList();

		return incomings;
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

		// Make it saveOrUpdate
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

		List<Incoming> items = session.createQuery("from Incoming ").getResultList();
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

		List<Warehouse> items = session.createQuery("from Warehouse ").getResultList();

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

		List<Spending> items = session.createQuery("from Spending order by date desc").getResultList();

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

	@Override
	public Client getClientById(int id) {

		Session session = entityManager.unwrap(Session.class);

		Client client = session.get(Client.class, id);

		return client;
	}

	@Override
	public void saveClientRecord(ClientRecord clientRecord) {

		Session session = entityManager.unwrap(Session.class);

		session.save(clientRecord);

	}

	@Override
	public void makeSpendingOpertaion(Spending spending) {

		Session session = entityManager.unwrap(Session.class);

		session.save(spending);
	}

	@Override
	@Transactional
	public void saveClient(Client client) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(client);
	}

	@Override
	public List<Spending> getSpendingsByDate(String date) {

		Session session = entityManager.unwrap(Session.class);

		Query<Spending> query = session.createQuery("from Spending where date = :theDate");

		query.setParameter("theDate", date);

		List<Spending> spendings = query.list();

		return spendings;
	}

	@Override
	public List<Incoming> GetIncomingsByDate(String date) {

		Session session = entityManager.unwrap(Session.class);

		Query<Incoming> query = session.createQuery("from Incoming where date = :theDate");

		query.setParameter("theDate", date);

		List<Incoming> incomings = query.list();

		return incomings;
	}

	@Override
	@Transactional
	public void payOffAmount(Client client) {

		Session session = entityManager.unwrap(Session.class);

		session.update(client);

		session.update(client.getClientRecords());

	}

	@Override
	public ClientRecord getClientRecordById(int id) {

		Session session = entityManager.unwrap(Session.class);

		ClientRecord theClientRecord = session.get(ClientRecord.class, id);

		return theClientRecord;
	}

}
