package com.zhouyang.pojo;

/**
 * MappedStatement:
 *
 * @author zhouYang
 * @date 2023/05/19
 */
public class MappedStatement {

    //id
    private String id;
    //resultType
    private String resultType;
    //parameterType
    private String parameterType;
    //sql
    private String sql;

    public MappedStatement() {
    }

    public MappedStatement(String id, String resultType, String parameterType, String sql) {
        this.id = id;
        this.resultType = resultType;
        this.parameterType = parameterType;
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
