package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.BOFactory;
import lk.ijse.gdse.supermarket.bo.custom.ItemBO;
import lk.ijse.gdse.supermarket.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrdersDAO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOimpl implements OrderDetailsBO {
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ITEM);
    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ORDERDETAIL);
    @Override
    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO dto : orderDetailsDTOS) {
            // @isOrderDetailsSaved: Saves the individual order detail
            boolean isOrderDetailsSaved = orderDetailsDAO.save(new OrderDetails(dto.getOrderId(), dto.getItemId(),dto.getQuantity(), dto.getPrice()));
            if (!isOrderDetailsSaved) {
                // Return false if saving any order detail fails
                return false;
            }

            // @isItemUpdated: Updates the item quantity in the stock for the corresponding order detail
            boolean isItemUpdated = itemDAO.reduceQty(new OrderDetails(
                    dto.getOrderId(),
                    dto.getItemId(),
                    dto.getQuantity(),
                    dto.getPrice()
            ));
            if (!isItemUpdated) {
                // Return false if updating the item quantity fails
                return false;
            }
        }
        // Return true if all order details are saved and item quantities updated successfully
        return true;
    }
}
