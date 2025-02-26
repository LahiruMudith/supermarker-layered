package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOimpl implements ItemDAO {
    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        String sql = "update item set name = ?, quantity= ?, price= ? where item_id = ?";
        return CrudUtil.execute(sql, dto.getItemName(), dto.getQuantity(), dto.getPrice(), dto.getItemId());
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from item";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Item> items = new ArrayList<>();
        while (res.next()){
            Item item = new Item(
                    res.getString("item_id"),
                    res.getString("name"),
                    res.getInt("quantity"),
                    res.getDouble("price")
            );

            items.add(item);
        }
        return items;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select item_id from item order by item_id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if (res.next()) {
            String lastId = res.getString("item_id");
            int i = Integer.parseInt(lastId.replaceAll("\\D", ""));
            int newIdNumber = i + 1;
            String newId = String.format("I%03d", newIdNumber);
            return newId;
        } else {
            return  "I001";
        }
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" delete from item where item_id = ?", id);
    }

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
    public boolean reduceQty(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                entity.getQuantity(),   // Quantity to reduce
                entity.getItemId()      // Item ID
        );
    }

    //------------>

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
