## 概要
ニュースサイトの情報を定期的にSNSへ自動投稿するシステムの作成。  
APIを用いたプログラムを作成する学習の一環として作成することにした。
 - 学習内容
   - APIの使用
   - JSONデータの取り扱い
   - 定期実行(spring schedulerの学習)

## 仕様
 - 使用するSNS: X(旧Twitter)
 - 使用するAPI
   - [X API v2](https://developer.twitter.com/en/support/x-api/v2)
     - freeプラン。月に1500回まで投稿可能。(50回/24h)
   - [newsapi](https://newsapi.org/)
     - freeプラン。1日100回までのリクエストが可能。
 - Xアカウント
   - [@Java_x_api](https://twitter.com/Java_x_api)
     - 学習のためにアカウントを新規で作成した。
   - 1時間に1投稿。(ただしサーバーが起動していることが条件)
   - 投稿内容は記事タイトルと記事のURL  
![スクリーンショット 2024-04-15 17 08 17](https://github.com/KeizoSasakiRP/x_post_news/assets/157101140/0204a505-9946-42d7-ab87-ad8cd6773e6c)
 - サーバー
   - 自PCのローカルサーバー
     - apache-tomcat-10.1.20  
※今回は学習目的のため、自PCの仮想環境でLinux(Ubuntu)を起動し、ローカルサーバーを建てた。




