package com.ntr1x.storage.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ntr1x.storage.core.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	Page<Resource> findByAliasLikeOrderByAlias(String pattern, Pageable pageable);
	
	@Query(
		" SELECT r"
	  + " FROM Resource r"
	  + " WHERE (:scope IS NULL OR r.scope = :scope)"
      + "	AND (:alias IS NULL OR r.alias LIKE :alias)"
	)
	Page<Resource> query(@Param("scope") Long scope, @Param("alias") String alias, Pageable pageable);
	
	@Query(
		" SELECT r"
	  + " FROM Resource r"
	  + " WHERE (:scope IS NULL OR r.scope = :scope)"
      + "	AND (r.id = :id)"
	)
	Resource select(@Param("scope") Long scope, @Param("id") long id);
	
	@Query(
		" SELECT r"
	  + " FROM Resource r"
	  + " WHERE (:scope IS NULL OR r.scope = :scope)"
      + "	AND (r.alias = :alias)"
	)
	Resource select(@Param("scope") Long scope, @Param("alias") String alias);
}
