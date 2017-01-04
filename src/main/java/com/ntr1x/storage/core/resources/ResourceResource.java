package com.ntr1x.storage.core.resources;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.ntr1x.storage.core.model.Resource;
import com.ntr1x.storage.core.services.IResourceService;
import com.ntr1x.storage.core.services.IResourceService.ResourcePageResponse;
import com.ntr1x.storage.core.transport.PageableQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Path("resources")
@Api("Resources")
@Component
public class ResourceResource {
	
	@Inject
	private IResourceService resources;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@RolesAllowed({ "res:///:admin" })
	public ResourcePageResponse list(
    		@QueryParam("pattern") @ApiParam(example = "%") String pattern,
    		@BeanParam PageableQuery pageable
    ) {
		
		Page<Resource> p = resources.query(
			pattern,
			pageable.toPageRequest()
		);
        
        return new ResourcePageResponse(
    		p.getTotalElements(),
    		p.getNumber(),
    		p.getSize(),
    		p.getContent()
		);
    }
	
	@GET
	@Path("/i/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@RolesAllowed({ "res:///:admin" })
	public Resource select(@PathParam("id") long id) {
		return resources.select(id);
    }
	
	@GET
	@Path("/a/{alias}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@RolesAllowed({ "res:///:admin" })
	public Resource select(@PathParam("alias") String alias) {
		return resources.select(alias);
    }
}
