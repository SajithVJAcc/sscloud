package com.example.sscloud.service;

import com.example.sscloud.dto.BookDto;

import java.util.List;

public interface BooksService {

    List<BookDto> getAllBooksInLibrary();

    String saveBooks(BookDto bookDto);

    String saveBooks(String id,String name);
}
