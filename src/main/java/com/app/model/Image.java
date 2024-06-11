package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "images")
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String name;

    private Long size;

    @Column(length = 300)
    private String uid;

    @OneToOne
    @JoinTable(
        name = "student_image",
        joinColumns = @JoinColumn(name = "image_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Student student;
}
