package com.etrans.itstock.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.Asset;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

	List<Asset> findByType(String type);

}
