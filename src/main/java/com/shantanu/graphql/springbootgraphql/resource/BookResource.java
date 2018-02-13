package com.shantanu.graphql.springbootgraphql.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shantanu.graphql.springbootgraphql.service.GraphQlService;

import graphql.ExecutionResult;

@RequestMapping("/rest/books")
@RestController
public class BookResource {

	@Autowired
	GraphQlService graphQLService;
	
	
	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query){
		
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
}
