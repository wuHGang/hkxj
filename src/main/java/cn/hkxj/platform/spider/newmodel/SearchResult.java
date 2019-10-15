package cn.hkxj.platform.spider.newmodel;

import cn.hkxj.platform.spider.newmodel.searchcourse.PageContext;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchResult<T> {

    private PageContext pageContext;
    private int pageNum;
    private int pageSize;
    private List<T> records;
}
