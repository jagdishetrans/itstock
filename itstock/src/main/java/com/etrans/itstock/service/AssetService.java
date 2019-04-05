package com.etrans.itstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;

	public List<Asset> getAssets() {
		return assetRepository.findAll();
	}

	public List<Asset> getAssetsByType(String type) {
		return assetRepository.findByType(type);
	}

	public void saveAssets(Asset asset) {
		assetRepository.save(asset);
	}

	public void deleteAsset(String name) {
		assetRepository.deleteById(name);
	}

}
