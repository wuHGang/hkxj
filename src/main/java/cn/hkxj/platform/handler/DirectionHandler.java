package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.constant.Direction;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrong.chen
 * @date 2018/10/28
 */
public class DirectionHandler extends BaseTypeHandler<Direction> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, Direction direction, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, direction.getCode());
	}

	@Override
	public Direction getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Direction.getDirectionByCode(resultSet.getInt(columnName));
		}
	}

	@Override
	public Direction getNullableResult(ResultSet resultSet, int index) throws SQLException {
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Direction.getDirectionByCode(resultSet.getInt(index));
		}
	}

	@Override
	public Direction getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
		if (callableStatement.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Direction.getDirectionByCode(callableStatement.getInt(index));
		}
	}
}
