package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Entity
public class Person extends PanacheEntity {

    private String name;

    private Integer age;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
