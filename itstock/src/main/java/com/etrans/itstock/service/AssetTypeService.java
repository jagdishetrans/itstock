package com.etrans.itstock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.AssetType;
import com.etrans.itstock.repository.AssetTypeRepository;

@Service
public class AssetTypeService {

	@Autowired
	AssetTypeRepository assetTypeRepository;

	public void saveData(AssetType assetType) {
		assetTypeRepository.save(assetType);
	}

	public Optional<AssetType> getAssetTypeByType(String type) {
		return assetTypeRepository.findById(type);
	}

}
