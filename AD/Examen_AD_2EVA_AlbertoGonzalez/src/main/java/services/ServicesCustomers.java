package services;

import java.util.List;

public interface ServicesCustomers {
    boolean add();

    boolean delete();

    boolean update();

    List<String> getAll();

    void get();
}
