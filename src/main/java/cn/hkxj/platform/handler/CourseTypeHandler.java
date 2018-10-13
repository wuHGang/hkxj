package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.CourseType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrong.chen
 * @date 2018/9/16
 */
public class CourseTypeHandler extends BaseTypeHandler<CourseType> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, CourseType type, JdbcType jdbcType) throws SQLException {
		preparedStatement.setByte(i, type.getByte());
	}

	@Override
	public CourseType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		byte code = resultSet.getByte(columnName);
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return CourseType.getCourseByByte(code);
		}
	}

	@Override
	public CourseType getNullableResult(ResultSet resultSet, int index) throws SQLException {
		byte code = resultSet.getByte(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return CourseType.getCourseByByte(code);
		}
	}

	@Override
	public CourseType getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
		byte code = callableStatement.getByte(index);
		if (callableStatement.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return CourseType.getCourseByByte(code);
		}
	}
}
