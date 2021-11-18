package dao.impl.spring;

import dao.DAOPurchases;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Purchase;
import producers.annotations.JDBC;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@JDBC
public class DAOPurchasesSpringImpl implements DAOPurchases {
    private final DBConnection dbConnection;

    @Inject
    public DAOPurchasesSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Purchase get(Purchase purchase) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_PURCHASE_QUERY)) {
            preparedStatement.setInt(1, purchase.getIdPurchase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Purchase.builder()
                        .idPurchase(resultSet.getInt("id_sale"))
                        .customer(Customer.builder()
                                .idCustomer(resultSet.getInt("sales.id_customer"))
                                .name(resultSet.getString("c.name"))
                                .phone(resultSet.getString("phone"))
                                .address(resultSet.getString("address"))
                                .build())
                        .item(Item.builder()
                                .id(resultSet.getInt("sales.id_item"))
                                .name(resultSet.getString("i.name"))
                                .company(resultSet.getString("company"))
                                .price(resultSet.getDouble("price"))
                                .build())
                        .date(resultSet.getDate("date").toLocalDate())
                        .build();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchase;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> purchaseList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_PURCHASES_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                purchaseList.add(Purchase.builder()
                        .idPurchase(resultSet.getInt("id_sale"))
                        .customer(Customer.builder()
                                .idCustomer(resultSet.getInt("sales.id_customer"))
                                .name(resultSet.getString("c.name"))
                                .phone(resultSet.getString("phone"))
                                .address(resultSet.getString("address"))
                                .build())
                        .item(Item.builder()
                                .id(resultSet.getInt("sales.id_item"))
                                .name(resultSet.getString("i.name"))
                                .company(resultSet.getString("company"))
                                .price(resultSet.getDouble("price"))
                                .build())
                        .date(resultSet.getDate("date").toLocalDate())
                        .build());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchaseList;
    }

    @Override
    public boolean add(Purchase purchase) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.INSERT_PURCHASE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, purchase.getItem().getId());
            preparedStatement.setInt(2, purchase.getCustomer().getIdCustomer());
            preparedStatement.setDate(3, Date.valueOf(purchase.getDate()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                purchase.setIdPurchase(resultSet.getInt(1));
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean update(Purchase purchase) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.UPDATE_PURCHASE_QUERY)) {
            preparedStatement.setDate(1, Date.valueOf(purchase.getDate()));
            preparedStatement.setInt(2, purchase.getIdPurchase());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(Purchase purchase) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.DELETE_PURCHASE_QUERY)) {
            preparedStatement.setInt(1, purchase.getIdPurchase());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }
}
