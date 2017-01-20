package com.ntr1x.storage.core.filters;

public interface IUserScope {
	
	long getId();

	<T> T get(Class<T> clazz);
	<T> IUserScope with(Class<T> clazz, T instance);
}
