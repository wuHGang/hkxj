package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.TaskMapper;
import cn.hkxj.platform.pojo.Task;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TaskBindingService {

    @Autowired
    TaskMapper taskMapper;

    /***
     *查询用户是否绑定要记录调用次数的服务
     * @param openid 用户的openid
     * @param taskName 绑定任务的名字
     */
    public void isTaskBinding(String openid, String taskName){
        System.out.println(taskMapper.isTaskBinding(openid,1));
        if(taskMapper.isTaskBinding(openid,1)==null){
            log.info("new task binding ");

        }
    }

    public void taskBinding(String openid,int updateType){
        Task task=new Task();
        task.setOpenid(openid);
        task.setUpdateType(updateType);
        task.setCount(0);
        taskMapper.taskBinding(task);
    }
}
