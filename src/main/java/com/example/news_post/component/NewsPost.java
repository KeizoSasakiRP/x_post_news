package com.example.news_post.component;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.news_post.domain.NewsData;

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

    // news API情報
    @Value("${news.apiKey}")
    private String newsApiKey;

    @Value("${news.apiEndPoint}")
    private String newsApiEndPoint;

    // 1時間毎(3600000ms)にツイートする
    @Scheduled(fixedRate = 3600000)
    public void postNewsToTwitter() {
        // ニュースの取得
        List<String> newsContents = getNewsTitleAndUrl();

        // TwitterClientの初期化
        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(accessToken)
                .accessTokenSecret(accessTokenSecret)
                .apiKey(apiKey)
                .apiSecretKey(apiSecretKey)
                .build());

        Tweet tweet = twitterClient.postTweet("記事タイトル「" + newsContents.get(0) + "」\n" + newsContents.get(1));
        System.out.println("Tweet ID: " + tweet.getId());

    }

    public List<String> getNewsTitleAndUrl() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format("%s?country=jp&apiKey=%s", newsApiEndPoint, newsApiKey);
        NewsData newsData = restTemplate.getForObject(apiUrl, NewsData.class);

        // xへPostする記事を抽選する
        int minimumNumberArticle = 1;
        int maximumNumberArticle = newsData.getArticles().size();
        int selectedArticleNumber = generateRandomNumber(minimumNumberArticle, maximumNumberArticle);

        // 抽選された記事のタイトルとURLを取得する
        String articleTitle = newsData.getArticles().get(selectedArticleNumber).getTitle();
        String articleUrl = newsData.getArticles().get(selectedArticleNumber).getUrl();

        return List.of(articleTitle, articleUrl);
    }

    // 指定された範囲内のランダムな整数を生成するメソッド
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("最小値は最大値より小さくする必要があります");
        }

        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
