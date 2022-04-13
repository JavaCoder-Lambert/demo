package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年12月02日 20:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocQcUnusualSimpleReq implements Serializable {
    private static final long serialVersionUID = 3776213679940383750L;

    /**
     * 质检ID
     */
    private Long qualityId;
    /**
     * 审核备注
     */
    private String reviewRemark;

}
