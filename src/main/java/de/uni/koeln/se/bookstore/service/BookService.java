package de.uni.koeln.se.bookstore.service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> findBooks() {
        return bookRepo.findAll();
    }

    public Optional<Book> fetchBook(int id) {
        return bookRepo.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public boolean deleteBook(int id) {
        boolean status;
        try {
            bookRepo.deleteById(id);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public boolean checkSize() {
        List<Book> bookList = bookRepo.findAll();
        if (bookList.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Book fetchOldestBook() {
        List<Book> bookList = bookRepo.findAll();
        int oldest = Integer.MAX_VALUE;
        Book temp = new Book();
        for (Book book : bookList) {
            if (book.getYear() < oldest) {
                temp = book;
                oldest = book.getYear();
            }
        }
        return temp;
    }

    public Book fetchLatestBook() {
        List<Book> bookList = bookRepo.findAll();
        int latest = Integer.MIN_VALUE;
        Book temp = new Book();
        for (Book book : bookList) {
            if (book.getYear() > latest) {
                temp = book;
                latest = book.getYear();
            }
        }
        return temp;
    }
}
