package br.com.dgc.simplecatalogue.service.impl;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.repository.impl.BookRepositoryImpl;
import br.com.dgc.simplecatalogue.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements WorkService<Book> {

    private final BookRepositoryImpl repository;

    public BookServiceImpl(BookRepositoryImpl repository){
        this.repository = repository;
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> read() {
        return repository.findAll();
    }

    @Override
    public Book readById(Long id){
        return repository.findById(id).orElse(new Book());
    }

    @Override
    public Book update(Book book) {
        return repository.save(book);
    }

    @Override
    public void delete() {
        repository.deleteAll();
    }
}
