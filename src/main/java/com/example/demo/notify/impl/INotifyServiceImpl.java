package com.example.demo.notify.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.notify.INotifyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月19日 17:57
 */
@Service
public class INotifyServiceImpl implements INotifyService{

    private ApplicationContext applicationContext;
    private Map<String,INotifyService> notifyServiceMap;

    /**
     * 启动加载
     */
    @PostConstruct
    public void init(){
        Map<String,INotifyService> map = applicationContext.getBeansOfType(INotifyService.class);
        Collection<INotifyService> services = map.values();
        if(CollectionUtils.isEmpty(services)){
            return;
        }
        notifyServiceMap = services.stream().collect(Collectors.toMap(INotifyService::handleType, x -> x));
    }


    public Map<String, INotifyService> getNotifyServiceMap() {
        return notifyServiceMap;
    }

    /**
     * 处理类型
     */
    @Override
    public String handleType() {
        return null;
    }

    @Override
    public Integer handle(String body) {
        //参数处理+验签逻辑

//        //获取具体的业务实现类
//        INotifyService notifyService = notifyServiceMap.get(notifyType);
        Integer status = null;
//        if (Objects.nonNull(notifyService)) {
//            //执行具体业务
//            try {
//                status = notifyService.handle(JSON.toJSONString(requestParameter));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //后续逻辑处理
       return status;
    }
}
