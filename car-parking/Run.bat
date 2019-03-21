@echo off
start "Client" java -classpath "client\target\classes" car_parking.client.Program
start "Server (port:8080)" java -classpath "server\target\classes" car_parking.server.Program
