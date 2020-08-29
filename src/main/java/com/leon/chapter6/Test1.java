package com.leon.chapter6;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Test1 {
    @Test
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("test/configuration-1.xml");
        assertThat(conf.get("color"), is("yellow"));
        assertThat(conf.getInt("size", 0), is(10));
        System.out.println(conf.get("color"));
    }
}
