package lk.ijse.gdse.supermarket.bo;

import lk.ijse.gdse.supermarket.bo.custom.impl.CustomerBOimpl;
import lk.ijse.gdse.supermarket.bo.custom.impl.ItemBOimpl;
import lk.ijse.gdse.supermarket.bo.custom.impl.OrderDetailsBOimpl;
import lk.ijse.gdse.supermarket.bo.custom.impl.OrdersBOimpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory(): boFactory;
    }

    public enum BOType{
        CUSTOMER, ITEM, ORDERDETAIL, ORDERS
    }

    public SuperBO getBO(BOType type){
        switch (type){
            case CUSTOMER -> {return new CustomerBOimpl();}
            case ITEM -> {return new ItemBOimpl();}
            case ORDERDETAIL -> {return new OrderDetailsBOimpl();}
            case ORDERS -> {return new OrdersBOimpl();}
            default -> {return null;}
        }
    }
}
