package com.zhouyang.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Configruation:
 *
 * @author zhouYang
 * @date 2023/05/19
 */
public class Configuration {

    private DataSource dataSource;

    // map key  mappedStatementId
    private Map<String, MappedStatement> map = new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMap() {
        return map;
    }

    public void setMap(Map<String, MappedStatement> map) {
        this.map = map;
    }
}
