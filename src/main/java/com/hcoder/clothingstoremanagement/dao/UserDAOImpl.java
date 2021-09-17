package com.hcoder.clothingstoremanagement.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.hcoder.clothingstoremanagement.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        Query<Bill> query = session.createQuery("from Bill order by TIMESTAMP desc");

        List<Bill> bills = query.getResultList();

        return bills;

    }

    @Override
    public List<Bill> getBillsByDate(String date) {

        Session session = entityManager.unwrap(Session.class);

        Query<Bill> query = session.createQuery("from Bill where date = :theDate order by TIMESTAMP desc");

        query.setParameter("theDate", date);

        List<Bill> bills = query.list();

        return bills;
    }

    @Override
    public List<Client> getAllClients() {

        Session session = entityManager.unwrap(Session.class);

        List<Client> clients = session.createQuery("from Client where id > 0 order by drawee desc").getResultList();

        return clients;
    }

    @Override
    public List<Incoming> getAllIncoming() {

        Session session = entityManager.unwrap(Session.class);

        Query<Incoming> query = session.createQuery("from Incoming order by TIMESTAMP desc");

        List<Incoming> incomings = query.getResultList();

        return incomings;
    }

    @Override
    public List<Incoming> getIncomingsByItemName(String itemName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Incoming> query = session.createQuery(
                "from Incoming where item like CONCAT('%',?1,'%') and current_quantity > 0 order by TIMESTAMP desc");

        query.setParameter(1, itemName);

        List<Incoming> incomings = query.getResultList();

        return incomings;

    }

    @Override
    public List<Incoming> getAllAvailableIncoming() {

        Session session = entityManager.unwrap(Session.class);

        List<Incoming> items = session.createQuery("from Incoming where current_quantity > 0 order by TIMESTAMP desc")
                .getResultList();

        return items;
    }

    @Override
    public void saveIncoming(Incoming incoming) {

        Session session = entityManager.unwrap(Session.class);

        session.save(incoming);
    }

    @Override
    public void addBill(Bill theBill) {

        Session session = entityManager.unwrap(Session.class);

        session.save(theBill);

    }

    @Override
    @Transactional
    public void updateIncomingCurrentQuantity(Incoming incoming) {

        Session session = entityManager.unwrap(Session.class);

        // Make it saveOrUpdate
        session.update(incoming);
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
    public int getEarningsTotalByTraderName(String traderName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Bill> query = session.createQuery("from Bill where trader = :traderName");

        query.setParameter("traderName", traderName);

        List<Bill> bills = query.getResultList();

        int EarningsTotal = 0;

        for (Bill bill : bills) {

            EarningsTotal += bill.getQuantity() * (bill.getPiecePrice() - bill.getTradePrice());
        }

        return EarningsTotal;
    }

    @Override
    public int getWarehouseTotal() {

        Session session = entityManager.unwrap(Session.class);

        List<Incoming> items = session
                .createQuery("from Incoming where current_quantity <= quantity AND current_quantity > 0")
                .getResultList();

        int listSize = items.size();

        int warehouseTotal = 0;

        for (int i = 0; i < listSize; i++) {

            Incoming item = items.get(i);

            warehouseTotal += item.getCurrentQuantity() * item.getTradePrice();
        }

        return warehouseTotal;
    }

    @Override
    public List<Spending> getAllSpending() {

        Session session = entityManager.unwrap(Session.class);

        List<Spending> items = session.createQuery("from Spending order by TIMESTAMP desc").getResultList();

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

        Query<Spending> query = session.createQuery("from Spending where date = :theDate order by TIMESTAMP desc");

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

        session.update(client.getBills());

    }

    @Override
    public int getSpendingTotalToday() {

        String theDate = LocalDate.now().toString();

        Session session = entityManager.unwrap(Session.class);

        Query<Spending> query = session.createQuery("from Spending where date = :theDate");

        query.setParameter("theDate", theDate);

        List<Spending> spendings = query.list();

        int listSize = spendings.size();

        int spendingTotal = 0;

        for (int i = 0; i < listSize; i++) {

            spendingTotal += spendings.get(i).getMoney();
        }

        return spendingTotal;
    }

    @Override
    public List<Trader> getAllTraders() {

        Session session = entityManager.unwrap(Session.class);

        List<Trader> traders = session.createQuery("from Trader where id > -1 order by remaining desc").getResultList();

        return traders;
    }

    @Override
    @Transactional
    public void saveTrader(Trader trader) {

        Session session = entityManager.unwrap(Session.class);

        session.merge(trader);

    }

    @Override
    public int[] getTradersTotals() {

        Session session = entityManager.unwrap(Session.class);

        Query<Trader> query = session.createQuery("from Trader");

        List<Trader> traders = query.getResultList();

        int listSize = traders.size();

        int[] tradersTotals = new int[2];

        for (int i = 0; i < listSize; i++) {

            tradersTotals[0] += traders.get(i).getPayed();
            tradersTotals[1] += traders.get(i).getRemaining();

        }

        return tradersTotals;

    }

    @Override
    public List<Trader> getAllTradersWithRemaining() {

        Session session = entityManager.unwrap(Session.class);

        List<Trader> traders = session.createQuery("from Trader where remaining > 0 order by remaining desc")
                .getResultList();

        return traders;
    }

    @Override
    public List<Trader> getAllTradersWithoutRemaining() {

        Session session = entityManager.unwrap(Session.class);

        List<Trader> traders = session.createQuery("from Trader where remaining = 0").getResultList();

        return traders;
    }

    @Override
    public Trader getTraderById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Trader theTrader = session.get(Trader.class, id);

        return theTrader;

    }

    @Override
    public List<Incoming> getIncomingsByTraderName(String traderName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Incoming> query = session.createQuery("from Incoming where trader = :traderName order by TIMESTAMP desc");

        query.setParameter("traderName", traderName);

        List<Incoming> traderIncomings = query.getResultList();

        return traderIncomings;
    }

    @Override
    public int getIncomingTotalByTraderName(String traderName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Incoming> query = session.createQuery("from Incoming where trader = :traderName");
        query.setParameter("traderName", traderName);

        List<Incoming> items = query.getResultList();

        int listSize = items.size();

        int incomingTotal = 0;

        for (int i = 0; i < listSize; i++) {

            Incoming item = items.get(i);

            incomingTotal += item.getTotal();
        }

        return incomingTotal;

    }

    @Override
    @Transactional
    public void updateIncomingTraderName(String traderName, String newTraderName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Incoming> query = session
                .createQuery("update Incoming set trader = :newTraderName where trader = :traderName");

        query.setParameter("newTraderName", newTraderName);
        query.setParameter("traderName", traderName);

        query.executeUpdate();
    }

    @Override
    public Trader getTraderByName(String name) {

        Session session = entityManager.unwrap(Session.class);

        Query<Trader> query = session.createQuery("from Trader where name = :name");

        query.setParameter("name", name);

        Trader trader = query.getSingleResult();

        return trader;

    }

    @Override
    public int getSpendingTotalByDate(String date) {

        Session session = entityManager.unwrap(Session.class);

        Query<Spending> query = session.createQuery("from Spending where date = :theDate");

        query.setParameter("theDate", date);

        List<Spending> spendings = query.list();

        int listSize = spendings.size();

        int spendingTotal = 0;

        for (int i = 0; i < listSize; i++) {

            spendingTotal += spendings.get(i).getMoney();
        }

        return spendingTotal;
    }

    @Override
    public List<Result> groupByMonths() {

        Session session = entityManager.unwrap(Session.class);

        Query<Result> queryBill = session
                .createNativeQuery("SELECT sum(gain) as amount,\r\n" + "  extract(month from TIMESTAMP) as month,\r\n"
                        + "  extract(year from TIMESTAMP) as year \r\n" + " FROM Bill \r\n" + "  GROUP BY month, \r\n"
                        + " year ORDER BY year desc, month desc")
                .addScalar("amount", new DoubleType()).addScalar("year", new DoubleType())
                .addScalar("month", new DoubleType());

        List<Result> monthslistForBill = queryBill.setResultTransformer(Transformers.aliasToBean(Result.class)).list();

        Query<Result> querySpending = session
                .createNativeQuery(
                        "SELECT sum(money) as spending,\r\n" + "  extract(month from TIMESTAMP) as month,\r\n"
                                + "  extract(year from TIMESTAMP) as year \r\n" + "  FROM Spending \r\n"
                                + "  GROUP BY month, \r\n" + "  year ORDER BY year desc, month desc")
                .addScalar("spending", new DoubleType()).addScalar("year", new DoubleType())
                .addScalar("month", new DoubleType());

        List<Result> monthslistForSpending = querySpending.setResultTransformer(Transformers.aliasToBean(Result.class))
                .list();

        int spendingMonth = 0;
        int spendingYear = 0;
        int billMonth = 0;
        int billYear = 0;

        if (monthslistForBill.size() > monthslistForSpending.size()) {

            for (Result billResult : monthslistForBill) {

                for (Result spendingResult : monthslistForSpending) {

                    billMonth = billResult.getMonth().intValue();
                    billYear = billResult.getYear().intValue();

                    spendingMonth = spendingResult.getMonth().intValue();
                    spendingYear = spendingResult.getYear().intValue();

                    if (billYear == spendingYear && billMonth == spendingMonth) {

                        billResult.setSpending(spendingResult.getSpending());

                    }

                }
            }

            return monthslistForBill;
        } else {

            for (Result spendingResult : monthslistForSpending) {

                for (Result billResult : monthslistForBill) {

                    billMonth = billResult.getMonth().intValue();
                    billYear = billResult.getYear().intValue();

                    spendingMonth = spendingResult.getMonth().intValue();
                    spendingYear = spendingResult.getYear().intValue();

                    if (billYear == spendingYear && billMonth == spendingMonth) {

                        spendingResult.setAmount(billResult.getAmount());

                    }

                }
            }

            return monthslistForSpending;
        }
    }

    @Override
    public void saveTraderPay(TraderPay traderPay) {
        Session session = entityManager.unwrap(Session.class);
        session.save(traderPay);
    }

    @Override
    @Transactional
    public void deleteTraderPay(int id) {
        Session session = entityManager.unwrap(Session.class);

        TraderPay traderPay = session.get(TraderPay.class, id);

        session.delete(traderPay);
    }

    @Override
    public TraderPay getTraderPayById(int id) {
        Session session = entityManager.unwrap(Session.class);

        TraderPay traderPay = session.get(TraderPay.class, id);

        return traderPay;
    }

    @Override
    @Transactional
    public void deleteAllSystem() {
        Session session = entityManager.unwrap(Session.class);
        Query deleteBillQuery = session.createQuery("DELETE FROM Bill");
        Query deleteIncomingQuery = session.createQuery("DELETE FROM Incoming");
        Query deleteSpendingQuery = session.createQuery("DELETE FROM Spending");

        deleteBillQuery.executeUpdate();
        deleteIncomingQuery.executeUpdate();
        deleteSpendingQuery.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteClient(int id) {

        Session session = entityManager.unwrap(Session.class);

        Client client = session.get(Client.class, id);

        session.delete(client);
    }

    @Override
    @Transactional
    public void deleteTrader(int id) {

        Session session = entityManager.unwrap(Session.class);

        Trader trader = session.get(Trader.class, id);

        session.delete(trader);
    }

    @Override
    @Transactional
    public void deleteSpending(int id) {

        Session session = entityManager.unwrap(Session.class);

        Spending spending = session.get(Spending.class, id);

        session.delete(spending);

        // entityManager.remove(entityManager.contains(spending) ? spending :
        // entityManager.merge(spending));

    }

    @Override
    @Transactional
    public Bill deleteBill(int id) {

        Session session = entityManager.unwrap(Session.class);

        Bill bill = session.get(Bill.class, id);

        session.delete(bill);

        return bill;
    }

    @Override
    @Transactional
    public void deleteIncoming(int id) {

        Session session = entityManager.unwrap(Session.class);

        Incoming incoming = session.get(Incoming.class, id);

        Query<?> query = session.createQuery("update Trader t " + "set t.remaining = t.remaining - :theRemaining,"
                + "t.payed = t.payed - :thePayed where name = :theTraderName");

        query.setParameter("theRemaining", incoming.getTotal() - incoming.getPayed());
        query.setParameter("thePayed", incoming.getPayed());
        query.setParameter("theTraderName", incoming.gettrader());

        query.executeUpdate();

        session.delete(incoming);
    }

    @Override
    public List<Bill> getClientBills(int clientId) {

        Session session = entityManager.unwrap(Session.class);

        Query<Bill> query = session.createQuery("from Bill where client_id = :theClientId");

        query.setParameter("theClientId", clientId);

        return query.getResultList();
    }

    @Override
    public Incoming getIncomingById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Incoming incoming = session.get(Incoming.class, id);

        return incoming;
    }

    @Override
    public List<Client> getClientByName(String clientName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Client> query = session
                .createQuery("from Client where id > 0 and name like CONCAT('%',?1,'%') order by drawee desc");
        query.setParameter(1, clientName);

        List<Client> clients = query.getResultList();

        return clients;

    }

    @Override
    public List<Trader> getTradersByName(String traderName) {

        Session session = entityManager.unwrap(Session.class);

        Query<Trader> query = session
                .createQuery("from Trader where id > -1 and  name like CONCAT('%',?1,'%') order by remaining desc");

        query.setParameter(1, traderName);

        List<Trader> traders = query.getResultList();

        return traders;
    }
}
