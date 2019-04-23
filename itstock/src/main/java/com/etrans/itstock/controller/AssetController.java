package com.etrans.itstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.service.AssetService;

@RestController
public class AssetController {

	@Autowired
	AssetService assetService;

	@GetMapping(path = "/assets")
	public List<Asset> getAssets(@RequestParam(required = false, defaultValue = "true") boolean parent) {
		List<Asset> assetList;
		if (parent == true) {
			assetList = assetService.getAssets();
		} else {
			assetList = assetService.getAssetsWithoutParent();
		}
		if (assetList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets not found");
		}
		return assetList;
	}

	@GetMapping(path = "/assets/{type}")
	public List<Asset> getAssetsByType(@PathVariable String type) {
		List<Asset> assetList = assetService.getAssetsByType(type);
		if (assetList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Assets not found of %s type", type));
		}
		return assetList;
	}

	@GetMapping(path = "/assets/{type}/count")
	public Long getAssetTypeCount(@PathVariable String type) {
		return assetService.getCountByType(type);
	}

	@PostMapping(path = "/assets")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String saveAssets(@RequestBody Asset asset) {
		assetService.saveAssets(asset);
		return "{\"message\":\"Saved successfully\"}";
	}

}
