package cn.hkxj.platform.pojo.example;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamTimeTableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamTimeTableExample() {
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
        protected List<Criterion> roomCriteria;

        protected List<Criterion> courseCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            roomCriteria = new ArrayList<Criterion>();
            courseCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getRoomCriteria() {
            return roomCriteria;
        }

        protected void addRoomCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            roomCriteria.add(new Criterion(condition, value, "cn.hkxj.platform.handler.RoomHandler"));
            allCriteria = null;
        }

        protected void addRoomCriterion(String condition, Room value1, Room value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            roomCriteria.add(new Criterion(condition, value1, value2, "cn.hkxj.platform.handler.RoomHandler"));
            allCriteria = null;
        }

        public List<Criterion> getCourseCriteria() {
            return courseCriteria;
        }

        protected void addCourseCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            courseCriteria.add(new Criterion(condition, value, "cn.hkxj.platform.handler.CourseHandler"));
            allCriteria = null;
        }

        protected void addCourseCriterion(String condition, Course value1, Course value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            courseCriteria.add(new Criterion(condition, value1, value2, "cn.hkxj.platform.handler.CourseHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || roomCriteria.size() > 0
                || courseCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(roomCriteria);
                allCriteria.addAll(courseCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
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

        public Criteria andRoomIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomEqualTo(Room value) {
            addRoomCriterion("room_id =", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotEqualTo(Room value) {
            addRoomCriterion("room_id <>", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThan(Room value) {
            addRoomCriterion("room_id >", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThanOrEqualTo(Room value) {
            addRoomCriterion("room_id >=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThan(Room value) {
            addRoomCriterion("room_id <", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomLessThanOrEqualTo(Room value) {
            addRoomCriterion("room_id <=", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomIn(List<Room> values) {
            addRoomCriterion("room_id in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotIn(List<Room> values) {
            addRoomCriterion("room_id not in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomBetween(Room value1, Room value2) {
            addRoomCriterion("room_id between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotBetween(Room value1, Room value2) {
            addRoomCriterion("room_id not between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andCourseIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseEqualTo(Course value) {
            addCourseCriterion("course_id =", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseNotEqualTo(Course value) {
            addCourseCriterion("course_id <>", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseGreaterThan(Course value) {
            addCourseCriterion("course_id >", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseGreaterThanOrEqualTo(Course value) {
            addCourseCriterion("course_id >=", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseLessThan(Course value) {
            addCourseCriterion("course_id <", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseLessThanOrEqualTo(Course value) {
            addCourseCriterion("course_id <=", value, "course");
            return (Criteria) this;
        }

        public Criteria andCourseIn(List<Course> values) {
            addCourseCriterion("course_id in", values, "course");
            return (Criteria) this;
        }

        public Criteria andCourseNotIn(List<Course> values) {
            addCourseCriterion("course_id not in", values, "course");
            return (Criteria) this;
        }

        public Criteria andCourseBetween(Course value1, Course value2) {
            addCourseCriterion("course_id between", value1, value2, "course");
            return (Criteria) this;
        }

        public Criteria andCourseNotBetween(Course value1, Course value2) {
            addCourseCriterion("course_id not between", value1, value2, "course");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year_ is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year_ is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("year_ =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("year_ <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("year_ >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("year_ >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("year_ <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("year_ <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("year_ in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("year_ not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("year_ between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("year_ not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andTermIsNull() {
            addCriterion("term is null");
            return (Criteria) this;
        }

        public Criteria andTermIsNotNull() {
            addCriterion("term is not null");
            return (Criteria) this;
        }

        public Criteria andTermEqualTo(Integer value) {
            addCriterion("term =", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotEqualTo(Integer value) {
            addCriterion("term <>", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThan(Integer value) {
            addCriterion("term >", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanOrEqualTo(Integer value) {
            addCriterion("term >=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThan(Integer value) {
            addCriterion("term <", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThanOrEqualTo(Integer value) {
            addCriterion("term <=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermIn(List<Integer> values) {
            addCriterion("term in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotIn(List<Integer> values) {
            addCriterion("term not in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermBetween(Integer value1, Integer value2) {
            addCriterion("term between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotBetween(Integer value1, Integer value2) {
            addCriterion("term not between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekIsNull() {
            addCriterion("school_week is null");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekIsNotNull() {
            addCriterion("school_week is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekEqualTo(Integer value) {
            addCriterion("school_week =", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekNotEqualTo(Integer value) {
            addCriterion("school_week <>", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekGreaterThan(Integer value) {
            addCriterion("school_week >", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_week >=", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekLessThan(Integer value) {
            addCriterion("school_week <", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekLessThanOrEqualTo(Integer value) {
            addCriterion("school_week <=", value, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekIn(List<Integer> values) {
            addCriterion("school_week in", values, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekNotIn(List<Integer> values) {
            addCriterion("school_week not in", values, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekBetween(Integer value1, Integer value2) {
            addCriterion("school_week between", value1, value2, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andSchoolWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("school_week not between", value1, value2, "schoolWeek");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("week not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Date value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Date value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Date value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Date value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Date value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Date value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Date> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Date> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Date value1, Date value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Date value1, Date value2) {
            addCriterion("start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andEndIsNull() {
            addCriterion("end is null");
            return (Criteria) this;
        }

        public Criteria andEndIsNotNull() {
            addCriterion("end is not null");
            return (Criteria) this;
        }

        public Criteria andEndEqualTo(Date value) {
            addCriterion("end =", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotEqualTo(Date value) {
            addCriterion("end <>", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThan(Date value) {
            addCriterion("end >", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThanOrEqualTo(Date value) {
            addCriterion("end >=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThan(Date value) {
            addCriterion("end <", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThanOrEqualTo(Date value) {
            addCriterion("end <=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndIn(List<Date> values) {
            addCriterion("end in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotIn(List<Date> values) {
            addCriterion("end not in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndBetween(Date value1, Date value2) {
            addCriterion("end between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotBetween(Date value1, Date value2) {
            addCriterion("end not between", value1, value2, "end");
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