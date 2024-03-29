package com.zy.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/28 11:20
 */
public class ZhouYangClassPathXmlApplicationContext {

    private Class configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ZhouYangClassPathXmlApplicationContext(Class configClass) {
        this.configClass = configClass;
        scan(configClass);
        //找出所有的单例bean，
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            BeanDefinition beanDefinition = beanDefinitionEntry.getValue();
            if ("singleton".equals(beanDefinition.getScope())) {
                Object singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
        }
    }

    //创建单例bean
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(Boolean.TRUE);
                    field.set(instance, getBean(field.getName()));
                }
            }
            
            //初始化前置处理器
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            if (instance instanceof InitializingBean) {
                //初始化bean
                ((InitializingBean) instance).afterPropertiesSet();
            }

            //初始化后置处理器
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 获取bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        //判断当前这个beanName 是否存在
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException("不存在这个bean");
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if ("singleton".equals(beanDefinition.getScope())) {
            //单例bean
            Object singletonBean = singletonObjects.get(beanName);
            if (null == singletonBean) {
                singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            //原型bean
            return createBean(beanName, beanDefinition);
        }
    }


    //扫描bean的过程
    private void scan(Class configClass) {
        if (configClass.isAnnotationPresent(SpringComponentScan.class)) {
            // 1.解析配置类
            SpringComponentScan springComponentScan = (SpringComponentScan) configClass.getAnnotation(SpringComponentScan.class);
            String path = springComponentScan.value().replace(".", "/");
            // 2.扫描包是否有注解 需要去加载target下的class文件
            ClassLoader classLoader = ZhouYangClassPathXmlApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                for (File listFile : file.listFiles()) {
                    String absolutePath = listFile.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");
                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        //判断是否有注解 SpringComponent
                        if (clazz.isAnnotationPresent(SpringComponent.class)) {
                            // 如果这个类加了 Component 注解， 判断这个类是否实现了 BeanPostProcessor 接口
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessorList.add(instance);
                            } else {
                                String beanName = clazz.getAnnotation(SpringComponent.class).value();
                                if (beanName == null || "".equals(beanName)) {
                                    //spring默认根据类的名字生成一个beanName;
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }
                                //创建 bean
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);
                                //判断他是原型bean 还是单利bean 通过scope 注解判断
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    String value = clazz.getAnnotation(Scope.class).value();
                                    beanDefinition.setScope(value);
                                } else {
                                    //单例
                                    beanDefinition.setScope("singleton");
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
