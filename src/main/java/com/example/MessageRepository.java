package com.example;//Created by KevinBozic on 3/8/16.

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
