package com.test.dev.base.enums;

import lombok.Getter;
import lombok.Setter;

public enum CodeConstants {

    SUCCESS("0", "成功"),
    FAIL("-1", "失败"),
    /**
     * 业务代码
     */
    MAIN_ROUTE_LOAN("100000050", "抱歉，您暂时不能借款"),
    MAIN_ROUTE_BUY("100000051", "抱歉，您暂时不能购买"),
    USER_NOT_EXIST("200000001", "用户不存在"),

    /**
     * 通用格式化错误
     */
    COMMON_10000001("100000001", "%s不能为空"),
    COMMON_10000002("100000002", "%s"),
    COMMON_10000003("100000003", "%s不能小于零"),
    COMMON_10000004("100000004", "%s枚举转化失败"),
    COMMON_10000005("100000005", "%s不存在"),
    COMMON_10000006("100000006", "%s不正确"),
    COMMON_10000007("100000007", "%s不能小于等于零"),
    COMMON_10000008("100000008", "%s已存在"),
    COMMON_10000009("100000009", "时间戳格式不对"),
    COMMON_10000010("100000010", "%s现在是%s状态，不能设置为%s状态"),
    COMMON_10000012("100000012", "[%s]长度必须在[%s]到[%s]之间"),
    COMMON_10000013("100000013", "%s不能大于%s"),
    COMMON_10000014("100000014", "文件类型不正确"),
    COMMON_10000015("100000015", "金额精度不符合要求"),
    COMMON_10000016("100000016", "[%s]必须在[%s]到[%s]之间"),
    COMMON_10000017("100000017", "[%s]尚不支持"),
    COMMON_10000018("100000018", "状态[%s]不正确，不能%s"),
    COMMON_10000019("100000019", "文件类型不正确"),
    COMMON_10000020("100000020", "不应到达的处理逻辑"),
    COMMON_10000021("100000021", "时间格式不正确"),
    COMMON_10000022("100000022", "[%s]数据格式不正确");

    @Getter
    private String key;
    @Getter
    @Setter
    private String desc;

    CodeConstants(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static String getDescByKey(String key) {
        for (CodeConstants c : CodeConstants.values()) {
            if (c.getKey().equals(key)) {
                return c.getDesc();
            }
        }
        return null;
    }

    public final CodeConstants format(String... params) {
        setDesc(String.format(getDesc(), params));
        return this;
    }

    @Override
    public String toString() {
        return String.format("[key=%s,desc=%s]", getKey(), getDesc());
    }

}