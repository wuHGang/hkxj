package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static cn.hkxj.platform.mapper.CourseDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface CourseMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, num, courseOrder, termYear, termOrder, teacherAccount, teacherName, examType, examTypeCode, academyName, academyCode, courseType, courseTypeCode, credit);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Course> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Course> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CourseResult")
    Optional<Course> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CourseResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_order", property="courseOrder", jdbcType=JdbcType.VARCHAR),
        @Result(column="term_year", property="termYear", jdbcType=JdbcType.VARCHAR),
        @Result(column="term_order", property="termOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="teacher_account", property="teacherAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_name", property="teacherName", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_type", property="examType", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_type_code", property="examTypeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="academy_name", property="academyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="academy_code", property="academyCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_type", property="courseType", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_type_code", property="courseTypeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit", property="credit", jdbcType=JdbcType.VARCHAR)
    })
    List<Course> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Course record) {
        return MyBatis3Utils.insert(this::insert, record, course, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(num).toProperty("num")
            .map(courseOrder).toProperty("courseOrder")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(teacherAccount).toProperty("teacherAccount")
            .map(teacherName).toProperty("teacherName")
            .map(examType).toProperty("examType")
            .map(examTypeCode).toProperty("examTypeCode")
            .map(academyName).toProperty("academyName")
            .map(academyCode).toProperty("academyCode")
            .map(courseType).toProperty("courseType")
            .map(courseTypeCode).toProperty("courseTypeCode")
            .map(credit).toProperty("credit")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Course> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, course, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(num).toProperty("num")
            .map(courseOrder).toProperty("courseOrder")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(teacherAccount).toProperty("teacherAccount")
            .map(teacherName).toProperty("teacherName")
            .map(examType).toProperty("examType")
            .map(examTypeCode).toProperty("examTypeCode")
            .map(academyName).toProperty("academyName")
            .map(academyCode).toProperty("academyCode")
            .map(courseType).toProperty("courseType")
            .map(courseTypeCode).toProperty("courseTypeCode")
            .map(credit).toProperty("credit")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Course record) {
        return MyBatis3Utils.insert(this::insert, record, course, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(num).toPropertyWhenPresent("num", record::getNum)
            .map(courseOrder).toPropertyWhenPresent("courseOrder", record::getCourseOrder)
            .map(termYear).toPropertyWhenPresent("termYear", record::getTermYear)
            .map(termOrder).toPropertyWhenPresent("termOrder", record::getTermOrder)
            .map(teacherAccount).toPropertyWhenPresent("teacherAccount", record::getTeacherAccount)
            .map(teacherName).toPropertyWhenPresent("teacherName", record::getTeacherName)
            .map(examType).toPropertyWhenPresent("examType", record::getExamType)
            .map(examTypeCode).toPropertyWhenPresent("examTypeCode", record::getExamTypeCode)
            .map(academyName).toPropertyWhenPresent("academyName", record::getAcademyName)
            .map(academyCode).toPropertyWhenPresent("academyCode", record::getAcademyCode)
            .map(courseType).toPropertyWhenPresent("courseType", record::getCourseType)
            .map(courseTypeCode).toPropertyWhenPresent("courseTypeCode", record::getCourseTypeCode)
            .map(credit).toPropertyWhenPresent("credit", record::getCredit)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Course> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Course> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Course> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Course> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, course, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Course record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(num).equalTo(record::getNum)
                .set(courseOrder).equalTo(record::getCourseOrder)
                .set(termYear).equalTo(record::getTermYear)
                .set(termOrder).equalTo(record::getTermOrder)
                .set(teacherAccount).equalTo(record::getTeacherAccount)
                .set(teacherName).equalTo(record::getTeacherName)
                .set(examType).equalTo(record::getExamType)
                .set(examTypeCode).equalTo(record::getExamTypeCode)
                .set(academyName).equalTo(record::getAcademyName)
                .set(academyCode).equalTo(record::getAcademyCode)
                .set(courseType).equalTo(record::getCourseType)
                .set(courseTypeCode).equalTo(record::getCourseTypeCode)
                .set(credit).equalTo(record::getCredit);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Course record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(num).equalToWhenPresent(record::getNum)
                .set(courseOrder).equalToWhenPresent(record::getCourseOrder)
                .set(termYear).equalToWhenPresent(record::getTermYear)
                .set(termOrder).equalToWhenPresent(record::getTermOrder)
                .set(teacherAccount).equalToWhenPresent(record::getTeacherAccount)
                .set(teacherName).equalToWhenPresent(record::getTeacherName)
                .set(examType).equalToWhenPresent(record::getExamType)
                .set(examTypeCode).equalToWhenPresent(record::getExamTypeCode)
                .set(academyName).equalToWhenPresent(record::getAcademyName)
                .set(academyCode).equalToWhenPresent(record::getAcademyCode)
                .set(courseType).equalToWhenPresent(record::getCourseType)
                .set(courseTypeCode).equalToWhenPresent(record::getCourseTypeCode)
                .set(credit).equalToWhenPresent(record::getCredit);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Course record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(num).equalTo(record::getNum)
            .set(courseOrder).equalTo(record::getCourseOrder)
            .set(termYear).equalTo(record::getTermYear)
            .set(termOrder).equalTo(record::getTermOrder)
            .set(teacherAccount).equalTo(record::getTeacherAccount)
            .set(teacherName).equalTo(record::getTeacherName)
            .set(examType).equalTo(record::getExamType)
            .set(examTypeCode).equalTo(record::getExamTypeCode)
            .set(academyName).equalTo(record::getAcademyName)
            .set(academyCode).equalTo(record::getAcademyCode)
            .set(courseType).equalTo(record::getCourseType)
            .set(courseTypeCode).equalTo(record::getCourseTypeCode)
            .set(credit).equalTo(record::getCredit)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Course record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(num).equalToWhenPresent(record::getNum)
            .set(courseOrder).equalToWhenPresent(record::getCourseOrder)
            .set(termYear).equalToWhenPresent(record::getTermYear)
            .set(termOrder).equalToWhenPresent(record::getTermOrder)
            .set(teacherAccount).equalToWhenPresent(record::getTeacherAccount)
            .set(teacherName).equalToWhenPresent(record::getTeacherName)
            .set(examType).equalToWhenPresent(record::getExamType)
            .set(examTypeCode).equalToWhenPresent(record::getExamTypeCode)
            .set(academyName).equalToWhenPresent(record::getAcademyName)
            .set(academyCode).equalToWhenPresent(record::getAcademyCode)
            .set(courseType).equalToWhenPresent(record::getCourseType)
            .set(courseTypeCode).equalToWhenPresent(record::getCourseTypeCode)
            .set(credit).equalToWhenPresent(record::getCredit)
            .where(id, isEqualTo(record::getId))
        );
    }
}