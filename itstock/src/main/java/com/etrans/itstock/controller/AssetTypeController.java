package com.etrans.itstock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etrans.itstock.model.AssetType;
import com.etrans.itstock.service.AssetTypeService;

@RestController
@RequestMapping("/assettype")
public class AssetTypeController {

	@Autowired
	AssetTypeService assetTypeService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void postAssetTypeData(@RequestBody AssetType assetType) {
		assetTypeService.saveAssetType(assetType);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void putAssetTypeData(@RequestBody AssetType assetType) {

	}

	@GetMapping(path = "/{type}")
	public AssetType getAssetTypeData(@PathVariable String type,
			@RequestParam(required = false, defaultValue = "key") String paramType,
			@RequestParam(required = false, defaultValue = "false") boolean raw) {
		Optional<AssetType> assetType;
		if (paramType.equals("name")) {
			assetType = assetTypeService.getAssetTypeByName(type, raw);
		} else {
			assetType = assetTypeService.getAssetType(type, raw);
		}
		if (assetType.isPresent())
			return assetType.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, type + " not found");
	}

	@GetMapping
	public List<AssetType> getAssetType(@RequestParam(required = false, defaultValue = "false") boolean onlylabel) {
		if (onlylabel) {
			return assetTypeService.getAllAssetLabel();
		} else {
			return assetTypeService.getAllAssetType();
		}
	}

}
