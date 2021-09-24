package com.example.demo.杂七杂八;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lzj
 * @Date: 2021/9/22 21:58
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;

    private String lastName;

    private String jobName;

    private String sex;

    private Integer salary;

    private Integer age;
}
