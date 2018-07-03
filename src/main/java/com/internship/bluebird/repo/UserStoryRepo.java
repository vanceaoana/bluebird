package com.internship.bluebird.repo;

import com.internship.bluebird.domain.UserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepo extends JpaRepository<UserStoryEntity,Integer>{
}
