package com.example.demo.杂七杂八;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月08日 15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {

    private static final long serialVersionUID = 3059724578818863719L;

    public String name;

    public Integer countLegs;

    public int countLegs() {
        return countLegs;
    }
}
