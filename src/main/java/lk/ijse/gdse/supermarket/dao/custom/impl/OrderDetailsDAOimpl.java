package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOimpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "insert into orderdetails values (?,?,?,?)",
                dto.getOrderId(),
                dto.getItemId(),
                dto.getQuantity(),
                dto.getPrice()
        );
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public OrderDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
