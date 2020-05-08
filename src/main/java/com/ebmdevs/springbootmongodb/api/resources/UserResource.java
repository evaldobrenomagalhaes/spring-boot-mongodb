package com.ebmdevs.springbootmongodb.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebmdevs.springbootmongodb.api.dto.UserDTO;
import com.ebmdevs.springbootmongodb.domain.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(this.userService.toCollectionDTO(this.userService.findAll()), HttpStatus.OK);
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return new ResponseEntity<>(this.userService.toDTO(this.userService.findById(id)), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		var user = this.userService.insert(this.userService.toEntity(userDTO));
		var uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updtae(@RequestBody UserDTO userDTO, @PathVariable String id){
		userDTO.setId(id);
		this.userService.update(this.userService.toEntity(userDTO));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		this.userService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("{id}/posts")
	public ResponseEntity<?> findPosts(@PathVariable String id){
		var user = this.userService.findById(id);
		
		return ResponseEntity.ok().body(user.getPosts());
		
	}
	
}
