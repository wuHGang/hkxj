package cn.hkxj.platform.handler;

import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.utils.ApplicationUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/11/29
 */
public class RoomWarpperHandler implements TypeHandler<Room> {
    private RoomMapper roomMapper;

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Room room, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, room.getId());
    }

    @Override
    public Room getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return roomMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Room getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return roomMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Room getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return roomMapper.selectByPrimaryKey(id);
        }
    }

    private void checkMapper(){
        if(Objects.isNull(roomMapper)){
            roomMapper = ApplicationUtil.getBean("roomMapper");
        }
    }
}
