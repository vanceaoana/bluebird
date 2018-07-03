package com.internship.bluebird.service;

import com.internship.bluebird.domain.UserStoryEntity;
import com.internship.bluebird.dto.UserStory;
import com.internship.bluebird.mapper.UserStoryMapper;
import com.internship.bluebird.repo.UserStoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserStoryService {

    private UserStoryRepo userStoryRepo;
    private UserStoryMapper userStoryMapper;

    @Autowired
    public UserStoryService(UserStoryRepo userStoryRepo, UserStoryMapper userStoryMapper) {
        this.userStoryRepo = userStoryRepo;
        this.userStoryMapper = userStoryMapper;
    }

    public UserStory create(UserStory userStory)
    {
        UserStoryEntity userStoryEntity = userStoryMapper.businessObjectToEntity(userStory);
        return userStoryMapper.entityToBusinessObject(userStoryRepo.save(userStoryEntity));
    }

    public UserStory get(Integer id){
        Optional<UserStoryEntity> userStoryEntityOptional = userStoryRepo.findById(id);

        if (userStoryEntityOptional.isPresent()) {
            return userStoryMapper.entityToBusinessObject(userStoryEntityOptional.get());
        }

        throw new EntityNotFoundException();
    }

    public void delete(Integer id)
    {
        userStoryRepo.deleteById(id);
    }

    public UserStory update(UserStory userStory)
    {
        UserStoryEntity userStoryEntity = userStoryMapper.businessObjectToEntity(userStory);
        return userStoryMapper.entityToBusinessObject(userStoryRepo.save(userStoryEntity));
    }

}
