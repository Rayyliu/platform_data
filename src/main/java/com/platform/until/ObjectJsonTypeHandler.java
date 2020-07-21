package com.platform.until;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectJsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {

    private  Class<T>     clazz;

    public ObjectJsonTypeHandler(Class<T> clazz){
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz=clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return this.toObject(resultSet.getObject(s), clazz);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return this.toObject(resultSet.getObject(i), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.toObject(callableStatement.getObject(i), clazz);
    }

    private T toObject(Object content, Class<T> clazz) {
        if (content == null) {
            return null;
        }
        return JSON.parseObject(content.toString(), clazz);

    }
}
