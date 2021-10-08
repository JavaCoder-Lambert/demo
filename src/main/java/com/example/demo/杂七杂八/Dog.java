package com.example.demo.杂七杂八;

import lombok.*;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月08日 15:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Dog extends Animal{
    private static final long serialVersionUID = -8090148567113742264L;

    @Builder(toBuilder = true)
    public Dog(String name, Integer countLegs) {
        super(name, countLegs);
        System.out.println("fgadasd");
    }
}
