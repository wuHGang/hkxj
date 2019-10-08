/**
  * Copyright 2019 bejson.com 
  */
package cn.hkxj.platform.spider.newmodel.searchcourse;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2019-10-08 13:30:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ClassInfoSearchResult {

    private PageContext pageContext;
    private int pageNum;
    private int pageSize;
    private List<Records> records;

}