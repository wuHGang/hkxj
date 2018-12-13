package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.Academy;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
public class AcademyHandler implements TypeHandler<Academy> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Academy academy, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, academy.getAcademyCode());
    }

    @Override
    public Academy getResult(ResultSet resultSet, String columnName) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(resultSet.getInt(columnName));
        }
    }

    @Override
    public Academy getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(resultSet.getInt(columnIndex));
        }
    }

    @Override
    public Academy getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return Academy.getAcademyByCode(callableStatement.getInt(columnIndex));
        }
    }
}
