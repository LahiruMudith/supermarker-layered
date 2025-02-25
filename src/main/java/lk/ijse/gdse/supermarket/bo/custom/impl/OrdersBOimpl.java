package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.BOFactory;
import lk.ijse.gdse.supermarket.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.supermarket.bo.custom.OrdersBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.OrdersDAO;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdersBOimpl implements OrdersBO {
    private OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERDETAIL);
    private OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ORDERS);
    @Override
    public String getNextOrderId() throws ClassNotFoundException, SQLException {
        return ordersDAO.generateNewId();
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // 1

            boolean isOrderSaved = CrudUtil.execute(
                    "insert into orders values (?,?,?)",
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    orderDTO.getOrderDate()
            );
            // If the order is saved successfully
            if (isOrderSaved) {
                boolean isOrderDetailListSaved = orderDetailsBO.saveOrderDetailsList(orderDTO.getOrderDetailsDTOS());
                if (isOrderDetailListSaved) {
                    connection.commit(); // 2
                    return true;
                }
            }
            connection.rollback(); // 3
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true); // 4
        }
    }
}
