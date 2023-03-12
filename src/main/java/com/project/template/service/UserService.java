package com.project.template.service;

import com.project.template.persistence.entity.UserEntity;
import com.project.template.persistence.repository.UserRepository;
import com.project.template.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @CacheEvict(value = "getUsers", allEntries = true)
    public void createUser(UserEntity userEntity){
        userRepository.save(userEntity);
        kafkaProducer.produceData(userEntity.toString());
    }

    @Cacheable(value = "getUsers", key = "#country", cacheManager = "defaultCacheManager", unless = "#result == null")
    public List<UserEntity> getUsersByCountry(String country){
        return userRepository.findAllByCountry(country);
    }

    @CacheEvict(value = "getUsers", allEntries = true)
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
