package com.example.sscloud.repository;


import com.example.sscloud.entity.Books;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Books, ObjectId> {

    Iterable<Books> findBooksByBookId(String id);

}
