package com.ntr1x.storage.core.transport;

import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiParam;

public class PageableQuery {

	@QueryParam("page")
	@ApiParam(example = "0")
	public Integer page;
	
	@QueryParam("size")
	@ApiParam(example = "10")
	public Integer size;
	
	public Pageable toPageRequest() {
		
		if (page != null && size != null) {
			return new PageRequest(page, size);
		}
		
		if (page == null) {
			return new PageRequest(0, size);
		}
		
		return null;
	}
}
