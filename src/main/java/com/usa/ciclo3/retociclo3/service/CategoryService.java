package com.usa.ciclo3.retociclo3.service;

import com.usa.ciclo3.retociclo3.model.Category;
import com.usa.ciclo3.retociclo3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category){
        if(category.getId()==null){
            return categoryRepository.save(category);
        } else {
            Optional<Category> ct = categoryRepository.getCategory(category.getId());
            if(ct.isEmpty()){
                return  categoryRepository.save(category);
            } else {
                return  category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId() !=null){
            Optional<Category> g= categoryRepository.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription() !=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName() != null){
                    g.get().setName(category.getName());
                }
                categoryRepository.save(g.get());
                return g.get();
            } else{
                return category;
            }
        }else{
            return category;
        }
    }

    public boolean deleteCategory(int id){
        Boolean aBoolean=getCategory(id).map(category -> {
            categoryRepository.delete((category));
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
