package com.example.sscloud.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "books")
public class Books {
 @Id
 private ObjectId id;
 @Field(value = "name")
 private String name;
 @Field(value = "author")
 private String author;
 @Field(value = "price")
 private Double price;
}
