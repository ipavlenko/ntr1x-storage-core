package com.ntr1x.storage.core.services;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ntr1x.storage.core.model.Resource;
import com.ntr1x.storage.core.repository.ResourceRepository;

@Service
public class ResourceService implements IResourceService {

	@Inject
	private ResourceRepository resources;
	
	@Override
	public Resource select(long id) {
		
		return resources.findOne(id);
	}
	
	@Override
	public Resource select(String alias) {
		
		return resources.findByAlias(alias);
	}
	
	@Override
	public Page<Resource> query(String pattern, Pageable pageable) {
		
		return pattern == null
			? resources.findOrderByAlias(pageable)
			: resources.findByAliasLikeOrderByAlias(pattern, pageable)
		;
	}
}
