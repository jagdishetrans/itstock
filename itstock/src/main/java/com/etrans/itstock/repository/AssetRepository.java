package com.etrans.itstock.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.Asset;

@Repository
public interface AssetRepository extends MongoRepository<Asset, Integer> {

	List<Asset> findByType(String type);

	@Query(count = true, value = "{'type':?0}")
	Long countByType(String type);

	List<Asset> findByParentIsNull();

}
