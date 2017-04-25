package com.ntr1x.storage.core.services;

import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

import com.ntr1x.storage.core.model.Action;
import com.ntr1x.storage.core.model.Resource;
import com.ntr1x.storage.core.model.Param;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface IParamService {

    Properties load(Long scope, long resource, String type);
    
    void createParams(Resource resource, RelatedParam[] params);
    void updateParams(Resource resource, RelatedParam[] params);
    
    List<Param> list(Long scope, long resource, String type);
    
    @XmlRootElement
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RelatedParam {
        
        public Long id;
        public String name;
        public String type;
        public String value;
        public Action action;
    }
}
