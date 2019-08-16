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

        public Criteria andAvgcjIsNull() {
            addCriterion("avgcj is null");
            return (Criteria) this;
        }

        public Criteria andAvgcjIsNotNull() {
            addCriterion("avgcj is not null");
            return (Criteria) this;
        }

        public Criteria andAvgcjEqualTo(Double value) {
            addCriterion("avgcj =", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjNotEqualTo(Double value) {
            addCriterion("avgcj <>", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjGreaterThan(Double value) {
            addCriterion("avgcj >", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjGreaterThanOrEqualTo(Double value) {
            addCriterion("avgcj >=", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjLessThan(Double value) {
            addCriterion("avgcj <", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjLessThanOrEqualTo(Double value) {
            addCriterion("avgcj <=", value, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjIn(List<Double> values) {
            addCriterion("avgcj in", values, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjNotIn(List<Double> values) {
            addCriterion("avgcj not in", values, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjBetween(Double value1, Double value2) {
            addCriterion("avgcj between", value1, value2, "avgcj");
            return (Criteria) this;
        }

        public Criteria andAvgcjNotBetween(Double value1, Double value2) {
            addCriterion("avgcj not between", value1, value2, "avgcj");
            return (Criteria) this;
        }

        public Criteria andBjhIsNull() {
            addCriterion("bjh is null");
            return (Criteria) this;
        }

        public Criteria andBjhIsNotNull() {
            addCriterion("bjh is not null");
            return (Criteria) this;
        }

        public Criteria andBjhEqualTo(String value) {
            addCriterion("bjh =", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhNotEqualTo(String value) {
            addCriterion("bjh <>", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhGreaterThan(String value) {
            addCriterion("bjh >", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhGreaterThanOrEqualTo(String value) {
            addCriterion("bjh >=", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhLessThan(String value) {
            addCriterion("bjh <", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhLessThanOrEqualTo(String value) {
            addCriterion("bjh <=", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhLike(String value) {
            addCriterion("bjh like", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhNotLike(String value) {
            addCriterion("bjh not like", value, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhIn(List<String> values) {
            addCriterion("bjh in", values, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhNotIn(List<String> values) {
            addCriterion("bjh not in", values, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhBetween(String value1, String value2) {
            addCriterion("bjh between", value1, value2, "bjh");
            return (Criteria) this;
        }

        public Criteria andBjhNotBetween(String value1, String value2) {
            addCriterion("bjh not between", value1, value2, "bjh");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberIsNull() {
            addCriterion("courseSequenceNumber is null");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberIsNotNull() {
            addCriterion("courseSequenceNumber is not null");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberEqualTo(String value) {
            addCriterion("courseSequenceNumber =", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberNotEqualTo(String value) {
            addCriterion("courseSequenceNumber <>", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberGreaterThan(String value) {
            addCriterion("courseSequenceNumber >", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberGreaterThanOrEqualTo(String value) {
            addCriterion("courseSequenceNumber >=", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberLessThan(String value) {
            addCriterion("courseSequenceNumber <", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberLessThanOrEqualTo(String value) {
            addCriterion("courseSequenceNumber <=", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberLike(String value) {
            addCriterion("courseSequenceNumber like", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberNotLike(String value) {
            addCriterion("courseSequenceNumber not like", value, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberIn(List<String> values) {
            addCriterion("courseSequenceNumber in", values, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberNotIn(List<String> values) {
            addCriterion("courseSequenceNumber not in", values, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberBetween(String value1, String value2) {
            addCriterion("courseSequenceNumber between", value1, value2, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursesequencenumberNotBetween(String value1, String value2) {
            addCriterion("courseSequenceNumber not between", value1, value2, "coursesequencenumber");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeIsNull() {
            addCriterion("coursePropertyCode is null");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeIsNotNull() {
            addCriterion("coursePropertyCode is not null");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeEqualTo(String value) {
            addCriterion("coursePropertyCode =", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeNotEqualTo(String value) {
            addCriterion("coursePropertyCode <>", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeGreaterThan(String value) {
            addCriterion("coursePropertyCode >", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeGreaterThanOrEqualTo(String value) {
            addCriterion("coursePropertyCode >=", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeLessThan(String value) {
            addCriterion("coursePropertyCode <", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeLessThanOrEqualTo(String value) {
            addCriterion("coursePropertyCode <=", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeLike(String value) {
            addCriterion("coursePropertyCode like", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeNotLike(String value) {
            addCriterion("coursePropertyCode not like", value, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeIn(List<String> values) {
            addCriterion("coursePropertyCode in", values, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeNotIn(List<String> values) {
            addCriterion("coursePropertyCode not in", values, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeBetween(String value1, String value2) {
            addCriterion("coursePropertyCode between", value1, value2, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertycodeNotBetween(String value1, String value2) {
            addCriterion("coursePropertyCode not between", value1, value2, "coursepropertycode");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameIsNull() {
            addCriterion("coursePropertyName is null");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameIsNotNull() {
            addCriterion("coursePropertyName is not null");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameEqualTo(String value) {
            addCriterion("coursePropertyName =", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameNotEqualTo(String value) {
            addCriterion("coursePropertyName <>", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameGreaterThan(String value) {
            addCriterion("coursePropertyName >", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameGreaterThanOrEqualTo(String value) {
            addCriterion("coursePropertyName >=", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameLessThan(String value) {
            addCriterion("coursePropertyName <", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameLessThanOrEqualTo(String value) {
            addCriterion("coursePropertyName <=", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameLike(String value) {
            addCriterion("coursePropertyName like", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameNotLike(String value) {
            addCriterion("coursePropertyName not like", value, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameIn(List<String> values) {
            addCriterion("coursePropertyName in", values, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameNotIn(List<String> values) {
            addCriterion("coursePropertyName not in", values, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameBetween(String value1, String value2) {
            addCriterion("coursePropertyName between", value1, value2, "coursepropertyname");
            return (Criteria) this;
        }

        public Criteria andCoursepropertynameNotBetween(String value1, String value2) {
            addCriterion("coursePropertyName not between", value1, value2, "coursepropertyname");
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

        public Criteria andExecutiveeducationplannumberIsNull() {
            addCriterion("executiveEducationPlanNumber is null");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberIsNotNull() {
            addCriterion("executiveEducationPlanNumber is not null");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberEqualTo(String value) {
            addCriterion("executiveEducationPlanNumber =", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberNotEqualTo(String value) {
            addCriterion("executiveEducationPlanNumber <>", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberGreaterThan(String value) {
            addCriterion("executiveEducationPlanNumber >", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberGreaterThanOrEqualTo(String value) {
            addCriterion("executiveEducationPlanNumber >=", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberLessThan(String value) {
            addCriterion("executiveEducationPlanNumber <", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberLessThanOrEqualTo(String value) {
            addCriterion("executiveEducationPlanNumber <=", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberLike(String value) {
            addCriterion("executiveEducationPlanNumber like", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberNotLike(String value) {
            addCriterion("executiveEducationPlanNumber not like", value, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberIn(List<String> values) {
            addCriterion("executiveEducationPlanNumber in", values, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberNotIn(List<String> values) {
            addCriterion("executiveEducationPlanNumber not in", values, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberBetween(String value1, String value2) {
            addCriterion("executiveEducationPlanNumber between", value1, value2, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andExecutiveeducationplannumberNotBetween(String value1, String value2) {
            addCriterion("executiveEducationPlanNumber not between", value1, value2, "executiveeducationplannumber");
            return (Criteria) this;
        }

        public Criteria andMaxcjIsNull() {
            addCriterion("maxcj is null");
            return (Criteria) this;
        }

        public Criteria andMaxcjIsNotNull() {
            addCriterion("maxcj is not null");
            return (Criteria) this;
        }

        public Criteria andMaxcjEqualTo(Integer value) {
            addCriterion("maxcj =", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjNotEqualTo(Integer value) {
            addCriterion("maxcj <>", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjGreaterThan(Integer value) {
            addCriterion("maxcj >", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxcj >=", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjLessThan(Integer value) {
            addCriterion("maxcj <", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjLessThanOrEqualTo(Integer value) {
            addCriterion("maxcj <=", value, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjIn(List<Integer> values) {
            addCriterion("maxcj in", values, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjNotIn(List<Integer> values) {
            addCriterion("maxcj not in", values, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjBetween(Integer value1, Integer value2) {
            addCriterion("maxcj between", value1, value2, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMaxcjNotBetween(Integer value1, Integer value2) {
            addCriterion("maxcj not between", value1, value2, "maxcj");
            return (Criteria) this;
        }

        public Criteria andMincjIsNull() {
            addCriterion("mincj is null");
            return (Criteria) this;
        }

        public Criteria andMincjIsNotNull() {
            addCriterion("mincj is not null");
            return (Criteria) this;
        }

        public Criteria andMincjEqualTo(Integer value) {
            addCriterion("mincj =", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjNotEqualTo(Integer value) {
            addCriterion("mincj <>", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjGreaterThan(Integer value) {
            addCriterion("mincj >", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjGreaterThanOrEqualTo(Integer value) {
            addCriterion("mincj >=", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjLessThan(Integer value) {
            addCriterion("mincj <", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjLessThanOrEqualTo(Integer value) {
            addCriterion("mincj <=", value, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjIn(List<Integer> values) {
            addCriterion("mincj in", values, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjNotIn(List<Integer> values) {
            addCriterion("mincj not in", values, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjBetween(Integer value1, Integer value2) {
            addCriterion("mincj between", value1, value2, "mincj");
            return (Criteria) this;
        }

        public Criteria andMincjNotBetween(Integer value1, Integer value2) {
            addCriterion("mincj not between", value1, value2, "mincj");
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

        public Criteria andPersentlevelpointIsNull() {
            addCriterion("persentlevelPoint is null");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointIsNotNull() {
            addCriterion("persentlevelPoint is not null");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointEqualTo(String value) {
            addCriterion("persentlevelPoint =", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointNotEqualTo(String value) {
            addCriterion("persentlevelPoint <>", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointGreaterThan(String value) {
            addCriterion("persentlevelPoint >", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointGreaterThanOrEqualTo(String value) {
            addCriterion("persentlevelPoint >=", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointLessThan(String value) {
            addCriterion("persentlevelPoint <", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointLessThanOrEqualTo(String value) {
            addCriterion("persentlevelPoint <=", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointLike(String value) {
            addCriterion("persentlevelPoint like", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointNotLike(String value) {
            addCriterion("persentlevelPoint not like", value, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointIn(List<String> values) {
            addCriterion("persentlevelPoint in", values, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointNotIn(List<String> values) {
            addCriterion("persentlevelPoint not in", values, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointBetween(String value1, String value2) {
            addCriterion("persentlevelPoint between", value1, value2, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andPersentlevelpointNotBetween(String value1, String value2) {
            addCriterion("persentlevelPoint not between", value1, value2, "persentlevelpoint");
            return (Criteria) this;
        }

        public Criteria andTermcodeIsNull() {
            addCriterion("termCode is null");
            return (Criteria) this;
        }

        public Criteria andTermcodeIsNotNull() {
            addCriterion("termCode is not null");
            return (Criteria) this;
        }

        public Criteria andTermcodeEqualTo(String value) {
            addCriterion("termCode =", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotEqualTo(String value) {
            addCriterion("termCode <>", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThan(String value) {
            addCriterion("termCode >", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeGreaterThanOrEqualTo(String value) {
            addCriterion("termCode >=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThan(String value) {
            addCriterion("termCode <", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLessThanOrEqualTo(String value) {
            addCriterion("termCode <=", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeLike(String value) {
            addCriterion("termCode like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotLike(String value) {
            addCriterion("termCode not like", value, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeIn(List<String> values) {
            addCriterion("termCode in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotIn(List<String> values) {
            addCriterion("termCode not in", values, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeBetween(String value1, String value2) {
            addCriterion("termCode between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermcodeNotBetween(String value1, String value2) {
            addCriterion("termCode not between", value1, value2, "termcode");
            return (Criteria) this;
        }

        public Criteria andTermnameIsNull() {
            addCriterion("termName is null");
            return (Criteria) this;
        }

        public Criteria andTermnameIsNotNull() {
            addCriterion("termName is not null");
            return (Criteria) this;
        }

        public Criteria andTermnameEqualTo(String value) {
            addCriterion("termName =", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameNotEqualTo(String value) {
            addCriterion("termName <>", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameGreaterThan(String value) {
            addCriterion("termName >", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameGreaterThanOrEqualTo(String value) {
            addCriterion("termName >=", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameLessThan(String value) {
            addCriterion("termName <", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameLessThanOrEqualTo(String value) {
            addCriterion("termName <=", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameLike(String value) {
            addCriterion("termName like", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameNotLike(String value) {
            addCriterion("termName not like", value, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameIn(List<String> values) {
            addCriterion("termName in", values, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameNotIn(List<String> values) {
            addCriterion("termName not in", values, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameBetween(String value1, String value2) {
            addCriterion("termName between", value1, value2, "termname");
            return (Criteria) this;
        }

        public Criteria andTermnameNotBetween(String value1, String value2) {
            addCriterion("termName not between", value1, value2, "termname");
            return (Criteria) this;
        }

        public Criteria andXshIsNull() {
            addCriterion("xsh is null");
            return (Criteria) this;
        }

        public Criteria andXshIsNotNull() {
            addCriterion("xsh is not null");
            return (Criteria) this;
        }

        public Criteria andXshEqualTo(Integer value) {
            addCriterion("xsh =", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshNotEqualTo(Integer value) {
            addCriterion("xsh <>", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshGreaterThan(Integer value) {
            addCriterion("xsh >", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshGreaterThanOrEqualTo(Integer value) {
            addCriterion("xsh >=", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshLessThan(Integer value) {
            addCriterion("xsh <", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshLessThanOrEqualTo(Integer value) {
            addCriterion("xsh <=", value, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshIn(List<Integer> values) {
            addCriterion("xsh in", values, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshNotIn(List<Integer> values) {
            addCriterion("xsh not in", values, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshBetween(Integer value1, Integer value2) {
            addCriterion("xsh between", value1, value2, "xsh");
            return (Criteria) this;
        }

        public Criteria andXshNotBetween(Integer value1, Integer value2) {
            addCriterion("xsh not between", value1, value2, "xsh");
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