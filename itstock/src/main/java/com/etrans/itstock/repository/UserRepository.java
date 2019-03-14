package com.etrans.itstock.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> getUserByUserNameAndPassword(String userName, String password);

}
