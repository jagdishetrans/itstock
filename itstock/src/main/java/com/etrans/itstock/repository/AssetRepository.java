package com.etrans.itstock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.etrans.itstock.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {

	// @Query(value ="DELETE FROM a WHERE a.NAME=:name")
	// public void deleteAsset(@Param("name") String name);

}
