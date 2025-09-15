package TicketFlash.Service;

import TicketFlash.Model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
     Category saveCategory(Category category);
}
