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
    public Resource select(Long scope, long id) {
        return resources.select(scope, id);
    }
    
    @Override
    public Resource select(Long scope, String alias) {
        
        return resources.select(scope, alias);
    }
    
    @Override
    public Page<Resource> query(Long scope, String pattern, Pageable pageable) {
        
        return resources.query(scope, pattern, pageable);
    }
}
