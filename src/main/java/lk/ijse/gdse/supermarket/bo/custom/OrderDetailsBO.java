package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dao.SuperDAO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException, ClassNotFoundException;
}
