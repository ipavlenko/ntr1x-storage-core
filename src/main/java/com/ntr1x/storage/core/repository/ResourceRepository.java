package com.ntr1x.storage.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ntr1x.storage.core.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	Resource findByAlias(String alias);
	Page<Resource> findByAliasLikeOrderByAlias(String pattern, Pageable pageable);
	Page<Resource> findOrderByAlias(Pageable pageable);
}
