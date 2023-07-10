package org.example.enums;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:26
 */
public enum CacheEnum {

    /**
     * 緩存名稱
     */
    CACHE_NAME("cache", 1000L);

    /**
     * 类型
     */
    private String name;

    /**
     * 描述
     */
    private Long expires;


    CacheEnum(String name, Long expires) {
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
