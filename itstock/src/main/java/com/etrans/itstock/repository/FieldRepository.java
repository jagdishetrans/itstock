package com.etrans.itstock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.Field;

@Repository
public interface FieldRepository extends MongoRepository<Field, String> {

	// List<Field> findByFieldType(FieldType fieldType);

}
