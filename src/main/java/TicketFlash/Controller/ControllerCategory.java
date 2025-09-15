package TicketFlash.Controller;

import TicketFlash.Model.Category;
import TicketFlash.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerCategory {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/create")
        public String registerCategory(@RequestBody Category category){

        categoryService.saveCategory(category);
        return "The category was created";
    }
}
