package com.example.demo.杂七杂八;

import lombok.*;

/**
 * @Author: lzj
 * @Date: 2021/11/28 12:37
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Girl extends Person{
    /**
     * 备注信息
     */
    private String remark;

    public Girl(String firstName, String lastName, String jobName, String sex, Integer salary, Integer age, String remark) {
        super(firstName, lastName, jobName, sex, salary, age);
        this.remark = remark;
    }
}
