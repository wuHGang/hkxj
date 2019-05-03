package cn.hkxj.platform.handler;

import cn.hkxj.platform.pojo.constant.Building;
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
public class BuildingHandler extends BaseTypeHandler<Building> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, Building building, JdbcType jdbcType) throws SQLException {
		preparedStatement.setString(i, building.getChinese());
	}

	@Override
	public Building getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Building.getBuildingByName(resultSet.getString(columnName));
		}
	}

	@Override
	public Building getNullableResult(ResultSet resultSet, int index) throws SQLException {
		if (resultSet.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Building.getBuildingByName(resultSet.getString(index));
		}
	}

	@Override
	public Building getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
		if (callableStatement.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值
			return Building.getBuildingByName(callableStatement.getString(index));
		}
	}
}
