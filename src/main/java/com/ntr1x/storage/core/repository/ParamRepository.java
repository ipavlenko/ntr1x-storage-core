package com.ntr1x.storage.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParamRepository extends JpaRepository<com.ntr1x.storage.core.model.Param, Long> {
    
    @Query(
        " SELECT p"
      + " FROM Param p"
      + " WHERE (:scope IS NULL OR p.scope = :scope)"
      + "    AND (p.id = :id)"
    )
    com.ntr1x.storage.core.model.Param select(
        @Param("scope") Long scope,
        @Param("id") long id
    );
    
    @Query(
        " SELECT p"
      + " FROM Param p"
      + " WHERE (p.relate.id = :relate)"
      + "   AND (p.type = :type)"
      + "   AND (:scope IS NULL OR p.scope = :scope)"
      
    )
    List<com.ntr1x.storage.core.model.Param> list(
        @Param("scope") Long scope,
        @Param("relate") long relate,
        @Param("type") String type
    );
}
