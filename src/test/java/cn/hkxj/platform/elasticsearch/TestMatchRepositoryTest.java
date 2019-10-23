package cn.hkxj.platform.elasticsearch;

import cn.hkxj.platform.elasticsearch.document.TestMatchDocument;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestMatchRepositoryTest {

    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }
    @Resource
    private TestMatchRepository testMatchRepository;

    @Test
    public void add() {
        TestMatchDocument document = new TestMatchDocument();
        document.setName("管理学院");
        TestMatchDocument save = testMatchRepository.save(document);
        System.out.println(save);
//        MatchPhraseQueryBuilder query = QueryBuilders.matchPhraseQuery("name", "管院");
//
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(query).build();
//
//        for (TestMatchDocument search : testMatchRepository.search(searchQuery)) {
//            System.out.println(search);
//        }


    }

    @Test
    public void search() {

        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "管理");

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(query).build();

        for (TestMatchDocument search : testMatchRepository.search(searchQuery)) {
            System.out.println(search);
        }


    }

}