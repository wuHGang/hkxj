package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrpExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpExamExample() {
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

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(Integer value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(Integer value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(Integer value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(Integer value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(Integer value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<Integer> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<Integer> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(Integer value1, Integer value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andAverageScoreIsNull() {
            addCriterion("average_score is null");
            return (Criteria) this;
        }

        public Criteria andAverageScoreIsNotNull() {
            addCriterion("average_score is not null");
            return (Criteria) this;
        }

        public Criteria andAverageScoreEqualTo(Double value) {
            addCriterion("average_score =", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreNotEqualTo(Double value) {
            addCriterion("average_score <>", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreGreaterThan(Double value) {
            addCriterion("average_score >", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreGreaterThanOrEqualTo(Double value) {
            addCriterion("average_score >=", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreLessThan(Double value) {
            addCriterion("average_score <", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreLessThanOrEqualTo(Double value) {
            addCriterion("average_score <=", value, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreIn(List<Double> values) {
            addCriterion("average_score in", values, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreNotIn(List<Double> values) {
            addCriterion("average_score not in", values, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreBetween(Double value1, Double value2) {
            addCriterion("average_score between", value1, value2, "averageScore");
            return (Criteria) this;
        }

        public Criteria andAverageScoreNotBetween(Double value1, Double value2) {
            addCriterion("average_score not between", value1, value2, "averageScore");
            return (Criteria) this;
        }

        public Criteria andClassNumberIsNull() {
            addCriterion("class_number is null");
            return (Criteria) this;
        }

        public Criteria andClassNumberIsNotNull() {
            addCriterion("class_number is not null");
            return (Criteria) this;
        }

        public Criteria andClassNumberEqualTo(String value) {
            addCriterion("class_number =", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotEqualTo(String value) {
            addCriterion("class_number <>", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberGreaterThan(String value) {
            addCriterion("class_number >", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberGreaterThanOrEqualTo(String value) {
            addCriterion("class_number >=", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberLessThan(String value) {
            addCriterion("class_number <", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberLessThanOrEqualTo(String value) {
            addCriterion("class_number <=", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberLike(String value) {
            addCriterion("class_number like", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotLike(String value) {
            addCriterion("class_number not like", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberIn(List<String> values) {
            addCriterion("class_number in", values, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotIn(List<String> values) {
            addCriterion("class_number not in", values, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberBetween(String value1, String value2) {
            addCriterion("class_number between", value1, value2, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotBetween(String value1, String value2) {
            addCriterion("class_number not between", value1, value2, "classNumber");
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

        public Criteria andCoursePropertyCodeIsNull() {
            addCriterion("course_property_code is null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeIsNotNull() {
            addCriterion("course_property_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeEqualTo(String value) {
            addCriterion("course_property_code =", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeNotEqualTo(String value) {
            addCriterion("course_property_code <>", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeGreaterThan(String value) {
            addCriterion("course_property_code >", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("course_property_code >=", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeLessThan(String value) {
            addCriterion("course_property_code <", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeLessThanOrEqualTo(String value) {
            addCriterion("course_property_code <=", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeLike(String value) {
            addCriterion("course_property_code like", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeNotLike(String value) {
            addCriterion("course_property_code not like", value, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeIn(List<String> values) {
            addCriterion("course_property_code in", values, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeNotIn(List<String> values) {
            addCriterion("course_property_code not in", values, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeBetween(String value1, String value2) {
            addCriterion("course_property_code between", value1, value2, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyCodeNotBetween(String value1, String value2) {
            addCriterion("course_property_code not between", value1, value2, "coursePropertyCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameIsNull() {
            addCriterion("course_property_name is null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameIsNotNull() {
            addCriterion("course_property_name is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameEqualTo(String value) {
            addCriterion("course_property_name =", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameNotEqualTo(String value) {
            addCriterion("course_property_name <>", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameGreaterThan(String value) {
            addCriterion("course_property_name >", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_property_name >=", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameLessThan(String value) {
            addCriterion("course_property_name <", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameLessThanOrEqualTo(String value) {
            addCriterion("course_property_name <=", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameLike(String value) {
            addCriterion("course_property_name like", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameNotLike(String value) {
            addCriterion("course_property_name not like", value, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameIn(List<String> values) {
            addCriterion("course_property_name in", values, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameNotIn(List<String> values) {
            addCriterion("course_property_name not in", values, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameBetween(String value1, String value2) {
            addCriterion("course_property_name between", value1, value2, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertyNameNotBetween(String value1, String value2) {
            addCriterion("course_property_name not between", value1, value2, "coursePropertyName");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNull() {
            addCriterion("examtime is null");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNotNull() {
            addCriterion("examtime is not null");
            return (Criteria) this;
        }

        public Criteria andExamtimeEqualTo(String value) {
            addCriterion("examtime =", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotEqualTo(String value) {
            addCriterion("examtime <>", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThan(String value) {
            addCriterion("examtime >", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThanOrEqualTo(String value) {
            addCriterion("examtime >=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThan(String value) {
            addCriterion("examtime <", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThanOrEqualTo(String value) {
            addCriterion("examtime <=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLike(String value) {
            addCriterion("examtime like", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotLike(String value) {
            addCriterion("examtime not like", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeIn(List<String> values) {
            addCriterion("examtime in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotIn(List<String> values) {
            addCriterion("examtime not in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeBetween(String value1, String value2) {
            addCriterion("examtime between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotBetween(String value1, String value2) {
            addCriterion("examtime not between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberIsNull() {
            addCriterion("executive_education_plan_number is null");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberIsNotNull() {
            addCriterion("executive_education_plan_number is not null");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberEqualTo(String value) {
            addCriterion("executive_education_plan_number =", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberNotEqualTo(String value) {
            addCriterion("executive_education_plan_number <>", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberGreaterThan(String value) {
            addCriterion("executive_education_plan_number >", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberGreaterThanOrEqualTo(String value) {
            addCriterion("executive_education_plan_number >=", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberLessThan(String value) {
            addCriterion("executive_education_plan_number <", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberLessThanOrEqualTo(String value) {
            addCriterion("executive_education_plan_number <=", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberLike(String value) {
            addCriterion("executive_education_plan_number like", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberNotLike(String value) {
            addCriterion("executive_education_plan_number not like", value, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberIn(List<String> values) {
            addCriterion("executive_education_plan_number in", values, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberNotIn(List<String> values) {
            addCriterion("executive_education_plan_number not in", values, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberBetween(String value1, String value2) {
            addCriterion("executive_education_plan_number between", value1, value2, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveEducationPlanNumberNotBetween(String value1, String value2) {
            addCriterion("executive_education_plan_number not between", value1, value2, "executiveEducationPlanNumber");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNull() {
            addCriterion("max_score is null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNotNull() {
            addCriterion("max_score is not null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreEqualTo(Integer value) {
            addCriterion("max_score =", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotEqualTo(Integer value) {
            addCriterion("max_score <>", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThan(Integer value) {
            addCriterion("max_score >", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_score >=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThan(Integer value) {
            addCriterion("max_score <", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThanOrEqualTo(Integer value) {
            addCriterion("max_score <=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIn(List<Integer> values) {
            addCriterion("max_score in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotIn(List<Integer> values) {
            addCriterion("max_score not in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreBetween(Integer value1, Integer value2) {
            addCriterion("max_score between", value1, value2, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("max_score not between", value1, value2, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreIsNull() {
            addCriterion("min_score is null");
            return (Criteria) this;
        }

        public Criteria andMinScoreIsNotNull() {
            addCriterion("min_score is not null");
            return (Criteria) this;
        }

        public Criteria andMinScoreEqualTo(Integer value) {
            addCriterion("min_score =", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreNotEqualTo(Integer value) {
            addCriterion("min_score <>", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreGreaterThan(Integer value) {
            addCriterion("min_score >", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_score >=", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreLessThan(Integer value) {
            addCriterion("min_score <", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreLessThanOrEqualTo(Integer value) {
            addCriterion("min_score <=", value, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreIn(List<Integer> values) {
            addCriterion("min_score in", values, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreNotIn(List<Integer> values) {
            addCriterion("min_score not in", values, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreBetween(Integer value1, Integer value2) {
            addCriterion("min_score between", value1, value2, "minScore");
            return (Criteria) this;
        }

        public Criteria andMinScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("min_score not between", value1, value2, "minScore");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNull() {
            addCriterion("operatetime is null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNotNull() {
            addCriterion("operatetime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeEqualTo(String value) {
            addCriterion("operatetime =", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotEqualTo(String value) {
            addCriterion("operatetime <>", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThan(String value) {
            addCriterion("operatetime >", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("operatetime >=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThan(String value) {
            addCriterion("operatetime <", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThanOrEqualTo(String value) {
            addCriterion("operatetime <=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLike(String value) {
            addCriterion("operatetime like", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotLike(String value) {
            addCriterion("operatetime not like", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIn(List<String> values) {
            addCriterion("operatetime in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotIn(List<String> values) {
            addCriterion("operatetime not in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeBetween(String value1, String value2) {
            addCriterion("operatetime between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotBetween(String value1, String value2) {
            addCriterion("operatetime not between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointIsNull() {
            addCriterion("persent_level_point is null");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointIsNotNull() {
            addCriterion("persent_level_point is not null");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointEqualTo(String value) {
            addCriterion("persent_level_point =", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointNotEqualTo(String value) {
            addCriterion("persent_level_point <>", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointGreaterThan(String value) {
            addCriterion("persent_level_point >", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointGreaterThanOrEqualTo(String value) {
            addCriterion("persent_level_point >=", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointLessThan(String value) {
            addCriterion("persent_level_point <", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointLessThanOrEqualTo(String value) {
            addCriterion("persent_level_point <=", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointLike(String value) {
            addCriterion("persent_level_point like", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointNotLike(String value) {
            addCriterion("persent_level_point not like", value, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointIn(List<String> values) {
            addCriterion("persent_level_point in", values, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointNotIn(List<String> values) {
            addCriterion("persent_level_point not in", values, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointBetween(String value1, String value2) {
            addCriterion("persent_level_point between", value1, value2, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andPersentLevelPointNotBetween(String value1, String value2) {
            addCriterion("persent_level_point not between", value1, value2, "persentLevelPoint");
            return (Criteria) this;
        }

        public Criteria andTermCodeIsNull() {
            addCriterion("term_code is null");
            return (Criteria) this;
        }

        public Criteria andTermCodeIsNotNull() {
            addCriterion("term_code is not null");
            return (Criteria) this;
        }

        public Criteria andTermCodeEqualTo(String value) {
            addCriterion("term_code =", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotEqualTo(String value) {
            addCriterion("term_code <>", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThan(String value) {
            addCriterion("term_code >", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeGreaterThanOrEqualTo(String value) {
            addCriterion("term_code >=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThan(String value) {
            addCriterion("term_code <", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLessThanOrEqualTo(String value) {
            addCriterion("term_code <=", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeLike(String value) {
            addCriterion("term_code like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotLike(String value) {
            addCriterion("term_code not like", value, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeIn(List<String> values) {
            addCriterion("term_code in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotIn(List<String> values) {
            addCriterion("term_code not in", values, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeBetween(String value1, String value2) {
            addCriterion("term_code between", value1, value2, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermCodeNotBetween(String value1, String value2) {
            addCriterion("term_code not between", value1, value2, "termCode");
            return (Criteria) this;
        }

        public Criteria andTermNameIsNull() {
            addCriterion("term_name is null");
            return (Criteria) this;
        }

        public Criteria andTermNameIsNotNull() {
            addCriterion("term_name is not null");
            return (Criteria) this;
        }

        public Criteria andTermNameEqualTo(String value) {
            addCriterion("term_name =", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotEqualTo(String value) {
            addCriterion("term_name <>", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameGreaterThan(String value) {
            addCriterion("term_name >", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameGreaterThanOrEqualTo(String value) {
            addCriterion("term_name >=", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLessThan(String value) {
            addCriterion("term_name <", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLessThanOrEqualTo(String value) {
            addCriterion("term_name <=", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLike(String value) {
            addCriterion("term_name like", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotLike(String value) {
            addCriterion("term_name not like", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameIn(List<String> values) {
            addCriterion("term_name in", values, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotIn(List<String> values) {
            addCriterion("term_name not in", values, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameBetween(String value1, String value2) {
            addCriterion("term_name between", value1, value2, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotBetween(String value1, String value2) {
            addCriterion("term_name not between", value1, value2, "termName");
            return (Criteria) this;
        }

        public Criteria andAcademyIsNull() {
            addCriterion("academy is null");
            return (Criteria) this;
        }

        public Criteria andAcademyIsNotNull() {
            addCriterion("academy is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyEqualTo(Integer value) {
            addCriterion("academy =", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyNotEqualTo(Integer value) {
            addCriterion("academy <>", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyGreaterThan(Integer value) {
            addCriterion("academy >", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyGreaterThanOrEqualTo(Integer value) {
            addCriterion("academy >=", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyLessThan(Integer value) {
            addCriterion("academy <", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyLessThanOrEqualTo(Integer value) {
            addCriterion("academy <=", value, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyIn(List<Integer> values) {
            addCriterion("academy in", values, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyNotIn(List<Integer> values) {
            addCriterion("academy not in", values, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyBetween(Integer value1, Integer value2) {
            addCriterion("academy between", value1, value2, "academy");
            return (Criteria) this;
        }

        public Criteria andAcademyNotBetween(Integer value1, Integer value2) {
            addCriterion("academy not between", value1, value2, "academy");
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