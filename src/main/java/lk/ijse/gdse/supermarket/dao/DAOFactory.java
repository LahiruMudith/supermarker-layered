package lk.ijse.gdse.supermarket.dao;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum getDAOType{

    }

    public SuperDAO getDAO(getDAOType type){
        switch (type){
            default -> {return null;}
        }
    }
}
