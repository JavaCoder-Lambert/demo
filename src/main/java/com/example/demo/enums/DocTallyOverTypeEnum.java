package com.example.demo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 超送类型
 * 1.超送无关联采购单  2.采购超送 3.有价格无采购 4.无采购价格     1 和2  和之前一致  只是增加3 和4  3 和4 是对1的补充
 *
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月08日 18:38
 */
@Getter
public enum DocTallyOverTypeEnum {

    /**
     * 1-超送无关联采购单  3(有价格无采购) 和4(无采购价格) 是对1的补充
     */
    TALLY_OVER_NO_RELATE(1, "超送无关联采购单"),

    /**
     * 2-采购超送
     */
    TALLY_OVER_HAS_RELATE(2, "采购超送"),
    /**
     * 3-有价格无采购
     */
    TALLY_OVER_HAS_PRICE(3, "有价格无采购"),
    /**
     * 4-无采购价格
     */
    TALLY_OVER_NO_PRICE(4, "无采购价格"),

    ;

    /**
     * 类型
     */
    private Integer code;
    /**
     * 描述
     */
     private String msg;

    DocTallyOverTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getEnumValueByCode(Integer code) {
        DocTallyOverTypeEnum value = Arrays.stream(DocTallyOverTypeEnum.values()).
                filter(item -> item.getCode().equals(code)).findFirst().orElse(null);
        return Objects.nonNull(value) ? value.getMsg() : null;
    }
}
