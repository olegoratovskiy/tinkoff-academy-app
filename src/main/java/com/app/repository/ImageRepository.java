package com.app.repository;

import com.app.model.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUid(String uid);

}
