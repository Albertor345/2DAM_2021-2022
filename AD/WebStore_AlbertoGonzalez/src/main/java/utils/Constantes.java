package utils;

public class Constantes {
    public static final String INSERT_ITEM_QUERY = "insert into items (name, price, company) values (?,?,?)";
    public static final String DELETE_ITEM_QUERY = "delete from items where id = ?";
    public static final String UPDATE_ITEM_QUERY = "update items set name = ?, company = ?, price = ? where id_item = ?";
    public static final String SELECT_ALL_ITEMS_QUERY = "select * from items";
    public static final String SELECT_ITEM_QUERY = "select * from items where id = ?";

}
