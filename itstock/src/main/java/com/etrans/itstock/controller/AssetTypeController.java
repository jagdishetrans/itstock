package com.etrans.itstock.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etrans.itstock.model.AssetType;
import com.etrans.itstock.service.AssetTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AssetTypeController {

	@Autowired
	AssetTypeService assetTypeService;

	@PostMapping(path = "/assettype")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void postAssetTypeData(@RequestBody AssetType assetType) throws JsonProcessingException {
		// ObjectMapper mapper = new ObjectMapper();
		// System.out.println(mapper.writeValueAsString(assetType));
		assetTypeService.saveData(assetType);
	}

	@GetMapping(path = "/assettype/{type}")
	public AssetType getAssetTypeData(@PathVariable String type) {
		Optional<AssetType> assetType = assetTypeService.getAssetTypeByType(type);
		if (assetType.isPresent())
			return assetType.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, type + " not found");
	}

}
