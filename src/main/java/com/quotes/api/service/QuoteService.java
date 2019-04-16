package com.quotes.api.service;

import com.quotes.api.entity.Quote;
import com.quotes.api.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    public List<Quote> listQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuote(long id) {
        return quoteRepository.findById(id);
    }

    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteQuote(Quote quote) {
        quoteRepository.delete(quote);
    }
}
