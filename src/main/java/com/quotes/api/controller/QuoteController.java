package com.quotes.api.controller;

import java.util.List;

import com.quotes.api.entity.Quote;
import com.quotes.api.service.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Quote>> listQuotes() {
        List<Quote> quotes = quoteService.listQuotes();
        return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Quote> getQuote(@PathVariable("id") long id) {
        Quote quote = quoteService.getQuote(id);
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Quote>(quote, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        Quote createdQuote = quoteService.saveQuote(quote);
        return new ResponseEntity<Quote>(createdQuote, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Quote> updateQuote(@PathVariable("id") long id, @RequestBody Quote quote) {
        Quote currentQuote = quoteService.getQuote(id);
        if (currentQuote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentQuote.setBody(quote.getBody());
        currentQuote.setAuthor(quote.getAuthor());
        quoteService.saveQuote(currentQuote);
        return new ResponseEntity<Quote>(currentQuote, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Quote> deleteQuote(@PathVariable("id") long id) {
        Quote quote = quoteService.getQuote(id);
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quoteService.deleteQuote(quote);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}