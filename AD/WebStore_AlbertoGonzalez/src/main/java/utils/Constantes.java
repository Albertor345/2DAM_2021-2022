package utils;

public class Constantes {
    public static final String INSERT_ITEM_QUERY = "insert into items (name, price, company) values (?,?,?)";
    public static final String DELETE_ITEM_QUERY = "delete from items where id_item = ?";
    public static final String UPDATE_ITEM_QUERY = "update items set name = ?, company = ?, price = ? where id_item = ?";
    public static final String SELECT_ALL_ITEMS_QUERY = "select * from items";
    public static final String SELECT_ITEM_QUERY = "select * from items where id_item = ?";

    public static final String INSERT_PURCHASE_QUERY = "insert into sales (id_item, id_customer, date) values (?,?,?)";
    public static final String DELETE_PURCHASE_QUERY = "delete from sales where id_sale = ?";
    public static final String UPDATE_PURCHASE_QUERY = "update sales set date = ? where id_sale = ?";
    public static final String SELECT_ALL_PURCHASES_QUERY = "select * from sales" +
            " inner join customers c on sales.id_customer = c.id_customer" +
            " inner join items i on sales.id_item = i.id_item";
    public static final String SELECT_PURCHASE_QUERY = "select * from sales" +
            " inner join items i on sales.id_item = i.id_item" +
            " inner join customers c on sales.id_customer = c.id_customer where id_sale = ?";

    public static final String INSERT_CUSTOMER_QUERY = "insert into customers (name, phone, address) values (?,?,?)";
    public static final String DELETE_CUSTOMER_QUERY = "delete from customers where id_customer = ?";
    public static final String UPDATE_CUSTOMER_QUERY = "update customers set name = ?, phone = ?, address = ? where id_customer = ?";
    public static final String SELECT_ALL_CUSTOMERS_QUERY = "select * from customers";
    public static final String SELECT_CUSTOMER_QUERY = "select * from customers where id_customer = ?";

    public static final String INSERT_REVIEW_QUERY = "insert into reviews (rating, title, review, date) values (?,?,?,?)";
    public static final String DELETE_REVIEW_QUERY = "delete from reviews where id_review = ?";
    public static final String UPDATE_REVIEW_QUERY = "update reviews set rating = ?, title = ?, review = ?, date = ? where id_review = ?";
    public static final String SELECT_ALL_REVIEWS_QUERY = "select * from reviews" +
            " inner join sales s on reviews.id_sales = s.id_sale" +
            " inner join customers c on s.id_customer = c.id_customer" +
            " inner join items i on s.id_item = i.id_item";
    public static final String SELECT_REVIEW_QUERY = "select * from reviews where id_customer = ?";

    /*-----------------------------------------------------------------------------------------------------------------------------------------*/

    public static final String DELETE_SALES_FROM_ITEM = "delete from sales where id_item = ?";

    public static final String QUERY_LOGIN = "select * from users " +
            "left join customers on id = id_customer " +
            "where users.name = :name and password = :password ";
}
