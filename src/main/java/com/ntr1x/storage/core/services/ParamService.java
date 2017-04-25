package com.ntr1x.storage.core.services;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.ForbiddenException;

import org.springframework.stereotype.Component;

import com.ntr1x.storage.core.model.Resource;
import com.ntr1x.storage.core.model.Param;
import com.ntr1x.storage.core.repository.ParamRepository;

@Component
public class ParamService implements IParamService {

    @Inject
    private EntityManager em;
    
    @Inject
    private ParamRepository params;
    
    @Override
    public List<Param> list(Long scope, long resource, String type) {
        
        return params.list(scope, resource, type);
    }
    
    @Override
    public Properties load(Long scope, long resource, String type) {
        
        List<Param> params = list(scope, resource, type);
        
        Properties properties = new Properties();
        
        if (params != null) {
            for (Param p : params) {
                properties.put(p.getName(), p.getValue());
            }
        }
        
        return properties;
    }
    
    @Override
    public void createParams(Resource resource, RelatedParam[] related) {
        
        if (related != null) {
            
            for (RelatedParam p : related) {
                
                Param v = new Param(); {
                    
                    v.setRelate(resource);
                    v.setScope(resource.getScope());
                    v.setType(p.type);
                    v.setName(p.name);
                    v.setValue(p.value);
                    
                    em.persist(v);
                }
            }
            
            em.flush();
        }
    }

    @Override
    public void updateParams(Resource resource, RelatedParam[] related) {
        
        if (related != null) {
            
            for (RelatedParam p : related) {
                
                switch (p.action) {
                
                    case CREATE: {
                        
                        Param v = new Param(); {
                            
                            v.setRelate(resource);
                            v.setScope(resource.getScope());
                            v.setType(p.type);
                            v.setName(p.name);
                            v.setValue(p.value);
                            
                            em.persist(v);
                        }
                        break;
                    }
                    case UPDATE: {
                    
                        Param v = params.select(resource.getScope(), p.id); {
                            
                            v.setName(p.name);
                            v.setValue(p.value);
                            
                            em.merge(v);
                        }
                        
                        break;
                    }
                    case REMOVE: {
                        
                        Param v = params.select(resource.getScope(), p.id); {
                            
                            if (v.getRelate().getId() != resource.getId() || v.getRelate().getScope() != resource.getScope()) {
                                throw new ForbiddenException("Param relates to another scope or resource");
                            }
                            em.remove(v);
                        }
                        break;
                    }
                default:
                    break;
                }
            }
            
            em.flush();
        }
    }
}
