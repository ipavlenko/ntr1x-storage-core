package com.ntr1x.storage.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "params")
@PrimaryKeyJoinColumn(name = "ResourceId", referencedColumnName = "Id")
@CascadeOnDelete
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Param extends Resource {

    @XmlElement
    @ManyToOne
    @JoinColumn(name = "RelateId", nullable = false, updatable = false)
    private Resource relate;
    
    @Column(name = "Type", nullable = false, updatable = false)
    private String type;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "Value")
    private String value;
}