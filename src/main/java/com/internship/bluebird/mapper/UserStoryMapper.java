package com.internship.bluebird.mapper;

import com.internship.bluebird.domain.UserStoryEntity;
import com.internship.bluebird.dto.UserStory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {

    UserStory entityToBusinessObject(UserStoryEntity userStoryEntity);

    List<UserStory> entitiesToBusinessObject(List<UserStoryEntity> userStoryEntityList);

    UserStoryEntity businessObjectToEntity(UserStory userStoryBusinessObject);

    List<UserStoryEntity> businessObjectsToEntities(List<UserStory> userStoryBusinessObjectList);
    
}
