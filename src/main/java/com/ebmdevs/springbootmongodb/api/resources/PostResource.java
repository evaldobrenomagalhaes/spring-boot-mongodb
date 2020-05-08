package com.ebmdevs.springbootmongodb.api.resources;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebmdevs.springbootmongodb.api.resources.util.URL;
import com.ebmdevs.springbootmongodb.domain.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {
	@Autowired
	private PostService postService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return new ResponseEntity<>(this.postService.findById(id), HttpStatus.OK);
		
	}
	
	@GetMapping("/tilesearch")
	public ResponseEntity<?> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		
		return new ResponseEntity<>(this.postService.findByTitle(text), HttpStatus.OK);
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<?> fullsearch(@RequestParam(value="text", defaultValue = "") String text,
										@RequestParam(value="minDate", defaultValue="") String minDate,
							 			@RequestParam(value="maxDate", defaultValue="") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		return new ResponseEntity<>(this.postService.fullSearch(text, min, max), HttpStatus.OK);
	}
	
}
