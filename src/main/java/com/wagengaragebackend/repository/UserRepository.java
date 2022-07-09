package com.wagengaragebackend.repository;

import org.springframework.data.repository.CrudRepository;
import com.wagengaragebackend.data.User;

public interface UserRepository extends CrudRepository<User, String>

{
}

