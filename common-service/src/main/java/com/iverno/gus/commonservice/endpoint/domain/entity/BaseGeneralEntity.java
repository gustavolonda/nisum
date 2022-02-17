package com.iverno.gus.commonservice.endpoint.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseGeneralEntity {
	@Column( columnDefinition = "timestamp NULL")
    public Timestamp createDate;
    @Column( columnDefinition = "timestamp NULL")
    private Timestamp createUpdate;
    @Column(columnDefinition = "boolean default true")
    private String status;
}
