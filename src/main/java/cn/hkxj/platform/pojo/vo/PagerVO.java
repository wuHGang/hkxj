package cn.hkxj.platform.pojo.vo;

import lombok.Data;

/**
 * @author zhouqinglai
 * @version 1.0
 * @title PagerVO
 * @desc 分页VO
 * @Date 2019/5/3
 */
@Data
public class PagerVO {

    private Integer pageSize;

    private Integer pageNum;

    private Integer count;

    private Integer totalPage;

    private Boolean hasMore;

    public static PagerVO getPager(Integer count, Integer pageNo, Integer pageSize) {
        PagerVO pagerVO = new PagerVO();
        pagerVO.setCount(count);
        pagerVO.setPageNum(pageNo);
        pagerVO.setPageSize(pageSize);
        pagerVO.setTotalPage((count + pageSize - 1) / pageSize);
        pagerVO.setHasMore(pagerVO.getTotalPage() > pageNo);
        return pagerVO;
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
