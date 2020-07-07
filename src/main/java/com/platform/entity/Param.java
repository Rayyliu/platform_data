package com.platform.entity;

public class Param {

    /** 参数名*/
    private String				name;
    /** 参数类型名称*/
    private String				typeName;
    /** 参数类型*/
    private String				type;
    /** 参数是基本类型*/
    private String				primitive;

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrimitive(String primitive) {
        this.primitive = primitive;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getType() {
        return type;
    }

    public String getPrimitive() {
        return primitive;
    }

}
