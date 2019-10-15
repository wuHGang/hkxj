package cn.hkxj.platform.spider.newmodel.searchclassroom;

import cn.hkxj.platform.spider.newmodel.SearchResult;
import lombok.Data;

@Data
public class SearchResultWrapper<T> {
    private String xqzs;
    private SearchResult<T> pageData;
}
