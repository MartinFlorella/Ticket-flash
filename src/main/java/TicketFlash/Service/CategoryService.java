package TicketFlash.Service;

import TicketFlash.Model.Category;
import TicketFlash.Repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    ICategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        if(categoryRepository.findAll().isEmpty())
            throw new RuntimeException("No se encontraron categorias");
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        if(categoryRepository.findById(id).isEmpty())
            throw new RuntimeException("No se encontro la categoria que desea borrar");

        categoryRepository.deleteById(id);
    }

    @Override
    public Category saveCategory(Category category) {

        if(category.getName() == null || category.getName().trim().isEmpty()){
            throw new RuntimeException("El nombre de la categoria no puede estar vacio");
        }
        Optional<Category> existCategory = categoryRepository.findByName(category.getName());

        if(existCategory.isPresent()){
            throw new RuntimeException("Ya existe una categoria con el nombre " + category.getName());
        }

        return categoryRepository.save(category);
    }
}
