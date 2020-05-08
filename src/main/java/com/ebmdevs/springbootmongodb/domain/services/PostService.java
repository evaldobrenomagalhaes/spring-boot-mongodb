package com.ebmdevs.springbootmongodb.domain.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebmdevs.springbootmongodb.domain.entity.Post;
import com.ebmdevs.springbootmongodb.domain.repositories.PostRepository;
import com.ebmdevs.springbootmongodb.domain.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id){
		return this.postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post não econtrado"));
		
	}
	
	public List<Post> findByTitle(String title){
		return this.postRepository.findByTitleContainingIgnoreCase(title);
		
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return this.postRepository.fullSearch(text, minDate, maxDate);
	}
	
}
