package com.ntr1x.storage.core.services;

import java.util.Map;

public interface IPageService {
	
	String page(String name, Map<String, ?> model);
}
