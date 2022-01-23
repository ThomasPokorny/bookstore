#!/bin/bash
mvn -f BookStoreProxy/ clean install
cp BookStoreProxy/target/BookStoreProxy.jar BookStoreProxy/install/
#
mvn -f BookService/ clean install
cp BookService/target/BookService.jar  BookService/install/
# 
cd install/
docker-compose up --build
