package TicketFlash.Service;

import TicketFlash.Model.CategoryTicket;

import java.util.List;

public interface ICategoryTicketService {

    List<CategoryTicket> findAll();
    CategoryTicket findById(Long id);
    void deleteById(Long id);
    CategoryTicket saveCategory(CategoryTicket category);
    public void reducirStock(Long categoryId,int stock);
}
