package cn.hkxj.platform.pojo.vo;

import lombok.Data;

/**
 * @author zhouqinglai
 * @version 1.0
 * @title PageVo
 * @desc 分页VO
 * @Date 2019/5/3
 */
@Data
public class PageVo {

    private Integer pageSize;

    private Integer pageNum;

    private Integer count;

    private Integer totalPage;

    private Boolean hasMore;

    public static PageVo getPage(Integer count, Integer pageNo, Integer pageSize) {
        PageVo pageVo = new PageVo();
        pageVo.setCount(count);
        pageVo.setPageNum(pageNo);
        pageVo.setPageSize(pageSize);
        pageVo.setTotalPage((count + pageSize - 1) / pageSize);
        pageVo.setHasMore(pageVo.getTotalPage() > pageNo);
        return pageVo;
    }

    /**
     * 获取开始索引下标位置
     * @param pageNum 当前是第几页
     * @param pageSize 每页内容
     * @return
     */
    public static int getStart(Integer pageNum, Integer pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 获取结束索引下标位置
     * @param pageNum 当前是第几页
     * @param pageSize 页面大小
     * @return
     */
    public static int getEnd(Integer pageNum, Integer pageSize) {
        return getStart(pageNum,pageSize) + pageSize - 1;
    }


}
