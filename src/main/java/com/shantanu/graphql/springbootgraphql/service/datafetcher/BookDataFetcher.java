package com.shantanu.graphql.springbootgraphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shantanu.graphql.springbootgraphql.model.Book;
import com.shantanu.graphql.springbootgraphql.respository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
@Component
public class BookDataFetcher implements DataFetcher<Book> {
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
		String isn = dataFetchingEnvironment.getArgument("id");
			
		return bookRepository.findOne(isn);
	}

}
