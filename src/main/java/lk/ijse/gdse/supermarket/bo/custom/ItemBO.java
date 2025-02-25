package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    ItemDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException;
    boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDTO dto) throws ClassNotFoundException, SQLException;
    boolean deleteItem(String id) throws ClassNotFoundException, SQLException;
    String getNextItemId() throws ClassNotFoundException,SQLException;
}
