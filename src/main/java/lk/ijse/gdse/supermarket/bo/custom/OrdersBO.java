package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.OrderDTO;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO {
    String getNextOrderId() throws ClassNotFoundException, SQLException;
    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
