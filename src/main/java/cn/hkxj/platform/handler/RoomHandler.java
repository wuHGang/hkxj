package cn.hkxj.platform.handler;

import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.service.RoomService;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomHandler extends BaseTypeHandler<Room> {
    @Resource
    private RoomMapper roomMapper;

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Room room, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, room.getId());
    }

    @Override
    public Room getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return roomMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Room getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return roomMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Room getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return roomMapper.selectByPrimaryKey(id);
        }
    }
}
