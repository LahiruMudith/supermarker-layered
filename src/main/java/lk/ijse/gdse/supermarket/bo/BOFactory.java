package lk.ijse.gdse.supermarket.bo;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory(): boFactory;
    }

    public enum BOType{

    }

    public SuperBO getBO(BOType type){
        switch (type){
            default -> {return null;}
        }
    }
}
