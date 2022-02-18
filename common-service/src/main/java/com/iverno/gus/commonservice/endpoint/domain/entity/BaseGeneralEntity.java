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
	@Column(name = "create_date", columnDefinition = "timestamp NULL")
    public Timestamp createDate;
    @Column(name = "create_update", columnDefinition = "timestamp NULL")
    private Timestamp createUpdate;
    @Column(columnDefinition = "varchar(1)")
    private String status;
}
