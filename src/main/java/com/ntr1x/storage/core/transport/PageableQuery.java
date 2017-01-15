package com.ntr1x.storage.core.transport;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import io.swagger.annotations.ApiParam;

public class PageableQuery {

	@QueryParam("page")
	@ApiParam(example = "0")
	public Integer page;
	
	@QueryParam("size")
	@ApiParam(example = "10")
	public Integer size;
	
	@QueryParam("sort")
	@ApiParam(example = "id,asc")
	public List<String> sort;
	
	public Pageable toPageRequest() {
		
		if (page != null && size != null) {
			return new PageRequest(page, size, toSort(sort));
		}
		
		if (page == null) {
			return new PageRequest(0, size, toSort(sort));
		}
		
		return null;
	}
	
	private static Sort toSort(List<String> sort) {
		
		if (sort != null) {
			
			List<String> list = sort
				.stream()
				.filter(s -> s != null && !s.trim().isEmpty())
				.map(s -> s.trim())
				.collect(Collectors.toList())
			;
			
			if (!list.isEmpty()) {
				
				return new Sort(
					sort.stream()
						.filter(s -> s != null && !s.trim().isEmpty())
						.map(s -> {
							String[] parts = s.split(",");
							Direction d = parts.length < 2 ? null : Direction.fromString(parts[1]);
							String f = parts[0];
							return new Order(d, f);
						})
						.collect(Collectors.toList())
				);
			}
		}
		
		return null;
	}
	
	public static class SortField {
		
		public String property;
		public Direction direction;
	}
}
