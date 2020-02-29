package cn.tedu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.tedu.mapper")
public class MusicServiceStart {
    public static void main(String[] args) {

        SpringApplication.run(MusicServiceStart.class,args);
    }
    @Bean
    public JedisCluster initJedis(){

        HostAndPort hostAndPort = new HostAndPort("10.9.162.121", 8000);
        return new JedisCluster(hostAndPort);
    }

}
