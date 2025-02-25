package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.dao.custom.impl.CustomerDAOimpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.ItemDAOimpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrderDetailsDAOimpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrdersDAOimpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum getDAOType{
        CUSTOMER, ITEM, ORDERDETAIL, ORDERS
    }

    public SuperDAO getDAO(getDAOType type){
        switch (type){
            case CUSTOMER -> {return new CustomerDAOimpl();}
            case ITEM -> {return new ItemDAOimpl();}
            case ORDERDETAIL -> {return new OrderDetailsDAOimpl();}
            case ORDERS -> {return new OrdersDAOimpl();}
            default -> {return null;}
        }
    }
}
