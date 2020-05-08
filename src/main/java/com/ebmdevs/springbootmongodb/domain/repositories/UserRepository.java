package com.ebmdevs.springbootmongodb.domain.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ebmdevs.springbootmongodb.domain.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
