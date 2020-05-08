package com.ebmdevs.springbootmongodb.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebmdevs.springbootmongodb.api.dto.UserDTO;
import com.ebmdevs.springbootmongodb.domain.entity.User;
import com.ebmdevs.springbootmongodb.domain.repositories.UserRepository;
import com.ebmdevs.springbootmongodb.domain.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return this.userRepository.findAll();
		
	}
	
	public User findById(String id) {
		Optional<User> optional = this.userRepository.findById(id);
		
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public User insert(User user) {
		return this.userRepository.insert(user);
		
	}
	
	public User update(User user) {
		var userNew = this.findById(user.getId());
		userNew = updateData(user, userNew);
		this.userRepository.save(userNew);
		
		return userNew;
	}
	
	public void delete(String id) {
		var user = this.findById(id);
		this.userRepository.deleteById(user.getId());
		
	}
	
	public UserDTO toDTO(User user) {
		return new UserDTO(user);
		
	}
	
	public List<UserDTO> toCollectionDTO(List<User> users){
		return users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		
	}
	
	public User toEntity(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
		
	}
	
	private User updateData(User userOld, User userNew) {
		userNew.setName(userOld.getName());
		userNew.setEmail(userOld.getEmail());
		
		return userNew;
		
	}
	
}
