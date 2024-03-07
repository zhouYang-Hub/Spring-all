package com.spring.zhouyang.xiancheng.stram;

import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamApiTest:
 *
 * @author zhouYang
 * @date 2024/02/29
 */
public class StreamApiTest {

    public static void main(String[] args) {
        // 1. 创建一个集合
        List<StudentInfo> list = new ArrayList<>();
        list.add(new StudentInfo("张三", 18, true, 1.75, LocalDateTime.now()));
        list.add(new StudentInfo("李四", 19, true, 1.80, LocalDateTime.now()));
        list.add(new StudentInfo("王五", 20, true, 1.85, LocalDateTime.now()));
        list.add(new StudentInfo("赵六", 21, true, 1.90, LocalDateTime.now()));
        list.add(new StudentInfo("赵四", 21, true, 1.93, LocalDateTime.now()));
        list.add(new StudentInfo("田七w", 22, true, 1.95, LocalDateTime.now()));
        list.add(new StudentInfo("田五", 24, true, 1.92, LocalDateTime.now()));
        Stream<StudentInfo> stream = list.stream();

        // 2. 使用stream的方式 过滤满足条件的数据 filter
        List<StudentInfo> collectFilter = stream
                .filter(f -> 21 == f.getAge())
                .filter(f -> f.getHeight() > 1.90)
                .filter(f -> f.getGender())
                .collect(Collectors.toList());
        System.out.println("stream Filter " + collectFilter);


        // 3. 使用stream的方式 filter() , findAny() , orElse()
        Stream<StudentInfo> stream2 = list.stream();
        StudentInfo studentInfo = stream2.filter(f -> 21 == f.getAge())  // age =21
                .findAny() // 如果找到就返回，找不到就返回null
                .orElse(null);// 如果找到就返回，找不到就返回orElse()里面的值
        System.out.println("studentInfo stream findAny " + studentInfo);

        // 4. 使用stream的方式 map()
        Stream<StudentInfo> stream3 = list.stream();
        List<String> EntityMapToList = stream3.map(m -> m.getName()).collect(Collectors.toList());
        System.out.println("stream map to list<String> " + EntityMapToList);

        // 5. 使用stream的方式 distinct() 去重
        Stream<StudentInfo> stream4 = list.stream();
        List<Integer> ageList = stream4.map(StudentInfo::getAge).distinct().collect(Collectors.toList());
        System.out.println("stream ageList distinct " + JSONUtil.toJsonStr(ageList));

        List<String[]> strList = Arrays.asList("hello", "world").stream().map(s -> s.toLowerCase().split("")).distinct().collect(Collectors.toList());
        strList.forEach(col -> System.out.println(Arrays.toString(col)));


        List<String> strListFlatMap = Arrays.asList("hello", "world").stream()
                .map(s -> s.toLowerCase().split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList());
        System.out.println("strListFlatMap: " + strListFlatMap);


        //6 . sorted() 对元素进行排序 默认是asc
        Stream<StudentInfo> stream5 = list.stream();
        List<StudentInfo> collectSorted = stream5.sorted(Comparator.comparing(StudentInfo::getGender).thenComparing(StudentInfo::getHeight).reversed()).collect(Collectors.toList());
        System.out.println("collectSorted : " + JSONUtil.toJsonStr(collectSorted));

        //7:limit()：截取Stream的前n个元素。
        Stream<StudentInfo> stream6 = list.stream();
        List<StudentInfo> collectLimit = stream6.limit(1).collect(Collectors.toList());
        System.out.println("collectLimit : " + collectLimit);


        //8: skip() 提过stream 的前n个元素
        Stream<StudentInfo> stream7 = list.stream();

        List<StudentInfo> collectSkip = stream7.skip(5).collect(Collectors.toList());
        System.out.println("collectSkip : " + collectSkip);

        //9:count 统计数量
        long count = list.stream().count();
        System.out.println("count : " + count);

        // 10 匹配操作 anyMatch
        Stream<StudentInfo> stream10 = list.stream();
        List<String> nameList = stream10.map(StudentInfo::getName).collect(Collectors.toList());

        boolean b = nameList.stream().anyMatch(p -> p.length() > 2);
        if (b){
            System.out.println("b:存在");
        }else {
            System.out.println("b:不存在");
        }

        boolean all = nameList.stream().allMatch(p -> p.length() > 1);
        if (all){
            System.out.println("all:存在");
        }else {
            System.out.println("all:不存在");
        }


        //groupingBy()
        Map<Integer, List<StudentInfo>> collectMap = list.stream().collect(Collectors.groupingBy(StudentInfo::getAge));
        System.out.println("collectMap : " + JSONUtil.toJsonStr(collectMap));


        String[] idStr = {"1", "2", "3", "4", "5"};

        List<Long> collect = Arrays.stream(idStr).map(Long::parseLong).collect(Collectors.toList());
        System.out.println("collectIdStr " + collect);






    }
}
