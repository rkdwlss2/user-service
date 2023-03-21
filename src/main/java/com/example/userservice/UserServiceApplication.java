package com.example.userservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @GetMapping("/config")
    public String string(@Value("${message.owner}") String messageOwner, @Value("${message.content}") String messageContent){
        return "Configuration File's Message Owner: "+ messageOwner+"\n" +"Configuration Files's Message Content: "+messageContent;
    }

    @GetMapping("/config/database")
    public String database(@Value("${spring.datasource.driver}") String driver,
                           @Value("${spring.datasource.url}") String url,
                           @Value("${spring.datasource.username}") String username,
                           @Value("${spring.datasource.password}") String password,
                           @Value("${jwt.token.key}") String tokenKey
                           ){
        return "driver : "+driver+"\n"
                +"url : "+url+"\n"
                +"username : "+username+"\n"
                +"password : "+password+"\n"
                +"tokenKey : "+tokenKey+"\n";
    }

    @GetMapping("/info")
    public String info(@Value("${server.port}") String port){
        return "User 서비스의 기본동작 port : {"+port+"}";
    }

    @GetMapping("/auth")
    public String auth(@RequestHeader(value = "token") String token){
        return "token is "+token;
    }
}
