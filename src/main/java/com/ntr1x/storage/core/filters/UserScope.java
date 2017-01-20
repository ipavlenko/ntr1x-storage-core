package com.ntr1x.storage.core.filters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserScope implements IUserScope, Serializable {
	
	private static final long serialVersionUID = -75045510472354672L;
	
	@Getter
	private final long id;
	
	private Map<Class<?>, Object> data = new HashMap<>();
	
	@Override
	public <T> UserScope with(Class<T> clazz, T instance) {
		data.put(clazz, instance);
		return this;
	}
	
	@Override
	public <T> T get(Class<T> clazz) {
		return clazz.cast(data.get(clazz));
	}
}
