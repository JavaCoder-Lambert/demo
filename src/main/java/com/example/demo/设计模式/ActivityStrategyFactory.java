package com.example.demo.设计模式;


import com.example.demo.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * 实践总结
 * * 在实际项目代码中我们采用的是简单工厂模式（静态工厂模式），实现时利用枚举（或者映射配置表）来保存渠道类型与具体策略实现类的映射关系，再结合Spring的单例模式，来进行策略类的创建。
 * * 相比于工厂方法模式，在满足业务的前提下，减少了工厂类数量，代码更加简单适用。
 * @CreateTime 2021年10月11日 11:04
 */
@Component
@Slf4j
public class ActivityStrategyFactory {

    public static class ActivityStrategy{

    }

    public static ActivityStrategy getActivityStrategy(ChannelTypeEnum channelType) {

        ChannelTypeStrategyEnum channelTypeStrategyEnum = ChannelTypeStrategyEnum.getByChannelType(channelType);
        Assert.notNull(channelTypeStrategyEnum , "指定的渠道类型[channelType=" + channelType + "]不存在");

        String strategyName= channelTypeStrategyEnum.getStrategyName();
        Assert.notNull(strategyName, "指定的渠道类型[channelType=" + channelType + "未配置策略");
        return (ActivityStrategy) SpringContextUtils.getBean(strategyName);
    }

    public enum ChannelTypeEnum{
        SMS,
        WX,
        PUSH
    }

    public enum ChannelTypeStrategyEnum {
        /**
         * 短信渠道
         */
        SMS(ChannelTypeEnum.SMS, "smsActivityStrategy"),
        /**
         * 微信渠道
         */
        WX_NEWS(ChannelTypeEnum.WX, "wxActivityStrategy"),
        /**
         * push渠道
         */
        PUSH(ChannelTypeEnum.PUSH, "pushActivityStrategy"),;

        private final ChannelTypeEnum channelTypeEnum;

        private final String strategyName;

        ChannelTypeStrategyEnum (ChannelTypeEnum channelTypeEnum, String strategyName) {
            this.channelTypeEnum = channelTypeEnum;
            this.strategyName= strategyName;
        }


        public String getStrategyName() {
            return strategyName;
        }

        public static ChannelTypeStrategyEnum getByChannelType(ChannelTypeEnum channelTypeEnum) {
            for (ChannelTypeStrategyEnum channelTypeStrategyEnum : values()) {
                if (channelTypeEnum == channelTypeStrategyEnum.channelTypeEnum) {
                    return channelTypeStrategyEnum ;
                }
            }
            return null;
        }
    }
}
