package es.unizar.tmdad.lab1.controller;

import ch.qos.logback.core.CoreConstants;
import es.unizar.tmdad.lab1.service.TwitterLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;


@RestController
public class SearchController {

    @Autowired
    TwitterLookupService twitter;


    /**
    @RequestMapping("/search")
    public SearchResults search(@RequestParam("q") String q) {
        return twitter.search(q);
    }
     */
    @MessageMapping("/search")
    public void search(String query)
    {
        twitter.search(query);
    }


    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UncategorizedApiException.class)
    public SearchResults handleUncategorizedApiException() {
        return twitter.emptyAnswer();
    }
}