package com.sparta.test002.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class TimeStamped {

    @CreatedDate //작성일자.
    private LocalDateTime createdAt;

    @LastModifiedDate //수정일자.
    private LocalDateTime updatedAt;
}
