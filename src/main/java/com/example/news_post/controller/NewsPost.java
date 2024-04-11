package com.example.news_post.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.signature.TwitterCredentials;

@Component
public class NewsPost {

    // Twitter APIの認証情報
    @Value("${twitter.apiKey}")
    private String apiKey;

    @Value("${twitter.apiSecretKey}")
    private String apiSecretKey;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    // 1時間毎(3600000ms)にツイートする
    @Scheduled(fixedRate = 3600000)
    public void postNewsToTwitter(){
        // ニュースの取得
        List<String> newsContents = getNews();

        // TwitterClientの初期化
        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                                                            .accessToken(accessToken)
                                                            .accessTokenSecret(accessTokenSecret)
                                                            .apiKey(apiKey)
                                                            .apiSecretKey(apiSecretKey)
                                                            .build());
        for (String news : newsContents) {
            Date dateTime = new Date();
            Tweet tweet = twitterClient.postTweet(dateTime.toString() + "¥n" + news);
            System.out.println("Tweet ID: " + tweet.getId());
        }

    }

    public List<String> getNews(){
        return List.of("Test! Here is the latest news!");
    }

}
