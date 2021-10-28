package dao.impl.files;

import configuration.ConfigProperties;
import dao.DAOCustomers;
import dao.DAOItems;
import model.Customer;
import model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCustomersFilesImpl implements DAOCustomers {


    public DaoCustomersFilesImpl() {
    }

    @Override
    public boolean add(Customer customer) {
        try {
            Path file = Paths.get(ConfigProperties.getInstance().getProperty("customers"));
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(Customers.builder().customers(new ArrayList<>() {{add(customer);}}).build(), Files.newOutputStream(file));
            return true;
        } catch (JAXBException e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public Customer get(Customer customer) {
        try {
            Path file = Paths.get(ConfigProperties.getInstance().getProperty("customers"));
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            customer = (Customer) unmarshaller.unmarshal(Files.newInputStream(file));
        } catch (JAXBException e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            Path file = Paths.get(ConfigProperties.getInstance().getProperty("customers"));
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            customers = (List<Customer>) unmarshaller.unmarshal(Files.newInputStream(file));
        } catch (JAXBException e) {
            customers.addAll(Collections.emptyList());
        } catch (IOException e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, e);
        }
        return customers;
    }

    @Override
    public boolean update(Customer t) {
        return false;
    }

    @Override
    public boolean delete(Customer t) {
        return false;
    }
}
