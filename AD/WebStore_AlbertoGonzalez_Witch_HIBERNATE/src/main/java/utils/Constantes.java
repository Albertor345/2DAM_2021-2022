package utils;

public class Constantes {
    public static final String INSERT_ITEM_QUERY = "insert into items (name, price, company) values (:name,:price,:company)";
    public static final String DELETE_ITEM_QUERY = "delete from items where id = :id";
    public static final String UPDATE_ITEM_QUERY = "update items set name = :name, company = :company, price = :price where id = :id";
    public static final String SELECT_ALL_ITEMS_QUERY = "select * from items";
    public static final String SELECT_ITEM_QUERY = "select * from items where id = :id";

    public static final String INSERT_PURCHASE_QUERY = "insert into sales (id_item, id_customer, date) values (:item.id, :customer.id, :date)";
    public static final String DELETE_PURCHASE_QUERY = "delete from sales where id = :id";
    public static final String UPDATE_PURCHASE_QUERY = "update sales set date = :date where id = :id";
    public static final String SELECT_ALL_PURCHASES_QUERY = "select * from sales" +
            " inner join customers c on sales.id_customer = c.id" +
            " inner join items i on sales.id_item = i.id";
    public static final String SELECT_PURCHASE_QUERY = "select * from sales " +
            " inner join items i on sales.id_item = i.id" +
            " inner join customers c on sales.id_customer = c.id where id = :id";

    public static final String INSERT_CUSTOMER_QUERY = "insert into customers (id, name, phone, address) values (:idCustomer, :name,:phone,:address)";
    public static final String INSERT_USER_QUERY = "insert into users (name, password) values (:name,:password)";

    public static final String DELETE_CUSTOMER_QUERY = "delete from customers where id = :id";
    public static final String DELETE_USER_QUERY = "delete from users where id = :id";
    public static final String UPDATE_CUSTOMER_QUERY = "update customers set name = :name, phone = :phone, address = :address where id = :id";
    public static final String SELECT_ALL_CUSTOMERS_QUERY = "select * from customers";
    public static final String SELECT_CUSTOMER_QUERY = "select * from customers where id = :id";

    public static final String INSERT_REVIEW_QUERY = "insert into reviews (id_sales, rating, title, review, date) values (:purchase.id, :rating,:title,:review,:date)";
    public static final String DELETE_REVIEW_QUERY = "delete from reviews where id = :id";
    public static final String UPDATE_REVIEW_QUERY = "update reviews set rating = :rating, title = :title, review = :review, date = :date where id = :id";

    public static final String SELECT_ALL_REVIEWS_QUERY = "select * from reviews " +
            " inner join sales s on reviews.id_sales = s.id" +
            " inner join customers c on s.id_customer = c.id" +
            " inner join items i on s.id_item = i.id";
    public static final String SELECT_REVIEWS_FROM_CUSTOMER = "select * from reviews " +
            "inner join sales s on reviews.id_sales = s.id " +
            "inner join customers c on s.id_customer = c.id " +
            "inner join items i on s.id_item = i.id where id = :id";

    public static final String SELECT_REVIEWS_FROM_ITEM = "select * " +
            "from reviews inner join sales s on reviews.id_sales = s.id " +
            "inner join items i on s.id_item = i.id " +
            "inner join customers c on s.id_customer = c.id " +
            "where i.id = :id";

    /*-----------------------------------------------------------------------------------------------------------------------------------------*/

    public static final String DELETE_SALES_FROM_ITEM = "delete from sales where id_item = :id";

    public static final String QUERY_CHECK_USER_IS_CUSTOMER = "select count(id) as 'hasUser' from customers " +
            "where id in (select id from users where name = :name and password = :password) " +
            "group by id";

    public static final String QUERY_LOGIN = "select * from users where name = :name and password = :password";

}
