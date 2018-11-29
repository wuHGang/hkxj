package cn.hkxj.platform.handler;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseType;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.utils.ApplicationUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CourseHandler implements TypeHandler<Course> {
    private CourseMapper courseMapper;

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Course course, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, course.getId());
    }

    @Override
    public Course getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return courseMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Course getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return courseMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Course getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            checkMapper();
            return courseMapper.selectByPrimaryKey(id);
        }
    }

    private void checkMapper(){
        if(Objects.isNull(courseMapper)){
            courseMapper = ApplicationUtil.getBean("courseMapper");
        }
    }
}
