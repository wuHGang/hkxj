package cn.hkxj.platform.elasticsearch;

import cn.hkxj.platform.elasticsearch.document.TestMatchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMatchRepository extends ElasticsearchRepository<TestMatchDocument,Long> {
}
