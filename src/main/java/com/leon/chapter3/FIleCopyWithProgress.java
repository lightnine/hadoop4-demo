package com.leon.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;
import java.nio.file.FileStore;

/**
 * ClassName FIleCopyWithProgress
 * Description: copy a local file to a hadoop filesystem
 * Create by leon
 * Date 2020/8/16 18:37
 */
public class FIleCopyWithProgress {
    public static void main(String[] args) throws IOException {
//        String localSrc = args[0];
//        String dst = args[1];
        String localSrc = "E:\\data\\sakila-db\\sakila-schema.sql";
        String dst = "hdfs://192.168.3.5:9000/user/root/sakila-schema.sql";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "hdfs://192.168.3.5:8082");
//        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
