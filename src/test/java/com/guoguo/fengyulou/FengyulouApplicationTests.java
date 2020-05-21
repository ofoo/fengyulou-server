package com.guoguo.fengyulou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FengyulouApplicationTests {

    @Test
    public void contextLoads() throws UnsupportedEncodingException {
        String gbk= URLEncoder.encode("杨文黎","GBK");
        String str = new String("杨文黎".getBytes(),"UTF-8");
        System.out.println(gbk);
        System.out.println(str);
    }

    public static String toGBK(String source) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = source.getBytes("GBK");
        for (byte b : bytes) {
            sb.append("%" + Integer.toHexString((b & 0xff)).toUpperCase());
        }

        return sb.toString();
    }

}
