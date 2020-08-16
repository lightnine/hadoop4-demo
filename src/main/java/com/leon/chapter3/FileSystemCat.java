package com.leon.chapter3;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * ClassName FileSystemCat
 * Description: example 3-2
 * 本地可以直接访问远程伪分布式的hadoop集群
 * 1. 直接在代码中写上远端的hdfs地址
 * 2. 远端core-site.xml中注意端口号
 * 3. 本地添加library库
 * Create by leon
 * Date 2020/8/16 17:58
 */
public class FileSystemCat {
    public static void main(String[] args) throws IOException {
//        String uri = args[0];
        String uri = "hdfs://192.168.3.5:9000/user/root/test.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
