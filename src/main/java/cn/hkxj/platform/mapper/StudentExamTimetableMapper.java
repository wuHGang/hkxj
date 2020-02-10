package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.StudentExamTimetable;
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

import static cn.hkxj.platform.mapper.StudentExamTimetableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface StudentExamTimetableMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, account, examTimetableId, termYear, termOrder, gmtCreate, gmtModify);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<StudentExamTimetable> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<StudentExamTimetable> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StudentExamTimetableResult")
    Optional<StudentExamTimetable> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StudentExamTimetableResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_timetable_id", property="examTimetableId", jdbcType=JdbcType.INTEGER),
        @Result(column="term_year", property="termYear", jdbcType=JdbcType.VARCHAR),
        @Result(column="term_order", property="termOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modify", property="gmtModify", jdbcType=JdbcType.TIMESTAMP)
    })
    List<StudentExamTimetable> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(StudentExamTimetable record) {
        return MyBatis3Utils.insert(this::insert, record, studentExamTimetable, c ->
            c.map(id).toProperty("id")
            .map(account).toProperty("account")
            .map(examTimetableId).toProperty("examTimetableId")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModify).toProperty("gmtModify")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<StudentExamTimetable> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, studentExamTimetable, c ->
            c.map(id).toProperty("id")
            .map(account).toProperty("account")
            .map(examTimetableId).toProperty("examTimetableId")
            .map(termYear).toProperty("termYear")
            .map(termOrder).toProperty("termOrder")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModify).toProperty("gmtModify")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(StudentExamTimetable record) {
        return MyBatis3Utils.insert(this::insert, record, studentExamTimetable, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(examTimetableId).toPropertyWhenPresent("examTimetableId", record::getExamTimetableId)
            .map(termYear).toPropertyWhenPresent("termYear", record::getTermYear)
            .map(termOrder).toPropertyWhenPresent("termOrder", record::getTermOrder)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtModify).toPropertyWhenPresent("gmtModify", record::getGmtModify)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StudentExamTimetable> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StudentExamTimetable> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<StudentExamTimetable> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<StudentExamTimetable> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, studentExamTimetable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(StudentExamTimetable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(account).equalTo(record::getAccount)
                .set(examTimetableId).equalTo(record::getExamTimetableId)
                .set(termYear).equalTo(record::getTermYear)
                .set(termOrder).equalTo(record::getTermOrder)
                .set(gmtCreate).equalTo(record::getGmtCreate)
                .set(gmtModify).equalTo(record::getGmtModify);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(StudentExamTimetable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(account).equalToWhenPresent(record::getAccount)
                .set(examTimetableId).equalToWhenPresent(record::getExamTimetableId)
                .set(termYear).equalToWhenPresent(record::getTermYear)
                .set(termOrder).equalToWhenPresent(record::getTermOrder)
                .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
                .set(gmtModify).equalToWhenPresent(record::getGmtModify);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(StudentExamTimetable record) {
        return update(c ->
            c.set(account).equalTo(record::getAccount)
            .set(examTimetableId).equalTo(record::getExamTimetableId)
            .set(termYear).equalTo(record::getTermYear)
            .set(termOrder).equalTo(record::getTermOrder)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtModify).equalTo(record::getGmtModify)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(StudentExamTimetable record) {
        return update(c ->
            c.set(account).equalToWhenPresent(record::getAccount)
            .set(examTimetableId).equalToWhenPresent(record::getExamTimetableId)
            .set(termYear).equalToWhenPresent(record::getTermYear)
            .set(termOrder).equalToWhenPresent(record::getTermOrder)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtModify).equalToWhenPresent(record::getGmtModify)
            .where(id, isEqualTo(record::getId))
        );
    }
}