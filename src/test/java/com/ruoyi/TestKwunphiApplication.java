package com.ruoyi;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
public class TestKwunphiApplication {

    @Autowired
    private ElasticsearchClient client;

    @Test
    public void createTest() throws IOException {
        //写法比RestHighLevelClient更加简洁
        client.indices().create(c -> c.index("testes"));

    }

}
