package cn.tedu.service;

import cn.tedu.mapper.SearchMapper;
import cn.tedu.pojo.Music;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SearchService {
    @Autowired
    private SearchMapper sm;
    @Autowired
    private TransportClient client;

    public List<Music> searchNews(String query, Integer page, Integer rows) {
        try {
            //构造查询query对象
//            MatchQueryBuilder queryBuilder1 = QueryBuilders.matchQuery("name", query);
//            MatchQueryBuilder queryBuilder2 = QueryBuilders.matchQuery("writer", query);
            MultiMatchQueryBuilder queryBuilder1 = QueryBuilders.multiMatchQuery(query, "name", "writer");
            //搜索
            SearchRequestBuilder request = client.prepareSearch("mindex");
            System.out.println(request);

            request.setQuery(queryBuilder1);
            request.setFrom((page - 1) * rows);
            request.setSize(rows);
            SearchResponse response = request.get();
            System.out.println("查询总数：" + response.getHits().totalHits + "===" + response);

            //获取source元素
            SearchHit[] hits = response.getHits().getHits();
            List<Music> mList = new ArrayList<>();
            for (SearchHit hit :
                    hits) {
                String json = hit.getSourceAsString();
                Music news = new ObjectMapper().readValue(json, Music.class);
                mList.add(news);
            }
            System.out.println(mList);
            return mList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void createIndex(String indexName) {
        try {
            //索引管理对象
            IndicesAdminClient indices = client.admin().indices();
            //判断索引是否存在
            IndicesExistsResponse response = indices.prepareExists(indexName).get();
            if (!response.isExists()) {
                //不存在则创建
                indices.prepareCreate(indexName).get();
            }
            //读取数据源，得到商品数据
            List<Music> mList = sm.selectMusic();
            System.out.println("gmList: " + mList);
            //将json解析成source，设置在request对象中
            for (Music m : mList) {
//                String json = MapperUtil.MP.writeValueAsString(gn);
                String mJson = new ObjectMapper().writeValueAsString(m);
                IndexRequestBuilder request = client.prepareIndex(indexName, "music", String.valueOf(m.getId()));
                //封装source信息，发送请求，写入索引
                request.setSource(mJson).get();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
