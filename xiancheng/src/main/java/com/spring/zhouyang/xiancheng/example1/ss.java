package com.spring.zhouyang.xiancheng.example1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ss:
 *
 * @author zhouYang
 * @date 2024/02/22
 */
public class ss {

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString1 = "[{\"skuArgsId\":\"1760920577499070465\",\"skuArgsName\":\"B\",\"skuArgsValue\":\"B!\"},{\"skuArgsId\":\"1760920509115138049\",\"skuArgsName\":\"A\",\"skuArgsValue\":\"A1\"},{\"skuArgsId\":\"1760918068072153090\",\"skuArgsName\":\"原油煤油2\",\"skuArgsValue\":\"1L\"}]";
        String jsonString2 = "[{\"skuArgsId\":\"1760918068072153090\",\"skuArgsName\":\"原油煤油2\",\"skuArgsValue\":\"1L\"},{\"skuArgsId\":\"1760920509115138049\",\"skuArgsName\":\"A\",\"skuArgsValue\":\"A1\"},{\"skuArgsId\":\"1760920577499070465\",\"skuArgsName\":\"B\",\"skuArgsValue\":\"B!\"}]";

        boolean areEqual = areJsonStringsEqual(jsonString1, jsonString2);
        if (areEqual) {
            System.out.println("两个字符串相等");
        } else {
            System.out.println("两个字符串不相等");
        }
    }

    private static boolean areJsonStringsEqual(String jsonString1, String jsonString2) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析 JSON 字符串为对象列表，并按照 skuArgsId 进行排序
        List<Object> list1 = Arrays.asList(objectMapper.readValue(jsonString1, Object[].class));
        List<Object> list2 = Arrays.asList(objectMapper.readValue(jsonString2, Object[].class));
        List<Object> sortedList1 = list1.stream()
                .sorted((o1, o2) -> ((String) ((List<Object>) o1).get(0)).compareTo((String) ((List<Object>) o2).get(0)))
                .collect(Collectors.toList());
        List<Object> sortedList2 = list2.stream()
                .sorted((o1, o2) -> ((String) ((List<Object>) o1).get(0)).compareTo((String) ((List<Object>) o2).get(0)))
                .collect(Collectors.toList());

        // 比较两个排序后的列表是否相等
        return sortedList1.equals(sortedList2);
    }
}
