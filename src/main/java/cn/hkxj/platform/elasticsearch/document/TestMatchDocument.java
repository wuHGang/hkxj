package cn.hkxj.platform.elasticsearch.document;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "test",type = "testMatch")
@Data
public class TestMatchDocument implements Serializable {
    private Long id;
    private String name;
}
