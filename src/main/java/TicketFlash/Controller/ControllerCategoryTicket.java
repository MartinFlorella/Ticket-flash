package TicketFlash.Controller;


import TicketFlash.Model.CategoryTicket;
import TicketFlash.Service.CategoryTicketService;
import TicketFlash.Service.ICategoryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-ticket")
public class ControllerCategoryTicket {

    @Autowired
    private ICategoryTicketService categoryTicketService;

    // 1. Obtener todas las categorías de tickets
    @GetMapping("/all")
    public List<CategoryTicket> getAllCategories() {
        return categoryTicketService.findAll();
    }

    // 2. Obtener una categoría por ID
    @GetMapping("/find/{id}")
    public CategoryTicket getCategoryById(@PathVariable Long id) {
        return categoryTicketService.findById(id);
    }

    // 3. Crear una nueva categoría de ticket
    @PostMapping("/create")
    public CategoryTicket createCategory(@RequestBody CategoryTicket categoryTicket) {
        return categoryTicketService.saveCategory(categoryTicket);
    }

    // 4. Eliminar una categoría por ID
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryTicketService.deleteById(id);
    }

    // 5. Reducir stock de una categoría
    @PutMapping("/reduce-stock/{id}")
    public void reduceCategoryStock(@PathVariable Long id, @RequestParam int cantidad) {
        categoryTicketService.reducirStock(id, cantidad);
    }
}
