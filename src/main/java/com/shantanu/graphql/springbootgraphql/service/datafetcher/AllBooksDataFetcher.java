package com.shantanu.graphql.springbootgraphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shantanu.graphql.springbootgraphql.model.Book;
import com.shantanu.graphql.springbootgraphql.respository.BookRepository;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment){
		return bookRepository.findAll();
		
	}

}
