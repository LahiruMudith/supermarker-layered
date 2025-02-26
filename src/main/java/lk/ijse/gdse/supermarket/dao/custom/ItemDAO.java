package lk.ijse.gdse.supermarket.dao.custom;

import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    boolean reduceQty(OrderDetails entity) throws SQLException, ClassNotFoundException;
}
