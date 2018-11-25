package cn.hkxj.platform.handler;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(Course.class)
public class CourseHandler extends BaseTypeHandler<Course> {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Course course, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, course.getId());
    }

    @Override
    public Course getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return courseMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Course getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return courseMapper.selectByPrimaryKey(id);
        }
    }

    @Override
    public Course getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值
            return courseMapper.selectByPrimaryKey(id);
        }
    }
}
