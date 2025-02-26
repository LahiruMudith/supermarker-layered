package lk.ijse.gdse.supermarket.dao.custom;

import lk.ijse.gdse.supermarket.bo.custom.CustomerBO;
import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.dao.SuperDAO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
