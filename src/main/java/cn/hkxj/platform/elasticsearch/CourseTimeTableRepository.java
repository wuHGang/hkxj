package cn.hkxj.platform.elasticsearch;

import cn.hkxj.platform.elasticsearch.document.CourseTimeTableDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTimeTableRepository extends ElasticsearchRepository<CourseTimeTableDocument,Long> {
}
