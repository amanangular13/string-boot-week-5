package com.Aman.SpringSecurityProject.repositories;

import com.Aman.SpringSecurityProject.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
