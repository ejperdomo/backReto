package com.usa.ciclo3.retociclo3.crudrepository;

import com.usa.ciclo3.retociclo3.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
