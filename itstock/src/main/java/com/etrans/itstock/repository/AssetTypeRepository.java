package com.etrans.itstock.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.AssetType;

@Repository
public interface AssetTypeRepository extends MongoRepository<AssetType, String> {

	Optional<AssetType> findByType(String type);

}
