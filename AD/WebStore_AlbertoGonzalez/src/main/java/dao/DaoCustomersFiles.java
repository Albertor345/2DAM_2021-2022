package dao;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DaoCustomersFiles implements DAOCustomers {
    private List<Customer> customerList = new ArrayList<>();

    public DaoCustomersFiles() {
        customerList.add(Customer.builder().idCustomer(0).address("San Cipriano 20, Madrid, Spain").name("AlbertoGonzalez").phone("635318374").reviews(new ArrayList<>()).build());
        customerList.add(Customer.builder().idCustomer(1).address("San Roman del Valle, 32, Madrid, Spain").name("FranciscoLeopoldo").phone("647837261").reviews(new ArrayList<>()).build());
        customerList.add(Customer.builder().idCustomer(2).address("Calle Embajadores 12, Madrid, Spain").name("EsmeraldaCaño").phone("638273849").reviews(new ArrayList<>()).build());
        customerList.add(Customer.builder().idCustomer(3).address("Calle Cabo de Tarifa 72").name("CristianGrande").phone("684930298").reviews(new ArrayList<>()).build());
    }

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customerList);
    }

    @Override
    public void save(Customer t) {

    }

    @Override
    public void update(Customer t) {

    }

    @Override
    public void delete(Customer t) {

    }
}
