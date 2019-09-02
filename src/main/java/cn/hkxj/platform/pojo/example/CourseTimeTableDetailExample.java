package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseTimeTableDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseTimeTableDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNull() {
            addCriterion("room_name is null");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNotNull() {
            addCriterion("room_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNameEqualTo(String value) {
            addCriterion("room_name =", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotEqualTo(String value) {
            addCriterion("room_name <>", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThan(String value) {
            addCriterion("room_name >", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("room_name >=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThan(String value) {
            addCriterion("room_name <", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThanOrEqualTo(String value) {
            addCriterion("room_name <=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLike(String value) {
            addCriterion("room_name like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotLike(String value) {
            addCriterion("room_name not like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameIn(List<String> values) {
            addCriterion("room_name in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotIn(List<String> values) {
            addCriterion("room_name not in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameBetween(String value1, String value2) {
            addCriterion("room_name between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotBetween(String value1, String value2) {
            addCriterion("room_name not between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdIsNull() {
            addCriterion("course_time_table_basic_info_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdIsNotNull() {
            addCriterion("course_time_table_basic_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdEqualTo(Integer value) {
            addCriterion("course_time_table_basic_info_id =", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdNotEqualTo(Integer value) {
            addCriterion("course_time_table_basic_info_id <>", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdGreaterThan(Integer value) {
            addCriterion("course_time_table_basic_info_id >", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_time_table_basic_info_id >=", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdLessThan(Integer value) {
            addCriterion("course_time_table_basic_info_id <", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_time_table_basic_info_id <=", value, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdIn(List<Integer> values) {
            addCriterion("course_time_table_basic_info_id in", values, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdNotIn(List<Integer> values) {
            addCriterion("course_time_table_basic_info_id not in", values, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("course_time_table_basic_info_id between", value1, value2, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCourseTimeTableBasicInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_time_table_basic_info_id not between", value1, value2, "courseTimeTableBasicInfoId");
            return (Criteria) this;
        }

        public Criteria andCampusNameIsNull() {
            addCriterion("campus_name is null");
            return (Criteria) this;
        }

        public Criteria andCampusNameIsNotNull() {
            addCriterion("campus_name is not null");
            return (Criteria) this;
        }

        public Criteria andCampusNameEqualTo(String value) {
            addCriterion("campus_name =", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotEqualTo(String value) {
            addCriterion("campus_name <>", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThan(String value) {
            addCriterion("campus_name >", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThanOrEqualTo(String value) {
            addCriterion("campus_name >=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThan(String value) {
            addCriterion("campus_name <", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThanOrEqualTo(String value) {
            addCriterion("campus_name <=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLike(String value) {
            addCriterion("campus_name like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotLike(String value) {
            addCriterion("campus_name not like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameIn(List<String> values) {
            addCriterion("campus_name in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotIn(List<String> values) {
            addCriterion("campus_name not in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameBetween(String value1, String value2) {
            addCriterion("campus_name between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotBetween(String value1, String value2) {
            addCriterion("campus_name not between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionIsNull() {
            addCriterion("continuing_session is null");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionIsNotNull() {
            addCriterion("continuing_session is not null");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionEqualTo(Integer value) {
            addCriterion("continuing_session =", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionNotEqualTo(Integer value) {
            addCriterion("continuing_session <>", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionGreaterThan(Integer value) {
            addCriterion("continuing_session >", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionGreaterThanOrEqualTo(Integer value) {
            addCriterion("continuing_session >=", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionLessThan(Integer value) {
            addCriterion("continuing_session <", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionLessThanOrEqualTo(Integer value) {
            addCriterion("continuing_session <=", value, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionIn(List<Integer> values) {
            addCriterion("continuing_session in", values, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionNotIn(List<Integer> values) {
            addCriterion("continuing_session not in", values, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionBetween(Integer value1, Integer value2) {
            addCriterion("continuing_session between", value1, value2, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andContinuingSessionNotBetween(Integer value1, Integer value2) {
            addCriterion("continuing_session not between", value1, value2, "continuingSession");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("course_id like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("course_id not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherIsNull() {
            addCriterion("attend_class_teacher is null");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherIsNotNull() {
            addCriterion("attend_class_teacher is not null");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherEqualTo(String value) {
            addCriterion("attend_class_teacher =", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherNotEqualTo(String value) {
            addCriterion("attend_class_teacher <>", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherGreaterThan(String value) {
            addCriterion("attend_class_teacher >", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("attend_class_teacher >=", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherLessThan(String value) {
            addCriterion("attend_class_teacher <", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherLessThanOrEqualTo(String value) {
            addCriterion("attend_class_teacher <=", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherLike(String value) {
            addCriterion("attend_class_teacher like", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherNotLike(String value) {
            addCriterion("attend_class_teacher not like", value, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherIn(List<String> values) {
            addCriterion("attend_class_teacher in", values, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherNotIn(List<String> values) {
            addCriterion("attend_class_teacher not in", values, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherBetween(String value1, String value2) {
            addCriterion("attend_class_teacher between", value1, value2, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andAttendClassTeacherNotBetween(String value1, String value2) {
            addCriterion("attend_class_teacher not between", value1, value2, "attendClassTeacher");
            return (Criteria) this;
        }

        public Criteria andDayIsNull() {
            addCriterion("day_ is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("day_ is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(Integer value) {
            addCriterion("day_ =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(Integer value) {
            addCriterion("day_ <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(Integer value) {
            addCriterion("day_ >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_ >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(Integer value) {
            addCriterion("day_ <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(Integer value) {
            addCriterion("day_ <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<Integer> values) {
            addCriterion("day_ in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<Integer> values) {
            addCriterion("day_ not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(Integer value1, Integer value2) {
            addCriterion("day_ between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(Integer value1, Integer value2) {
            addCriterion("day_ not between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("order_ is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("order_ is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("order_ =", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("order_ <>", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("order_ >", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_ >=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("order_ <", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("order_ <=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("order_ in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("order_ not in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("order_ between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("order_ not between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andStartWeekIsNull() {
            addCriterion("start_week is null");
            return (Criteria) this;
        }

        public Criteria andStartWeekIsNotNull() {
            addCriterion("start_week is not null");
            return (Criteria) this;
        }

        public Criteria andStartWeekEqualTo(Integer value) {
            addCriterion("start_week =", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekNotEqualTo(Integer value) {
            addCriterion("start_week <>", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekGreaterThan(Integer value) {
            addCriterion("start_week >", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_week >=", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekLessThan(Integer value) {
            addCriterion("start_week <", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekLessThanOrEqualTo(Integer value) {
            addCriterion("start_week <=", value, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekIn(List<Integer> values) {
            addCriterion("start_week in", values, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekNotIn(List<Integer> values) {
            addCriterion("start_week not in", values, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekBetween(Integer value1, Integer value2) {
            addCriterion("start_week between", value1, value2, "startWeek");
            return (Criteria) this;
        }

        public Criteria andStartWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("start_week not between", value1, value2, "startWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekIsNull() {
            addCriterion("end_week is null");
            return (Criteria) this;
        }

        public Criteria andEndWeekIsNotNull() {
            addCriterion("end_week is not null");
            return (Criteria) this;
        }

        public Criteria andEndWeekEqualTo(Integer value) {
            addCriterion("end_week =", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekNotEqualTo(Integer value) {
            addCriterion("end_week <>", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekGreaterThan(Integer value) {
            addCriterion("end_week >", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_week >=", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekLessThan(Integer value) {
            addCriterion("end_week <", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekLessThanOrEqualTo(Integer value) {
            addCriterion("end_week <=", value, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekIn(List<Integer> values) {
            addCriterion("end_week in", values, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekNotIn(List<Integer> values) {
            addCriterion("end_week not in", values, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekBetween(Integer value1, Integer value2) {
            addCriterion("end_week between", value1, value2, "endWeek");
            return (Criteria) this;
        }

        public Criteria andEndWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("end_week not between", value1, value2, "endWeek");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week_ is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week_ is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(String value) {
            addCriterion("week_ =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(String value) {
            addCriterion("week_ <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(String value) {
            addCriterion("week_ >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(String value) {
            addCriterion("week_ >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(String value) {
            addCriterion("week_ <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(String value) {
            addCriterion("week_ <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLike(String value) {
            addCriterion("week_ like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotLike(String value) {
            addCriterion("week_ not like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<String> values) {
            addCriterion("week_ in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<String> values) {
            addCriterion("week_ not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(String value1, String value2) {
            addCriterion("week_ between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(String value1, String value2) {
            addCriterion("week_ not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberIsNull() {
            addCriterion("course_sequence_number is null");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberIsNotNull() {
            addCriterion("course_sequence_number is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberEqualTo(String value) {
            addCriterion("course_sequence_number =", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberNotEqualTo(String value) {
            addCriterion("course_sequence_number <>", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberGreaterThan(String value) {
            addCriterion("course_sequence_number >", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("course_sequence_number >=", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberLessThan(String value) {
            addCriterion("course_sequence_number <", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberLessThanOrEqualTo(String value) {
            addCriterion("course_sequence_number <=", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberLike(String value) {
            addCriterion("course_sequence_number like", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberNotLike(String value) {
            addCriterion("course_sequence_number not like", value, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberIn(List<String> values) {
            addCriterion("course_sequence_number in", values, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberNotIn(List<String> values) {
            addCriterion("course_sequence_number not in", values, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberBetween(String value1, String value2) {
            addCriterion("course_sequence_number between", value1, value2, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andCourseSequenceNumberNotBetween(String value1, String value2) {
            addCriterion("course_sequence_number not between", value1, value2, "courseSequenceNumber");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionIsNull() {
            addCriterion("week_description is null");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionIsNotNull() {
            addCriterion("week_description is not null");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionEqualTo(String value) {
            addCriterion("week_description =", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionNotEqualTo(String value) {
            addCriterion("week_description <>", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionGreaterThan(String value) {
            addCriterion("week_description >", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("week_description >=", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionLessThan(String value) {
            addCriterion("week_description <", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionLessThanOrEqualTo(String value) {
            addCriterion("week_description <=", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionLike(String value) {
            addCriterion("week_description like", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionNotLike(String value) {
            addCriterion("week_description not like", value, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionIn(List<String> values) {
            addCriterion("week_description in", values, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionNotIn(List<String> values) {
            addCriterion("week_description not in", values, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionBetween(String value1, String value2) {
            addCriterion("week_description between", value1, value2, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andWeekDescriptionNotBetween(String value1, String value2) {
            addCriterion("week_description not between", value1, value2, "weekDescription");
            return (Criteria) this;
        }

        public Criteria andSksjIsNull() {
            addCriterion("sksj is null");
            return (Criteria) this;
        }

        public Criteria andSksjIsNotNull() {
            addCriterion("sksj is not null");
            return (Criteria) this;
        }

        public Criteria andSksjEqualTo(String value) {
            addCriterion("sksj =", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjNotEqualTo(String value) {
            addCriterion("sksj <>", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjGreaterThan(String value) {
            addCriterion("sksj >", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjGreaterThanOrEqualTo(String value) {
            addCriterion("sksj >=", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjLessThan(String value) {
            addCriterion("sksj <", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjLessThanOrEqualTo(String value) {
            addCriterion("sksj <=", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjLike(String value) {
            addCriterion("sksj like", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjNotLike(String value) {
            addCriterion("sksj not like", value, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjIn(List<String> values) {
            addCriterion("sksj in", values, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjNotIn(List<String> values) {
            addCriterion("sksj not in", values, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjBetween(String value1, String value2) {
            addCriterion("sksj between", value1, value2, "sksj");
            return (Criteria) this;
        }

        public Criteria andSksjNotBetween(String value1, String value2) {
            addCriterion("sksj not between", value1, value2, "sksj");
            return (Criteria) this;
        }

        public Criteria andDistinctIsNull() {
            addCriterion("distinct_ is null");
            return (Criteria) this;
        }

        public Criteria andDistinctIsNotNull() {
            addCriterion("distinct_ is not null");
            return (Criteria) this;
        }

        public Criteria andDistinctEqualTo(Byte value) {
            addCriterion("distinct_ =", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctNotEqualTo(Byte value) {
            addCriterion("distinct_ <>", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctGreaterThan(Byte value) {
            addCriterion("distinct_ >", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctGreaterThanOrEqualTo(Byte value) {
            addCriterion("distinct_ >=", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctLessThan(Byte value) {
            addCriterion("distinct_ <", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctLessThanOrEqualTo(Byte value) {
            addCriterion("distinct_ <=", value, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctIn(List<Byte> values) {
            addCriterion("distinct_ in", values, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctNotIn(List<Byte> values) {
            addCriterion("distinct_ not in", values, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctBetween(Byte value1, Byte value2) {
            addCriterion("distinct_ between", value1, value2, "distinct");
            return (Criteria) this;
        }

        public Criteria andDistinctNotBetween(Byte value1, Byte value2) {
            addCriterion("distinct_ not between", value1, value2, "distinct");
            return (Criteria) this;
        }

        public Criteria andTermYearIsNull() {
            addCriterion("term_year is null");
            return (Criteria) this;
        }

        public Criteria andTermYearIsNotNull() {
            addCriterion("term_year is not null");
            return (Criteria) this;
        }

        public Criteria andTermYearEqualTo(String value) {
            addCriterion("term_year =", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearNotEqualTo(String value) {
            addCriterion("term_year <>", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearGreaterThan(String value) {
            addCriterion("term_year >", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearGreaterThanOrEqualTo(String value) {
            addCriterion("term_year >=", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearLessThan(String value) {
            addCriterion("term_year <", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearLessThanOrEqualTo(String value) {
            addCriterion("term_year <=", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearLike(String value) {
            addCriterion("term_year like", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearNotLike(String value) {
            addCriterion("term_year not like", value, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearIn(List<String> values) {
            addCriterion("term_year in", values, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearNotIn(List<String> values) {
            addCriterion("term_year not in", values, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearBetween(String value1, String value2) {
            addCriterion("term_year between", value1, value2, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermYearNotBetween(String value1, String value2) {
            addCriterion("term_year not between", value1, value2, "termYear");
            return (Criteria) this;
        }

        public Criteria andTermOrderIsNull() {
            addCriterion("term_order is null");
            return (Criteria) this;
        }

        public Criteria andTermOrderIsNotNull() {
            addCriterion("term_order is not null");
            return (Criteria) this;
        }

        public Criteria andTermOrderEqualTo(Integer value) {
            addCriterion("term_order =", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderNotEqualTo(Integer value) {
            addCriterion("term_order <>", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderGreaterThan(Integer value) {
            addCriterion("term_order >", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("term_order >=", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderLessThan(Integer value) {
            addCriterion("term_order <", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderLessThanOrEqualTo(Integer value) {
            addCriterion("term_order <=", value, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderIn(List<Integer> values) {
            addCriterion("term_order in", values, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderNotIn(List<Integer> values) {
            addCriterion("term_order not in", values, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderBetween(Integer value1, Integer value2) {
            addCriterion("term_order between", value1, value2, "termOrder");
            return (Criteria) this;
        }

        public Criteria andTermOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("term_order not between", value1, value2, "termOrder");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}