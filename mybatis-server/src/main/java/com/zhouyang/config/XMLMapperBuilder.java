package com.zhouyang.config;

import com.zhouyang.pojo.Configuration;
import com.zhouyang.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * XMLMapperBuilder:
 *
 * @author zhouYang
 * @date 2023/05/22
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configration) {
        this.configuration = configration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String nameSpace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        list.forEach(element -> {
            MappedStatement statement = new MappedStatement();
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            statement.setId(id);
            statement.setResultType(resultType);
            statement.setParameterType(paramterType);
            statement.setSql(sqlText);
            String mappedStatementId = nameSpace.concat(id);
            configuration.getMap().put(mappedStatementId, statement);
        });
    }
}
