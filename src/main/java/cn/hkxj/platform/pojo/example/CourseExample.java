package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIsNull() {
            addCriterion("course_order is null");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIsNotNull() {
            addCriterion("course_order is not null");
            return (Criteria) this;
        }

        public Criteria andCourseOrderEqualTo(String value) {
            addCriterion("course_order =", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotEqualTo(String value) {
            addCriterion("course_order <>", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderGreaterThan(String value) {
            addCriterion("course_order >", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderGreaterThanOrEqualTo(String value) {
            addCriterion("course_order >=", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderLessThan(String value) {
            addCriterion("course_order <", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderLessThanOrEqualTo(String value) {
            addCriterion("course_order <=", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderLike(String value) {
            addCriterion("course_order like", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotLike(String value) {
            addCriterion("course_order not like", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIn(List<String> values) {
            addCriterion("course_order in", values, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotIn(List<String> values) {
            addCriterion("course_order not in", values, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderBetween(String value1, String value2) {
            addCriterion("course_order between", value1, value2, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotBetween(String value1, String value2) {
            addCriterion("course_order not between", value1, value2, "courseOrder");
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

        public Criteria andTeacherAccountIsNull() {
            addCriterion("teacher_account is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountIsNotNull() {
            addCriterion("teacher_account is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountEqualTo(String value) {
            addCriterion("teacher_account =", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountNotEqualTo(String value) {
            addCriterion("teacher_account <>", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountGreaterThan(String value) {
            addCriterion("teacher_account >", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_account >=", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountLessThan(String value) {
            addCriterion("teacher_account <", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountLessThanOrEqualTo(String value) {
            addCriterion("teacher_account <=", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountLike(String value) {
            addCriterion("teacher_account like", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountNotLike(String value) {
            addCriterion("teacher_account not like", value, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountIn(List<String> values) {
            addCriterion("teacher_account in", values, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountNotIn(List<String> values) {
            addCriterion("teacher_account not in", values, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountBetween(String value1, String value2) {
            addCriterion("teacher_account between", value1, value2, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherAccountNotBetween(String value1, String value2) {
            addCriterion("teacher_account not between", value1, value2, "teacherAccount");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("teacher_name is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("teacher_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("teacher_name =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("teacher_name <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("teacher_name >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_name >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("teacher_name <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("teacher_name <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("teacher_name like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("teacher_name not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("teacher_name in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("teacher_name not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("teacher_name between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("teacher_name not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNull() {
            addCriterion("exam_type is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNotNull() {
            addCriterion("exam_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeEqualTo(String value) {
            addCriterion("exam_type =", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotEqualTo(String value) {
            addCriterion("exam_type <>", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThan(String value) {
            addCriterion("exam_type >", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("exam_type >=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThan(String value) {
            addCriterion("exam_type <", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThanOrEqualTo(String value) {
            addCriterion("exam_type <=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLike(String value) {
            addCriterion("exam_type like", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotLike(String value) {
            addCriterion("exam_type not like", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeIn(List<String> values) {
            addCriterion("exam_type in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotIn(List<String> values) {
            addCriterion("exam_type not in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeBetween(String value1, String value2) {
            addCriterion("exam_type between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotBetween(String value1, String value2) {
            addCriterion("exam_type not between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeIsNull() {
            addCriterion("exam_type_code is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeIsNotNull() {
            addCriterion("exam_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeEqualTo(String value) {
            addCriterion("exam_type_code =", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeNotEqualTo(String value) {
            addCriterion("exam_type_code <>", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeGreaterThan(String value) {
            addCriterion("exam_type_code >", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("exam_type_code >=", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeLessThan(String value) {
            addCriterion("exam_type_code <", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("exam_type_code <=", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeLike(String value) {
            addCriterion("exam_type_code like", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeNotLike(String value) {
            addCriterion("exam_type_code not like", value, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeIn(List<String> values) {
            addCriterion("exam_type_code in", values, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeNotIn(List<String> values) {
            addCriterion("exam_type_code not in", values, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeBetween(String value1, String value2) {
            addCriterion("exam_type_code between", value1, value2, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andExamTypeCodeNotBetween(String value1, String value2) {
            addCriterion("exam_type_code not between", value1, value2, "examTypeCode");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIsNull() {
            addCriterion("academy_name is null");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIsNotNull() {
            addCriterion("academy_name is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyNameEqualTo(String value) {
            addCriterion("academy_name =", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotEqualTo(String value) {
            addCriterion("academy_name <>", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameGreaterThan(String value) {
            addCriterion("academy_name >", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameGreaterThanOrEqualTo(String value) {
            addCriterion("academy_name >=", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLessThan(String value) {
            addCriterion("academy_name <", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLessThanOrEqualTo(String value) {
            addCriterion("academy_name <=", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLike(String value) {
            addCriterion("academy_name like", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotLike(String value) {
            addCriterion("academy_name not like", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIn(List<String> values) {
            addCriterion("academy_name in", values, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotIn(List<String> values) {
            addCriterion("academy_name not in", values, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameBetween(String value1, String value2) {
            addCriterion("academy_name between", value1, value2, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotBetween(String value1, String value2) {
            addCriterion("academy_name not between", value1, value2, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeIsNull() {
            addCriterion("academy_code is null");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeIsNotNull() {
            addCriterion("academy_code is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeEqualTo(String value) {
            addCriterion("academy_code =", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeNotEqualTo(String value) {
            addCriterion("academy_code <>", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeGreaterThan(String value) {
            addCriterion("academy_code >", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("academy_code >=", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeLessThan(String value) {
            addCriterion("academy_code <", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeLessThanOrEqualTo(String value) {
            addCriterion("academy_code <=", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeLike(String value) {
            addCriterion("academy_code like", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeNotLike(String value) {
            addCriterion("academy_code not like", value, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeIn(List<String> values) {
            addCriterion("academy_code in", values, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeNotIn(List<String> values) {
            addCriterion("academy_code not in", values, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeBetween(String value1, String value2) {
            addCriterion("academy_code between", value1, value2, "academyCode");
            return (Criteria) this;
        }

        public Criteria andAcademyCodeNotBetween(String value1, String value2) {
            addCriterion("academy_code not between", value1, value2, "academyCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIsNull() {
            addCriterion("course_type is null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIsNotNull() {
            addCriterion("course_type is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeEqualTo(String value) {
            addCriterion("course_type =", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNotEqualTo(String value) {
            addCriterion("course_type <>", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeGreaterThan(String value) {
            addCriterion("course_type >", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("course_type >=", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeLessThan(String value) {
            addCriterion("course_type <", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeLessThanOrEqualTo(String value) {
            addCriterion("course_type <=", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeLike(String value) {
            addCriterion("course_type like", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNotLike(String value) {
            addCriterion("course_type not like", value, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIn(List<String> values) {
            addCriterion("course_type in", values, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNotIn(List<String> values) {
            addCriterion("course_type not in", values, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeBetween(String value1, String value2) {
            addCriterion("course_type between", value1, value2, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNotBetween(String value1, String value2) {
            addCriterion("course_type not between", value1, value2, "courseType");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeIsNull() {
            addCriterion("course_type_code is null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeIsNotNull() {
            addCriterion("course_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeEqualTo(String value) {
            addCriterion("course_type_code =", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeNotEqualTo(String value) {
            addCriterion("course_type_code <>", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeGreaterThan(String value) {
            addCriterion("course_type_code >", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("course_type_code >=", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeLessThan(String value) {
            addCriterion("course_type_code <", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("course_type_code <=", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeLike(String value) {
            addCriterion("course_type_code like", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeNotLike(String value) {
            addCriterion("course_type_code not like", value, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeIn(List<String> values) {
            addCriterion("course_type_code in", values, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeNotIn(List<String> values) {
            addCriterion("course_type_code not in", values, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeBetween(String value1, String value2) {
            addCriterion("course_type_code between", value1, value2, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCourseTypeCodeNotBetween(String value1, String value2) {
            addCriterion("course_type_code not between", value1, value2, "courseTypeCode");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(String value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(String value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(String value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(String value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(String value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(String value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLike(String value) {
            addCriterion("credit like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotLike(String value) {
            addCriterion("credit not like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<String> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<String> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(String value1, String value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(String value1, String value2) {
            addCriterion("credit not between", value1, value2, "credit");
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