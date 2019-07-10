package com.etrans.itstock.controller;

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

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.service.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

	@Autowired
	AssetService assetService;

	@GetMapping(path = "/{type}")
	public Asset getAsset(@PathVariable String type,
			@RequestParam(required = false, defaultValue = "key") String paramType) {
		Optional<Asset> asset;
		if (paramType.equals("tag")) {
			asset = assetService.getAssetByTag(type);
		} else {
			asset = assetService.getAssetByKey(type);
		}
		if (asset.isPresent())
			return asset.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, type + " not found");
	}

//	@GetMapping
//	public List<Asset> getAssets(@RequestParam(required = false, defaultValue = "true") boolean parent) {
//		List<Asset> assetList;
//		if (parent == true) {
//			assetList = assetService.getAssets();
//		} else {
//			assetList = assetService.getAssetsWithoutParent();
//		}
//		if (assetList.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets not found");
//		}
//		return assetList;
//	}
//
//	@GetMapping(path = "/{type}")
//	public List<Asset> getAssetsByType(@PathVariable String type) {
//		List<Asset> assetList = assetService.getAssetsByType(type);
//		if (assetList.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Assets not found of %s type", type));
//		}
//		return assetList;
//	}
//
	@GetMapping(path = "/{type}/count")
	public Long getAssetTypeCount(@PathVariable String type) {
		return assetService.getCountByTypeKey(type);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void postAssetData(@RequestBody Asset asset) {
		assetService.saveAsset(asset);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void putAssetData(@RequestBody Asset asset) {
		assetService.updateAsset(asset);
	}

}
