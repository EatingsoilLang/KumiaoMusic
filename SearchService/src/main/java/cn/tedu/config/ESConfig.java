package cn.tedu.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "es")
public class ESConfig {
    private List<String> nodes;

    @Bean
    public TransportClient initClient(){
        try {
            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
            for (String node :
                    nodes) {
                String host = node.split(":")[0];
                int port = Integer.parseInt(node.split(":")[1]);
                InetSocketTransportAddress address = new InetSocketTransportAddress(InetAddress.getByName(host), port);
                client = client.addTransportAddresses(address);
            }
            return client;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    private List<String> getNodes() {
        return nodes;
    }
}

