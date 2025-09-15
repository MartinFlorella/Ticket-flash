package TicketFlash.Service;

import TicketFlash.Model.CategoryTicket;
import TicketFlash.Repository.ICategoryTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTicketService implements ICategoryTicketService {

    @Autowired
    ICategoryTicketRepository CticketRepository;

    @Override
    public List<CategoryTicket> findAll() {
        return CticketRepository.findAll();
    }

    @Override
    public CategoryTicket findById(Long id) {
        return CticketRepository.findById(id).orElseThrow(() -> new RuntimeException("La categoria de ticket con ID " + id + ", No existe"));
    }

    @Override
    public void deleteById(Long id) {
        if (CticketRepository.findById(id).isEmpty())
            throw new RuntimeException("No se encontro La categoria de ticket que desea eliminar");
        CticketRepository.deleteById(id);

    }

    @Override
    public CategoryTicket saveCategory(CategoryTicket category) {

        CticketRepository.save(category);
        return category;

    }

    @Override
    public void reducirStock(Long categoryId, int cantidad) {
        CategoryTicket category = CticketRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        if (category.getStock() < cantidad) {
            throw new RuntimeException("No hay stock suficiente.");
        }
        category.setStock(category.getStock() - cantidad);

    }
}