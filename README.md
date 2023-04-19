# Spring Boot + Apache Kafka + Mongo DB + Elasticsearch + Redis

Dummy project that using those stack.

## System Overview

This application will only provide CRUD for 1 object called Product.
1. Create : save data to Redis / MongoDB, and publish Kafka to send email notif.
2. Find by ID : get data from Redis / MongoDB.
3. Find all : get data from Elasticsearch.
4. Update : update data to Redis / MongoDB.
5. Delete : delete data from Redis / MongoDB, and publish Kafka to send email notif.

We use [Monstache](https://rwynn.github.io/monstache-site/) to sync data from MongoDB to Elasticsearch (can't vice versa). So every data event at MongoDB will be synchronized to Elasticsearch.

## Getting Started

Version app used :
- MongoDB : 4.2.8
- Apache Kafka : 2.5.0 & Apache Zookeeper : 3.6.1
- Monstache : 6.6.0
- Redis : 5.0.3
- Elasticsearch : 7.8.0

### Installation

Install all the application used above, and clone this source.


### Running the app
Make sure all the applications above already run successfully before you run the source.

There are 4 kind of properties used here. You won't be confused about those things since the name already represent its function.

Please adjust it based on your application environment.