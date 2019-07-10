package com.etrans.itstock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.Field;
import com.etrans.itstock.model.FieldType;
import com.etrans.itstock.repository.FieldRepository;

@Service
public class FieldService {

	@Autowired
	FieldRepository fieldRepository;

	public List<Field> getAllFields() {
		return fieldRepository.findAll();
	}

	public List<Field> getFieldsByType(FieldType fieldType) {
		return fieldRepository.findAll();
	}

	public Optional<Field> getFieldById(String key) {
		return fieldRepository.findById(key);
	}

	public List<Field> getFieldsById(List<String> keys) {
		List<Field> fieldList = new ArrayList<>();
		fieldRepository.findAllById(keys).forEach(fieldList::add);
		return fieldList;
	}

	public void saveField(Field field) {
		field.setKey(UUID.randomUUID().toString());
		fieldRepository.save(field);
	}

}
