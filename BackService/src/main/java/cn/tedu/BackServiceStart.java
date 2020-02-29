package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.tedu.mapper")
public class BackServiceStart {
    public static void main(String[] args) {
        SpringApplication.run(BackServiceStart.class,args);
    }
}
