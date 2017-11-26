#!/usr/bin/env bash

docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql || docker start mysqldb

docker run --name recipe-app -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev --link mysqldb:mysql -d spring-web-image
