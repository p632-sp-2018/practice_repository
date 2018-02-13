package com.shantanu.graphql.springbootgraphql.respository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<com.shantanu.graphql.springbootgraphql.model.Book, String>{

}
