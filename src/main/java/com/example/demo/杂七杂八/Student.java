package com.example.demo.杂七杂八;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月14日 15:04
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 3564286038105234358L;
    private Integer id;

    private String name;

    private Integer age;
}
