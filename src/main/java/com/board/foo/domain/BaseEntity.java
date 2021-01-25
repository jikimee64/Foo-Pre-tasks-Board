package com.board.foo.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedBy
    @Column(name ="created_by", length = 20, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name="updated_by", length = 20)
    private String updatedBy;

}
