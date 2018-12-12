package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.CourseType;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrong.chen
 * @date 2018/9/16
 */
public class CourseTypeHandler extends EnumTypeHandler<CourseType> {


    public CourseTypeHandler(Class<CourseType> type) {
        super(type);
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, CourseType courseType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, courseType.getByte());
    }

    @Override
    public CourseType getResult(ResultSet resultSet, String columnName) throws SQLException {
        int code = resultSet.getInt(columnName);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return CourseType.getCourseByByte(code);
        }
    }

    @Override
    public CourseType getResult(ResultSet resultSet, int index) throws SQLException {
        int code = resultSet.getInt(index);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return CourseType.getCourseByByte(code);
        }
    }

    @Override
    public CourseType getResult(CallableStatement callableStatement, int index) throws SQLException {
        int code = callableStatement.getInt(index);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return CourseType.getCourseByByte(code);
        }
    }
}
