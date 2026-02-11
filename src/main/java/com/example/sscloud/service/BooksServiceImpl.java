package com.example.sscloud.service;

import com.example.sscloud.dto.BookDto;
import com.example.sscloud.entity.Books;
import com.example.sscloud.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BooksServiceImpl implements BooksService{
    private BooksRepository booksRepository;

    @Autowired
    public BooksServiceImpl (BooksRepository booksRepository){
        this.booksRepository=booksRepository;

    }

    @Override
    public List<BookDto> getAllBooksInLibrary() {
        List<Books> booksList = new ArrayList<>();
        List<BookDto>bookDtoList=new ArrayList<>();

        booksRepository.findAll().forEach(booksList::add);
        for (Books books:booksList) {
            BookDto bookDto= BookDto.builder().id(books.getId()).name(books.getName()).author(books.getAuthor()).price(books.getPrice()).build();
            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }

    @Override
    public String saveBooks(BookDto bookDto) {
        Books books= new Books();
        books.setAuthor(bookDto.getAuthor());
        books.setName(bookDto.getName());
        try {
            booksRepository.save(books);
            return "Success";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    @Override
    public String saveBooks(String id, String name) {
        List<Books> booksList = new ArrayList<>();
        booksRepository.findBooksByName(name).forEach(booksList::add);
        booksList.forEach(
                books -> {
                    books.setName(name);
                    booksRepository.save(books);
                }
        );
        return "Success";
    }

    @Override
    public List<BookDto> getAllBooksBooksByName(String name) {
        List<Books> booksList = new ArrayList<>();
        List<BookDto>bookDtoList=new ArrayList<>();
        booksRepository.findBooksByName(name).forEach(booksList::add);
        booksList.forEach(books ->{ BookDto bookDto= BookDto.builder().id(books.getId()).name(books.getName()).author(books.getAuthor()).price(books.getPrice()).build();
        bookDtoList.add(bookDto); });
        return bookDtoList;
    }
}
