package com.etrans.itstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.etrans.itstock.model.Field;
import com.etrans.itstock.service.FieldService;

@RestController
@RequestMapping("/fields")
public class FieldController {

	@Autowired
	FieldService fieldService;

	@GetMapping
	public List<Field> getAllFields() {
		return fieldService.getAllFields();
	}

	@GetMapping(path = "/{type}")
	public List<Field> getFieldByType(@PathVariable String type) {
		return fieldService.getAllFields();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveField(@RequestBody Field field) {
		fieldService.saveField(field);
	}

}
