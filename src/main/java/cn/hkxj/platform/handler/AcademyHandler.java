package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.Academy;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
public class AcademyHandler extends BaseTypeHandler<Academy> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Academy academy, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, academy.getAcademyCode());
    }

    @Override
    public Academy getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(resultSet.getInt(s));
        }
    }

    @Override
    public Academy getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(resultSet.getInt(i));
        }
    }

    @Override
    public Academy getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(callableStatement.getInt(i));
        }
    }
}
