package com.example.demo.notify;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月19日 17:53
 */
public interface INotifyService {
    /**
     * 处理类型
     */
    public String handleType();
    /**
     * 处理具体业务
     */
    Integer handle(String notifyBody);
}
