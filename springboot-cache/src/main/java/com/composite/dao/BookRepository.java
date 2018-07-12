package com.composite.dao;

import com.composite.entity.Book;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
