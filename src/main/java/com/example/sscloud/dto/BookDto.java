package com.example.sscloud.dto;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Builder
@Data
public class BookDto implements Serializable {
    private ObjectId id;
    private String name;
    private String author;
    private Double price;
}
