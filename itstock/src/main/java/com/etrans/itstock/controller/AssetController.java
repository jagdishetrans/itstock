package com.etrans.itstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.service.AssetService;

@RestController
public class AssetController {

	@Autowired
	AssetService assetService;

	@RequestMapping(path = "/assets", method = RequestMethod.GET)
	public List<Asset> getAssets() {
		List<Asset> assetList = assetService.getAssets();
		if (assetList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets not found");
		}
		return assetList;
	}

	@RequestMapping(path = "/assets", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public String saveAssets(@RequestBody Asset asset) {
		assetService.saveAssets(asset);
		return "{\"message\":\"Saved successfully\"}";
	}

	@RequestMapping(path = "/assets/{name}", method = RequestMethod.DELETE)
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
