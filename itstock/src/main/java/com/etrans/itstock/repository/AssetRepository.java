package com.etrans.itstock.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.etrans.itstock.model.Asset;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

//	List<Asset> findByType(String type);
//
	@Query(count = true, value = "{'typeKey':?0}")
	Long countByTypeKey(String type);
//
//	List<Asset> findByParentIsNull();

	Optional<Asset> findByTag(String tag);

}
