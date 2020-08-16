package com.leon.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * ClassName FileSystemDoubleCat
 * Description:采用FileSystem读取hadoop文件
 * seek函数, 读取两次
 * Create by leon
 * Date 2020/8/16 18:18
 */
public class FileSystemDoubleCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        // 设置配置文件内容,默认构造函数会读取core-site.xml以及hdfs-site.xml文件内容
        Configuration conf = new Configuration();
        // 根据配置文件获取指定路径文件
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            // 重新设置到文件开头
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
