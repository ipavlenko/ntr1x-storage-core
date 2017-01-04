package com.ntr1x.storage.core.services;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ntr1x.storage.core.model.Resource;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface IResourceService {

	Resource select(long id);
	Resource select(String alias);
	
	Page<Resource> query(String pattern, Pageable pageable);
	
	@XmlRootElement
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResourcePageResponse {

    	public long count;
        public int page;
        public int size;

        @XmlElement
        public List<Resource> content;
	}
}
