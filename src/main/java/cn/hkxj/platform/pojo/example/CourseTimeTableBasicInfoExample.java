package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseTimeTableBasicInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseTimeTableBasicInfoExample() {
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

        public Criteria andCoursePropertiesCodeIsNull() {
            addCriterion("course_properties_code is null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeIsNotNull() {
            addCriterion("course_properties_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeEqualTo(String value) {
            addCriterion("course_properties_code =", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeNotEqualTo(String value) {
            addCriterion("course_properties_code <>", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeGreaterThan(String value) {
            addCriterion("course_properties_code >", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeGreaterThanOrEqualTo(String value) {
            addCriterion("course_properties_code >=", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeLessThan(String value) {
            addCriterion("course_properties_code <", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeLessThanOrEqualTo(String value) {
            addCriterion("course_properties_code <=", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeLike(String value) {
            addCriterion("course_properties_code like", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeNotLike(String value) {
            addCriterion("course_properties_code not like", value, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeIn(List<String> values) {
            addCriterion("course_properties_code in", values, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeNotIn(List<String> values) {
            addCriterion("course_properties_code not in", values, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeBetween(String value1, String value2) {
            addCriterion("course_properties_code between", value1, value2, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesCodeNotBetween(String value1, String value2) {
            addCriterion("course_properties_code not between", value1, value2, "coursePropertiesCode");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameIsNull() {
            addCriterion("course_properties_name is null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameIsNotNull() {
            addCriterion("course_properties_name is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameEqualTo(String value) {
            addCriterion("course_properties_name =", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameNotEqualTo(String value) {
            addCriterion("course_properties_name <>", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameGreaterThan(String value) {
            addCriterion("course_properties_name >", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_properties_name >=", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameLessThan(String value) {
            addCriterion("course_properties_name <", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameLessThanOrEqualTo(String value) {
            addCriterion("course_properties_name <=", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameLike(String value) {
            addCriterion("course_properties_name like", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameNotLike(String value) {
            addCriterion("course_properties_name not like", value, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameIn(List<String> values) {
            addCriterion("course_properties_name in", values, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameNotIn(List<String> values) {
            addCriterion("course_properties_name not in", values, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameBetween(String value1, String value2) {
            addCriterion("course_properties_name between", value1, value2, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andCoursePropertiesNameNotBetween(String value1, String value2) {
            addCriterion("course_properties_name not between", value1, value2, "coursePropertiesName");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionIsNull() {
            addCriterion("restricted_condition is null");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionIsNotNull() {
            addCriterion("restricted_condition is not null");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionEqualTo(String value) {
            addCriterion("restricted_condition =", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionNotEqualTo(String value) {
            addCriterion("restricted_condition <>", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionGreaterThan(String value) {
            addCriterion("restricted_condition >", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionGreaterThanOrEqualTo(String value) {
            addCriterion("restricted_condition >=", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionLessThan(String value) {
            addCriterion("restricted_condition <", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionLessThanOrEqualTo(String value) {
            addCriterion("restricted_condition <=", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionLike(String value) {
            addCriterion("restricted_condition like", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionNotLike(String value) {
            addCriterion("restricted_condition not like", value, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionIn(List<String> values) {
            addCriterion("restricted_condition in", values, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionNotIn(List<String> values) {
            addCriterion("restricted_condition not in", values, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionBetween(String value1, String value2) {
            addCriterion("restricted_condition between", value1, value2, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictedConditionNotBetween(String value1, String value2) {
            addCriterion("restricted_condition not between", value1, value2, "restrictedCondition");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeIsNull() {
            addCriterion("select_course_status_code is null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeIsNotNull() {
            addCriterion("select_course_status_code is not null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeEqualTo(String value) {
            addCriterion("select_course_status_code =", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeNotEqualTo(String value) {
            addCriterion("select_course_status_code <>", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeGreaterThan(String value) {
            addCriterion("select_course_status_code >", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeGreaterThanOrEqualTo(String value) {
            addCriterion("select_course_status_code >=", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeLessThan(String value) {
            addCriterion("select_course_status_code <", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeLessThanOrEqualTo(String value) {
            addCriterion("select_course_status_code <=", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeLike(String value) {
            addCriterion("select_course_status_code like", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeNotLike(String value) {
            addCriterion("select_course_status_code not like", value, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeIn(List<String> values) {
            addCriterion("select_course_status_code in", values, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeNotIn(List<String> values) {
            addCriterion("select_course_status_code not in", values, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeBetween(String value1, String value2) {
            addCriterion("select_course_status_code between", value1, value2, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusCodeNotBetween(String value1, String value2) {
            addCriterion("select_course_status_code not between", value1, value2, "selectCourseStatusCode");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameIsNull() {
            addCriterion("select_course_status_name is null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameIsNotNull() {
            addCriterion("select_course_status_name is not null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameEqualTo(String value) {
            addCriterion("select_course_status_name =", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameNotEqualTo(String value) {
            addCriterion("select_course_status_name <>", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameGreaterThan(String value) {
            addCriterion("select_course_status_name >", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("select_course_status_name >=", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameLessThan(String value) {
            addCriterion("select_course_status_name <", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("select_course_status_name <=", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameLike(String value) {
            addCriterion("select_course_status_name like", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameNotLike(String value) {
            addCriterion("select_course_status_name not like", value, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameIn(List<String> values) {
            addCriterion("select_course_status_name in", values, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameNotIn(List<String> values) {
            addCriterion("select_course_status_name not in", values, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameBetween(String value1, String value2) {
            addCriterion("select_course_status_name between", value1, value2, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andSelectCourseStatusNameNotBetween(String value1, String value2) {
            addCriterion("select_course_status_name not between", value1, value2, "selectCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeIsNull() {
            addCriterion("study_mode_code is null");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeIsNotNull() {
            addCriterion("study_mode_code is not null");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeEqualTo(String value) {
            addCriterion("study_mode_code =", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeNotEqualTo(String value) {
            addCriterion("study_mode_code <>", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeGreaterThan(String value) {
            addCriterion("study_mode_code >", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("study_mode_code >=", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeLessThan(String value) {
            addCriterion("study_mode_code <", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeLessThanOrEqualTo(String value) {
            addCriterion("study_mode_code <=", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeLike(String value) {
            addCriterion("study_mode_code like", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeNotLike(String value) {
            addCriterion("study_mode_code not like", value, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeIn(List<String> values) {
            addCriterion("study_mode_code in", values, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeNotIn(List<String> values) {
            addCriterion("study_mode_code not in", values, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeBetween(String value1, String value2) {
            addCriterion("study_mode_code between", value1, value2, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeCodeNotBetween(String value1, String value2) {
            addCriterion("study_mode_code not between", value1, value2, "studyModeCode");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameIsNull() {
            addCriterion("study_mode_name is null");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameIsNotNull() {
            addCriterion("study_mode_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameEqualTo(String value) {
            addCriterion("study_mode_name =", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameNotEqualTo(String value) {
            addCriterion("study_mode_name <>", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameGreaterThan(String value) {
            addCriterion("study_mode_name >", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameGreaterThanOrEqualTo(String value) {
            addCriterion("study_mode_name >=", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameLessThan(String value) {
            addCriterion("study_mode_name <", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameLessThanOrEqualTo(String value) {
            addCriterion("study_mode_name <=", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameLike(String value) {
            addCriterion("study_mode_name like", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameNotLike(String value) {
            addCriterion("study_mode_name not like", value, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameIn(List<String> values) {
            addCriterion("study_mode_name in", values, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameNotIn(List<String> values) {
            addCriterion("study_mode_name not in", values, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameBetween(String value1, String value2) {
            addCriterion("study_mode_name between", value1, value2, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andStudyModeNameNotBetween(String value1, String value2) {
            addCriterion("study_mode_name not between", value1, value2, "studyModeName");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andDgFlagIsNull() {
            addCriterion("dg_flag is null");
            return (Criteria) this;
        }

        public Criteria andDgFlagIsNotNull() {
            addCriterion("dg_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDgFlagEqualTo(String value) {
            addCriterion("dg_flag =", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagNotEqualTo(String value) {
            addCriterion("dg_flag <>", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagGreaterThan(String value) {
            addCriterion("dg_flag >", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagGreaterThanOrEqualTo(String value) {
            addCriterion("dg_flag >=", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagLessThan(String value) {
            addCriterion("dg_flag <", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagLessThanOrEqualTo(String value) {
            addCriterion("dg_flag <=", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagLike(String value) {
            addCriterion("dg_flag like", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagNotLike(String value) {
            addCriterion("dg_flag not like", value, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagIn(List<String> values) {
            addCriterion("dg_flag in", values, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagNotIn(List<String> values) {
            addCriterion("dg_flag not in", values, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagBetween(String value1, String value2) {
            addCriterion("dg_flag between", value1, value2, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andDgFlagNotBetween(String value1, String value2) {
            addCriterion("dg_flag not between", value1, value2, "dgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagIsNull() {
            addCriterion("ywdg_flag is null");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagIsNotNull() {
            addCriterion("ywdg_flag is not null");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagEqualTo(String value) {
            addCriterion("ywdg_flag =", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagNotEqualTo(String value) {
            addCriterion("ywdg_flag <>", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagGreaterThan(String value) {
            addCriterion("ywdg_flag >", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ywdg_flag >=", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagLessThan(String value) {
            addCriterion("ywdg_flag <", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagLessThanOrEqualTo(String value) {
            addCriterion("ywdg_flag <=", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagLike(String value) {
            addCriterion("ywdg_flag like", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagNotLike(String value) {
            addCriterion("ywdg_flag not like", value, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagIn(List<String> values) {
            addCriterion("ywdg_flag in", values, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagNotIn(List<String> values) {
            addCriterion("ywdg_flag not in", values, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagBetween(String value1, String value2) {
            addCriterion("ywdg_flag between", value1, value2, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andYwdgFlagNotBetween(String value1, String value2) {
            addCriterion("ywdg_flag not between", value1, value2, "ywdgFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagIsNull() {
            addCriterion("rl_flag is null");
            return (Criteria) this;
        }

        public Criteria andRlFlagIsNotNull() {
            addCriterion("rl_flag is not null");
            return (Criteria) this;
        }

        public Criteria andRlFlagEqualTo(String value) {
            addCriterion("rl_flag =", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagNotEqualTo(String value) {
            addCriterion("rl_flag <>", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagGreaterThan(String value) {
            addCriterion("rl_flag >", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagGreaterThanOrEqualTo(String value) {
            addCriterion("rl_flag >=", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagLessThan(String value) {
            addCriterion("rl_flag <", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagLessThanOrEqualTo(String value) {
            addCriterion("rl_flag <=", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagLike(String value) {
            addCriterion("rl_flag like", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagNotLike(String value) {
            addCriterion("rl_flag not like", value, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagIn(List<String> values) {
            addCriterion("rl_flag in", values, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagNotIn(List<String> values) {
            addCriterion("rl_flag not in", values, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagBetween(String value1, String value2) {
            addCriterion("rl_flag between", value1, value2, "rlFlag");
            return (Criteria) this;
        }

        public Criteria andRlFlagNotBetween(String value1, String value2) {
            addCriterion("rl_flag not between", value1, value2, "rlFlag");
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