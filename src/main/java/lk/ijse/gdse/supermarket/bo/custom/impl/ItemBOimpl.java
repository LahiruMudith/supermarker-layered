package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.custom.ItemBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOimpl implements ItemBO {
    //=========
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.ITEM);
    //=========
    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemIds();
    }

    @Override
    public ItemDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        Item item =  itemDAO.search(selectedItemId);
        return (item!= null)? new ItemDTO(item.getItemId(), item.getItemName(), item.getQuantity(), item.getPrice()): null;
    }

    @Override
    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.reduceQty(orderDetailsDTO);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws ClassNotFoundException, SQLException {
        return itemDAO.save(new Item(dto.getItemId(), dto.getItemName(), dto.getQuantity(), dto.getPrice()));
    }
}
