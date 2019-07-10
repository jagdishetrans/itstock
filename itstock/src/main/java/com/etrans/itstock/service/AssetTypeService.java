package com.etrans.itstock.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.AssetType;
import com.etrans.itstock.model.Field;
import com.etrans.itstock.repository.AssetTypeRepository;
import com.etrans.itstock.repository.FieldRepository;

@Service
public class AssetTypeService {

	@Autowired
	AssetTypeRepository assetTypeRepository;

	@Autowired
	FieldRepository fieldRepository;

	public void saveAssetType(AssetType assetType) {
		assetType.setKey(UUID.randomUUID().toString());
		assetType.setCreatedOn(LocalDateTime.now());
		assetTypeRepository.save(assetType);
	}

	public void updateAssetType(AssetType assetType) {
		assetType.setUpdatedOn(LocalDateTime.now());
		assetTypeRepository.save(assetType);
	}

	public Optional<AssetType> getAssetTypeByName(String name, boolean raw) {
		Optional<AssetType> assetType = assetTypeRepository.findByName(name);
		if (!raw && assetType.isPresent()) {
			assetType = Optional.of(attachAssetTypeWithBaseField(assetType.get()));
		}
		return assetType;

	}

	public Optional<AssetType> getAssetType(String id, boolean raw) {
		Optional<AssetType> assetType = assetTypeRepository.findById(id);
		if (!raw && assetType.isPresent()) {
			assetType = Optional.of(attachAssetTypeWithBaseField(assetType.get()));
		}
		return assetType;
	}

	private AssetType attachAssetTypeWithBaseField(AssetType assetType) {
		List<Field> assetTypeFields = assetType.getFields();
		List<String> keys = assetTypeFields.stream().map(field -> field.getKey()).collect(Collectors.toList());
		Map<String, Field> baseFields = new HashMap<>(assetTypeFields.size());
		fieldRepository.findAllById(keys).forEach(field -> {
			baseFields.put(field.getKey(), field);
		});
		assetTypeFields = assetTypeFields.stream().map(field -> {
			Field bField = baseFields.get(field.getKey());
			field.setFieldsForAssetType(bField);
			return field;
		}).collect(Collectors.toList());
		assetType.setFields(assetTypeFields);
		return assetType;
	}

	public List<AssetType> getAllAssetType() {
		return assetTypeRepository.findAll();
	}

	public List<AssetType> getAllAssetLabel() {
		return assetTypeRepository.findAll().stream().map(aType -> {
			aType.setFields(null);
			return aType;
		}).collect(Collectors.toList());
	}

}
