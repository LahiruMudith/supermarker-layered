package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.BOFactory;
import lk.ijse.gdse.supermarket.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.supermarket.bo.custom.OrdersBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrdersDAO;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Order;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdersBOimpl implements OrdersBO {
    private OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ORDERS);
    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ORDERDETAIL);
    @Override
    public String getNextOrderId() throws ClassNotFoundException, SQLException {
        return ordersDAO.generateNewId();
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // 1

            boolean isOrderSaved = ordersDAO.save(new Order(
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    orderDTO.getOrderDate()
            ));
            System.out.println("isOrderSaved: " + isOrderSaved);
            // If the order is saved successfully
            for (OrderDetailsDTO orderDetailsDTO : orderDTO.getOrderDetailsDTOS()){
                if (isOrderSaved) {
                    boolean isOrderDetailListSaved = orderDetailsDAO.save(new OrderDetails(
                            orderDetailsDTO.getOrderId(),
                            orderDetailsDTO.getItemId(),
                            orderDetailsDTO.getQuantity(),
                            orderDetailsDTO.getPrice()
                    ));
                    if (isOrderDetailListSaved) {
                        connection.commit(); // 2
                        return true;
                    }
                    System.out.println("isOrderDetailSaved: " + isOrderSaved);
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
