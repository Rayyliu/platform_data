package com.platform.shiro;

import org.apache.shiro.util.ByteSource;

public class ByteSourceUtils {

    public static ByteSource bytes(byte[] bytes){
        return new PlatformSimpleByteSource(bytes);
    }
    public static ByteSource bytes(String arg0){
        return new PlatformSimpleByteSource(arg0.getBytes());
    }

}
