package cn.tedu.controller;

import cn.tedu.pojo.Music;
import cn.tedu.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService ss;

    //    http://www.music.com/search/query?query=yyyy&page=1&rows=12
      @RequestMapping("/query")
  public List<Music> searchMusic(String query, Integer page, Integer rows) {
        List<Music> mList = ss.searchNews(query, page, rows);
        System.out.println("newsList:   "+mList);
        return mList;
    }

    //访问该接口，创建一次索引文件
//    http://localhost:15000/search/create/mindex
    @RequestMapping("/create/{indexName}")
    public String createIndex(@PathVariable String indexName) {
        try {
            ss.createIndex(indexName);
            return "创建索引成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "创建索引失败";
        }
    }

}
