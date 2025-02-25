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

    @Override
    public boolean deleteItem(String id) throws ClassNotFoundException, SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public String getNextItemId() throws ClassNotFoundException, SQLException {
        return itemDAO.generateNewId();
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for(Item item : items){
            ItemDTO itemDTO = new ItemDTO(
                item.getItemId(),
                item.getItemName(),
                item.getQuantity(),
                item.getPrice()
            );

            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemId(), itemDTO.getItemName(), itemDTO.getQuantity(), itemDTO.getPrice()));
    }
}
