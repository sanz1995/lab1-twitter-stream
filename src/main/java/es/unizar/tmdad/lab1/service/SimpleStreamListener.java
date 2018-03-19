package es.unizar.tmdad.lab1.service;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.MimeTypeUtils;
import sun.net.www.MessageHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge on 13/03/18.
 */
public class SimpleStreamListener implements StreamListener {

    private String q;
    private MessageSendingOperations ops;

    public SimpleStreamListener(MessageSendingOperations messagingTemplate, String query){
        ops = messagingTemplate;
        q=query;
    }

    @Override
    public void onTweet(Tweet tweet) {
        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        ops.convertAndSend("/queue/search/"+q,tweet, map);
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {

    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {

    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {

    }
}
