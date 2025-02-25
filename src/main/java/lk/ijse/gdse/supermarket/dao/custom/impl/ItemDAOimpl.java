package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOimpl implements ItemDAO {
    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into item values(?,?,?,?)", dto.getItemId(), dto.getItemName(), dto.getQuantity(), dto.getPrice());
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from item where item_id=?", id);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new Item(
                    rst.getString(1),  // Item ID
                    rst.getString(2),  // Item Name
                    rst.getInt(3),     // Item Quantity
                    rst.getDouble(4)   // Item Price
            );
        }

        // Return null if the item is not found
        return null;
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        // Execute SQL query to get all item IDs
        ResultSet rst = CrudUtil.execute("select item_id from item");

        // Create an ArrayList to store the item IDs
        ArrayList<String> itemIds = new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return itemIds;
    }
    @Override
    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                orderDetailsDTO.getQuantity(),   // Quantity to reduce
                orderDetailsDTO.getItemId()      // Item ID
        );
    }

    //------------>

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
