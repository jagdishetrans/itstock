package com.etrans.itstock.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.Asset;
import com.etrans.itstock.model.AssetType;
import com.etrans.itstock.model.Field;
import com.etrans.itstock.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;

//	@Autowired
//	AssetTypeRepository assetTypeRepository;

	@Autowired
	AssetTypeService assetTypeService;

	public List<Asset> getAssets() {
		return assetRepository.findAll();
	}

	public Optional<Asset> getAssetByKey(String key) {
		Optional<Asset> asset = assetRepository.findById(key);
		if (asset.isPresent()) {
			asset = Optional.of(attachBaseFieldsToAsset(asset.get()));
		}
		return asset;
	}

	public Optional<Asset> getAssetByTag(String tag) {
		Optional<Asset> asset = assetRepository.findByTag(tag);
		if (asset.isPresent()) {
			asset = Optional.of(attachBaseFieldsToAsset(asset.get()));
		}
		return asset;
	}

	private Asset attachBaseFieldsToAsset(Asset asset) {
		List<Field> fields = asset.getFields();
		AssetType assetType = assetTypeService.getAssetType(asset.getTypeKey(), false).get();
		Map<String, Field> baseFields = assetType.getFields().stream()
				.collect(Collectors.toMap(Field::getKey, field -> field));
		fields = fields.stream().map(field -> {
			Field baseField = baseFields.get(field.getKey());
			field.setFieldsForAsset(baseField);
			return field;
		}).collect(Collectors.toList());
		asset.setName(assetType.getName());
		asset.setFields(fields);
		return asset;
	}

//	public List<Asset> getAssetsWithoutParent() {
//		return assetRepository.findByParentIsNull();
//	}
//
//	public List<Asset> getAssetsByType(String type) {
//		return assetRepository.findByType(type);
//	}
//
//	public Long getCountByType(String type) {
//		return assetRepository.countByType(type);
//	}

	public Long getCountByTypeKey(String typeKey) {
		return assetRepository.countByTypeKey(typeKey);
	}

	public void saveAsset(Asset asset) {
		asset.setKey(UUID.randomUUID().toString());
		asset.setCreatedOn(LocalDateTime.now());
		assetRepository.save(asset);
	}

	public void updateAsset(Asset asset) {
		asset.setUpdatedOn(LocalDateTime.now());
		assetRepository.save(asset);
	}

//	public void saveAssets(Asset asset) {
//		PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.DESC, "_id");
//		List<Asset> assets = assetRepository.findAll(pageRequest).getContent();
//		int maxId = 1;
//		if (!assets.isEmpty()) {
//			Asset lastMaxAsset = assets.get(0);
//			maxId = lastMaxAsset.getId() + 1;
//		}
//		asset.setId(maxId);
//		assetRepository.save(asset);
//		if (!asset.getChild().isEmpty()) {
//			Iterable<Asset> childAssetList = assetRepository.findAllById(asset.getChild());
//			childAssetList.forEach(cAsset -> cAsset.setParent(asset.getId()));
//			assetRepository.saveAll(childAssetList);
//		}
//		if (null != asset.getParent()) {
//			Optional<Asset> optinalParentAsset = assetRepository.findById(asset.getParent());
//			if (optinalParentAsset.isPresent()) {
//				Asset parentAsset = optinalParentAsset.get();
//				List<Integer> childList = parentAsset.getChild();
//				childList.add(asset.getId());
//				parentAsset.setChild(childList);
//				assetRepository.save(parentAsset);
//			}
//		}
//	}

}
