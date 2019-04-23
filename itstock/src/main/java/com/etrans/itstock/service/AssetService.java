package com.etrans.itstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public List<Asset> getAssetsWithoutParent() {
		return assetRepository.findByParentIsNull();
	}

	public List<Asset> getAssetsByType(String type) {
		return assetRepository.findByType(type);
	}

	public Long getCountByType(String type) {
		return assetRepository.countByType(type);
	}

	public void saveAssets(Asset asset) {
		PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.DESC, "_id");
		List<Asset> assets = assetRepository.findAll(pageRequest).getContent();
		int maxId = 1;
		if (!assets.isEmpty()) {
			Asset lastMaxAsset = assets.get(0);
			maxId = lastMaxAsset.getId() + 1;
		}
		asset.setId(maxId);
		assetRepository.save(asset);
		if (!asset.getChild().isEmpty()) {
			Iterable<Asset> childAssetList = assetRepository.findAllById(asset.getChild());
			childAssetList.forEach(cAsset -> cAsset.setParent(asset.getId()));
			assetRepository.saveAll(childAssetList);
		}
		if (null != asset.getParent()) {
			Optional<Asset> optinalParentAsset = assetRepository.findById(asset.getParent());
			if (optinalParentAsset.isPresent()) {
				Asset parentAsset = optinalParentAsset.get();
				List<Integer> childList = parentAsset.getChild();
				childList.add(asset.getId());
				parentAsset.setChild(childList);
				assetRepository.save(parentAsset);
			}
		}
	}

}
