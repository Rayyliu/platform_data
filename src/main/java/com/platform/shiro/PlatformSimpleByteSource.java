package com.platform.shiro;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class PlatformSimpleByteSource
//        extends SimpleByteSource
        implements ByteSource,  Serializable {


    private static final long serialVersionUID = 5528101080905698238L;

    private  byte[] bytes;
    private String cachedHex;
    private String cachedBase64;

    public PlatformSimpleByteSource(){
    }

    public PlatformSimpleByteSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public PlatformSimpleByteSource(char[] chars) {
        this.bytes = CodecSupport.toBytes(chars);
    }

    public PlatformSimpleByteSource(String string) {
        this.bytes = CodecSupport.toBytes(string);
    }

    public PlatformSimpleByteSource(ByteSource source) {
        this.bytes = source.getBytes();
    }

    public PlatformSimpleByteSource(File file) {
        this.bytes = (new PlatformSimpleByteSource.BytesHelper()).getBytes(file);
    }

    public PlatformSimpleByteSource(InputStream stream) {
        this.bytes = (new PlatformSimpleByteSource.BytesHelper()).getBytes(stream);
    }


//    public PlatformSimpleByteSource(byte[] bytes) {
//        super(bytes);
//    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public String toHex() {
        return null;
    }

    @Override
    public String toBase64() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private static final class BytesHelper extends CodecSupport {
        private BytesHelper() {
        }

        public byte[] getBytes(File file) {
            return this.toBytes(file);
        }

        public byte[] getBytes(InputStream stream) {
            return this.toBytes(stream);
        }
    }

//    public PlatformSimpleByteSource(char[] chars) {
//        super(chars);
//    }
//
//    public PlatformSimpleByteSource(String string) {
//        super(string);
//    }
//
//    public PlatformSimpleByteSource(ByteSource source) {
//        super(source);
//    }
//
//    public PlatformSimpleByteSource(File file) {
//        super(file);
//    }
//
//    public PlatformSimpleByteSource(InputStream stream) {
//        super(stream);
//    }
}
