package com.example.demo.设计模式;

/**
 * 策略模式
 * @Author: lzj
 * @Date: 2021/9/20 21:22
 * @Description:
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context c=new Context();
        Strategy s=new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s=new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }

    interface  Strategy{
        /**
         * 策略方法
         */
        public void strategyMethod();
    }

    //具体策略类A
    static class ConcreteStrategyA implements Strategy
    {
        @Override
        public void strategyMethod()
        {
            System.out.println("具体策略A的策略方法被访问！");
        }
    }

    //具体策略类B
    static class ConcreteStrategyB implements Strategy
    {
        @Override
        public void strategyMethod()
        {
            System.out.println("具体策略B的策略方法被访问！");
        }
    }

    static class Context{
        private Strategy strategy;

        public Strategy getStrategy() {
            return strategy;
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }
        public void strategyMethod()
        {
            strategy.strategyMethod();
        }
    }

}
