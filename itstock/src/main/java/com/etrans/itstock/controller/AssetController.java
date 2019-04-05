package com.etrans.itstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Asset> getAssets() {
		List<Asset> assetList = assetService.getAssets();
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

	@PostMapping(path = "/assets")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String saveAssets(@RequestBody Asset asset) {
		assetService.saveAssets(asset);
		return "{\"message\":\"Saved successfully\"}";
	}

	@DeleteMapping(path = "/assets/{name}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteAssets(@PathVariable("name") String name) {
		assetService.deleteAsset(name);
	}

//	@RequestMapping("/asset/type/{name}")
//	public ResponseEntity<Asset> getAssetTypeFields(@PathVariable("name") String name) {
//		try {
//			Optional<Asset> assetType = assetTypeService.getAssetType(name);
//			if (assetType.isPresent()) {
//				return ResponseEntity.ok().body(assetType.get());
//			}
//			return ResponseEntity.notFound().build();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//
//	@RequestMapping(path = "/asset/type", headers = "Accept=application/json", produces = "text/plain", method = RequestMethod.POST)
//	public ResponseEntity<String> saveAssetType(@RequestBody Asset assetType) {
//		try {
//			if (null == assetType) {
//				return ResponseEntity.badRequest().body("{\"message\":\"Data missing\"}");
//			}
//			assetTypeService.setAssetType(assetType);
//			return ResponseEntity.ok().body("{\"message\":\"Saved successfully\"}");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//
//	@RequestMapping(path="/asset/type", method=RequestMethod.GET)
//	public ResponseEntity<List<Asset>> getAssetType(){
//		return ResponseEntity.ok().body(assetTypeService.getAssetTypes());
//	}

}
