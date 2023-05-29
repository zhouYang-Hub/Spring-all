package com.zhouyang.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource:
 *
 * @author zhouYang
 * @date 2023/05/19
 */
public class Resources {

    /**
     * 根据配置文件的全路径名，将配置文件的内容以输入流的形式加载到内存中
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream getResourceAsStream(String path) throws IOException {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }

}
