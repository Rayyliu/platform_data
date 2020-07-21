package com.platform.until;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ObjectListJsonTypeHandler <T> extends BaseTypeHandler<List<T>> {

    private  Class<T>     clazz;

    public ObjectListJsonTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getObject(columnName), clazz);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getObject(columnIndex), clazz);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getObject(columnIndex), clazz);
    }

    private List<T> toObject(Object content, Class<T> clazz) {
        if (content == null) {
            return null;
        }
        return JSON.parseArray(content.toString(), clazz);

    }

}
