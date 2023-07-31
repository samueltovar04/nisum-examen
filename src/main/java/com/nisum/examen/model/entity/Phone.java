package com.nisum.examen.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    @Id
    @Column(name = "number")
    private Long number;
    @Column(name = "citycode")
    private Long cityCode;
    @Column(name = "countrycode")
    private Long countryCode;
    @ManyToOne
    @JoinColumn(name = "FK_USER", nullable = false, updatable = false)
    private User user;
}
