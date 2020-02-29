package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.tedu.mapper")
public class UserStart {
    public static void main(String[] args) {
        SpringApplication.run(UserStart.class,args);
    }
    @Bean
    public JedisCluster initJedis(){

        HostAndPort hostAndPort = new HostAndPort("10.9.162.121", 8000);
//        Set<HostAndPort> hostAndPortSet = new HashSet<>();
//        hostAndPortSet.add(hostAndPort);
        return new JedisCluster(hostAndPort);
    }
}
