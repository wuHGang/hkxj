package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ExamTimetable;
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

import static cn.hkxj.platform.mapper.ExamTimetableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface ExamTimetableMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, roomName, courseNum, courseOrder, termYear, termOrder, examDate, startTime, endTime, day, schoolWek, gmtCreate, gmtModify);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<ExamTimetable> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ExamTimetable> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ExamTimetableResult")
    Optional<ExamTimetable> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ExamTimetableResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="room_name", property="roomName", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_num", property="courseNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_order", property="courseOrder", jdbcType=JdbcType.VARCHAR),
        @Result(column="term_year", property="termYear", jdbcType=JdbcType.VARCHAR),
        @Result(column="term_order", property="termOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="exam_date", property="examDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="day", property="day", jdbcType=JdbcType.VARCHAR),
        @Result(column="school_wek", property="schoolWek", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modify", property="gmtModify", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ExamTimetable> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(ExamTimetable record) {
        return MyBatis3Utils.insert(this::insert, record, examTimetable, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(roomName).toProperty("roomName")
            .map(courseNum).toProperty("courseNum")
            .map(courseOrder).toProperty("courseOrder")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(examDate).toProperty("examDate")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(day).toProperty("day")
            .map(schoolWek).toProperty("schoolWek")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModify).toProperty("gmtModify")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<ExamTimetable> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, examTimetable, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(roomName).toProperty("roomName")
            .map(courseNum).toProperty("courseNum")
            .map(courseOrder).toProperty("courseOrder")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(examDate).toProperty("examDate")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(day).toProperty("day")
            .map(schoolWek).toProperty("schoolWek")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModify).toProperty("gmtModify")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(ExamTimetable record) {
        return MyBatis3Utils.insert(this::insert, record, examTimetable, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(roomName).toPropertyWhenPresent("roomName", record::getRoomName)
            .map(courseNum).toPropertyWhenPresent("courseNum", record::getCourseNum)
            .map(courseOrder).toPropertyWhenPresent("courseOrder", record::getCourseOrder)
            .map(termYear).toPropertyWhenPresent("termYear", record::getTermYear)
            .map(termOrder).toPropertyWhenPresent("termOrder", record::getTermOrder)
            .map(examDate).toPropertyWhenPresent("examDate", record::getExamDate)
            .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
            .map(endTime).toPropertyWhenPresent("endTime", record::getEndTime)
            .map(day).toPropertyWhenPresent("day", record::getDay)
            .map(schoolWek).toPropertyWhenPresent("schoolWek", record::getSchoolWek)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtModify).toPropertyWhenPresent("gmtModify", record::getGmtModify)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<ExamTimetable> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<ExamTimetable> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<ExamTimetable> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<ExamTimetable> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, examTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(ExamTimetable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(roomName).equalTo(record::getRoomName)
                .set(courseNum).equalTo(record::getCourseNum)
                .set(courseOrder).equalTo(record::getCourseOrder)
                .set(termYear).equalTo(record::getTermYear)
                .set(termOrder).equalTo(record::getTermOrder)
                .set(examDate).equalTo(record::getExamDate)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .set(day).equalTo(record::getDay)
                .set(schoolWek).equalTo(record::getSchoolWek)
                .set(gmtCreate).equalTo(record::getGmtCreate)
                .set(gmtModify).equalTo(record::getGmtModify);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ExamTimetable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(roomName).equalToWhenPresent(record::getRoomName)
                .set(courseNum).equalToWhenPresent(record::getCourseNum)
                .set(courseOrder).equalToWhenPresent(record::getCourseOrder)
                .set(termYear).equalToWhenPresent(record::getTermYear)
                .set(termOrder).equalToWhenPresent(record::getTermOrder)
                .set(examDate).equalToWhenPresent(record::getExamDate)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .set(day).equalToWhenPresent(record::getDay)
                .set(schoolWek).equalToWhenPresent(record::getSchoolWek)
                .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
                .set(gmtModify).equalToWhenPresent(record::getGmtModify);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(ExamTimetable record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(roomName).equalTo(record::getRoomName)
            .set(courseNum).equalTo(record::getCourseNum)
            .set(courseOrder).equalTo(record::getCourseOrder)
            .set(termYear).equalTo(record::getTermYear)
            .set(termOrder).equalTo(record::getTermOrder)
            .set(examDate).equalTo(record::getExamDate)
            .set(startTime).equalTo(record::getStartTime)
            .set(endTime).equalTo(record::getEndTime)
            .set(day).equalTo(record::getDay)
            .set(schoolWek).equalTo(record::getSchoolWek)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtModify).equalTo(record::getGmtModify)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(ExamTimetable record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(roomName).equalToWhenPresent(record::getRoomName)
            .set(courseNum).equalToWhenPresent(record::getCourseNum)
            .set(courseOrder).equalToWhenPresent(record::getCourseOrder)
            .set(termYear).equalToWhenPresent(record::getTermYear)
            .set(termOrder).equalToWhenPresent(record::getTermOrder)
            .set(examDate).equalToWhenPresent(record::getExamDate)
            .set(startTime).equalToWhenPresent(record::getStartTime)
            .set(endTime).equalToWhenPresent(record::getEndTime)
            .set(day).equalToWhenPresent(record::getDay)
            .set(schoolWek).equalToWhenPresent(record::getSchoolWek)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtModify).equalToWhenPresent(record::getGmtModify)
            .where(id, isEqualTo(record::getId))
        );
    }
}