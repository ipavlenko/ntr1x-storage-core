package com.ntr1x.storage.core.reflection;

import com.ntr1x.storage.core.model.Resource;

public class ResourceUtils {
    
    public static String alias(Resource parent, String locator, Resource resource) {
        
        return parent != null
            ? String.format("%s/%s/%d/", parent.getAlias(), locator, resource.getId())
            : String.format("/%s/%d/", locator, resource.getId())
        ;
    }
}
