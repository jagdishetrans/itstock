package com.etrans.itstock.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;
	
	public List<Asset> getAssets(){
		Iterable<Asset> assets = assetRepository.findAll();
		List<Asset> assetList = new LinkedList<Asset>();
		for(Asset asset : assets) {
			assetList.add(asset);
		}
		return assetList;
	}
	
	public void saveAssets(Asset asset) {
		assetRepository.save(asset);
	}
	
	public void deleteAsset(String name) {
		//assetRepository.deleteAsset(name);
		assetRepository.deleteById(name);
	}
	
}
