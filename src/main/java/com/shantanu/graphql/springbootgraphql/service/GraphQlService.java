package com.shantanu.graphql.springbootgraphql.service;
//import static package.Class.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.shantanu.graphql.springbootgraphql.model.Book;
import com.shantanu.graphql.springbootgraphql.respository.BookRepository;
import com.shantanu.graphql.springbootgraphql.service.datafetcher.AllBooksDataFetcher;
import com.shantanu.graphql.springbootgraphql.service.datafetcher.BookDataFetcher;

import graphql.GraphQL;
//import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
//import graphql.schema.idl.TypeRuntimeWiring;


@Service
public class GraphQlService {
	
	@Autowired
	BookRepository bookRespository;
	
	@Value("classpath:books.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;
	
	@PostConstruct
	private void loadSchema() throws IOException{
		loadDataIntoHSQL(); 	
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private void loadDataIntoHSQL() {
		// TODO Auto-generated method stub
		Stream.of(
					new Book("123","book title", "kindle", new String[] {
							"author name"
					}, "Jan 2018"),
					new Book("124","book title2", "kindle2", new String[] {
							"author name2"
					}, "Feb 2018"),
					new Book("125","book title3", "kindle3", new String[] {
							"author name3","author name4"
					}, "Mar 2018")
				).forEach(book -> {
					bookRespository.save(book);
				});
		
		
		
	}

	private RuntimeWiring buildRuntimeWiring() {
		
		
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",typeWiring ->typeWiring 
						.dataFetcher("allBooks",allBooksDataFetcher)
						.dataFetcher("book", bookDataFetcher)
					)
				.build();
	}
	
	public GraphQL getGraphQL() {
		return graphQL;
	}
	
	
}
