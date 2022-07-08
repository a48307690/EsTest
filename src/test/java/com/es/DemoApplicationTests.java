package com.es;

import com.alibaba.fastjson.JSON;
import com.es.dao.EbookEntryDao;
import com.es.entity.EbookEntry;
import com.es.service.impl.Mail;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.recycler.Recycler;
import org.elasticsearch.common.xcontent.SuggestingErrorOnUnknown;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.engine.Engine;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.net.CacheRequest;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private EbookEntryDao ebookEntryDao;

    private RestHighLevelClient client;
  @Autowired
    private Mail mail;

    @BeforeEach
    void setUp() {
        HttpHost httpHost = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(httpHost);
        client = new RestHighLevelClient(builder);
    }

    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }

    @Test
    void contextLoads() throws IOException {
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("books");
        client.indices().create(request, RequestOptions.DEFAULT);

    }

    @Test
        //添加数据
    void contextList() throws IOException {
        List<EbookEntry> ebookEntries = ebookEntryDao.selectList(null);
        BulkRequest bulkRequest = new BulkRequest();
        for (EbookEntry ebookEntry : ebookEntries) {
            IndexRequest indexRequest = new IndexRequest("book").id(ebookEntry.getId().toString());
            String s = JSON.toJSONString(ebookEntry);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Test
    //查询当个
    public void selectAid() throws IOException {
        GetRequest request = new GetRequest("book", "1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        String sourceAsString = documentFields.getSourceAsString();
        System.out.println(sourceAsString);

    }

    @Test
    //条件查询
    public void SelectAll() throws IOException {
        SearchRequest request=new SearchRequest("book");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
         searchSourceBuilder.query(QueryBuilders.termQuery("summary","解"));
           request.source(searchSourceBuilder);
        SearchResponse search = client.search(request,RequestOptions.DEFAULT);
        SearchHits hits= search.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }

    }
    @Test
    //发送邮件
    public void  send(){
        mail.wc();
    }


}
