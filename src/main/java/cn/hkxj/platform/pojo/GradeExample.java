package cn.hkxj.platform.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GradeExample() {
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

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(Integer value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(Integer value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(Integer value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(Integer value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(Integer value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<Integer> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<Integer> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(Integer value1, Integer value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Double value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Double value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Double value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Double value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Double value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Double value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Double> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Double> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Double value1, Double value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Double value1, Double value2) {
            addCriterion("score not between", value1, value2, "score");
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

        public Criteria andCreditEqualTo(Double value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Double value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Double value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Double value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Double value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Double> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Double> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Double value1, Double value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Double value1, Double value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andGradePointIsNull() {
            addCriterion("grade_point is null");
            return (Criteria) this;
        }

        public Criteria andGradePointIsNotNull() {
            addCriterion("grade_point is not null");
            return (Criteria) this;
        }

        public Criteria andGradePointEqualTo(Double value) {
            addCriterion("grade_point =", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotEqualTo(Double value) {
            addCriterion("grade_point <>", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointGreaterThan(Double value) {
            addCriterion("grade_point >", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointGreaterThanOrEqualTo(Double value) {
            addCriterion("grade_point >=", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointLessThan(Double value) {
            addCriterion("grade_point <", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointLessThanOrEqualTo(Double value) {
            addCriterion("grade_point <=", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointIn(List<Double> values) {
            addCriterion("grade_point in", values, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotIn(List<Double> values) {
            addCriterion("grade_point not in", values, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointBetween(Double value1, Double value2) {
            addCriterion("grade_point between", value1, value2, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotBetween(Double value1, Double value2) {
            addCriterion("grade_point not between", value1, value2, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelPointIsNull() {
            addCriterion("level_point is null");
            return (Criteria) this;
        }

        public Criteria andLevelPointIsNotNull() {
            addCriterion("level_point is not null");
            return (Criteria) this;
        }

        public Criteria andLevelPointEqualTo(String value) {
            addCriterion("level_point =", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointNotEqualTo(String value) {
            addCriterion("level_point <>", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointGreaterThan(String value) {
            addCriterion("level_point >", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointGreaterThanOrEqualTo(String value) {
            addCriterion("level_point >=", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointLessThan(String value) {
            addCriterion("level_point <", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointLessThanOrEqualTo(String value) {
            addCriterion("level_point <=", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointLike(String value) {
            addCriterion("level_point like", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointNotLike(String value) {
            addCriterion("level_point not like", value, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointIn(List<String> values) {
            addCriterion("level_point in", values, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointNotIn(List<String> values) {
            addCriterion("level_point not in", values, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointBetween(String value1, String value2) {
            addCriterion("level_point between", value1, value2, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andLevelPointNotBetween(String value1, String value2) {
            addCriterion("level_point not between", value1, value2, "levelPoint");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("rank is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("rank is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(Integer value) {
            addCriterion("rank =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(Integer value) {
            addCriterion("rank <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(Integer value) {
            addCriterion("rank >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(Integer value) {
            addCriterion("rank <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(Integer value) {
            addCriterion("rank <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<Integer> values) {
            addCriterion("rank in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<Integer> values) {
            addCriterion("rank not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(Integer value1, Integer value2) {
            addCriterion("rank between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(Integer value1, Integer value2) {
            addCriterion("rank not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNumberIsNull() {
            addCriterion("course_number is null");
            return (Criteria) this;
        }

        public Criteria andCourseNumberIsNotNull() {
            addCriterion("course_number is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNumberEqualTo(String value) {
            addCriterion("course_number =", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberNotEqualTo(String value) {
            addCriterion("course_number <>", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberGreaterThan(String value) {
            addCriterion("course_number >", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("course_number >=", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberLessThan(String value) {
            addCriterion("course_number <", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberLessThanOrEqualTo(String value) {
            addCriterion("course_number <=", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberLike(String value) {
            addCriterion("course_number like", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberNotLike(String value) {
            addCriterion("course_number not like", value, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberIn(List<String> values) {
            addCriterion("course_number in", values, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberNotIn(List<String> values) {
            addCriterion("course_number not in", values, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberBetween(String value1, String value2) {
            addCriterion("course_number between", value1, value2, "courseNumber");
            return (Criteria) this;
        }

        public Criteria andCourseNumberNotBetween(String value1, String value2) {
            addCriterion("course_number not between", value1, value2, "courseNumber");
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

        public Criteria andExamTypeNameIsNull() {
            addCriterion("exam_type_name is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameIsNotNull() {
            addCriterion("exam_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameEqualTo(String value) {
            addCriterion("exam_type_name =", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotEqualTo(String value) {
            addCriterion("exam_type_name <>", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameGreaterThan(String value) {
            addCriterion("exam_type_name >", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("exam_type_name >=", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLessThan(String value) {
            addCriterion("exam_type_name <", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLessThanOrEqualTo(String value) {
            addCriterion("exam_type_name <=", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLike(String value) {
            addCriterion("exam_type_name like", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotLike(String value) {
            addCriterion("exam_type_name not like", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameIn(List<String> values) {
            addCriterion("exam_type_name in", values, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotIn(List<String> values) {
            addCriterion("exam_type_name not in", values, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameBetween(String value1, String value2) {
            addCriterion("exam_type_name between", value1, value2, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotBetween(String value1, String value2) {
            addCriterion("exam_type_name not between", value1, value2, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andStudyHourIsNull() {
            addCriterion("study_hour is null");
            return (Criteria) this;
        }

        public Criteria andStudyHourIsNotNull() {
            addCriterion("study_hour is not null");
            return (Criteria) this;
        }

        public Criteria andStudyHourEqualTo(Integer value) {
            addCriterion("study_hour =", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourNotEqualTo(Integer value) {
            addCriterion("study_hour <>", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourGreaterThan(Integer value) {
            addCriterion("study_hour >", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("study_hour >=", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourLessThan(Integer value) {
            addCriterion("study_hour <", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourLessThanOrEqualTo(Integer value) {
            addCriterion("study_hour <=", value, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourIn(List<Integer> values) {
            addCriterion("study_hour in", values, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourNotIn(List<Integer> values) {
            addCriterion("study_hour not in", values, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourBetween(Integer value1, Integer value2) {
            addCriterion("study_hour between", value1, value2, "studyHour");
            return (Criteria) this;
        }

        public Criteria andStudyHourNotBetween(Integer value1, Integer value2) {
            addCriterion("study_hour not between", value1, value2, "studyHour");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("operate_time is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(String value) {
            addCriterion("operate_time =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(String value) {
            addCriterion("operate_time <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(String value) {
            addCriterion("operate_time >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("operate_time >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(String value) {
            addCriterion("operate_time <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(String value) {
            addCriterion("operate_time <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLike(String value) {
            addCriterion("operate_time like", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotLike(String value) {
            addCriterion("operate_time not like", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<String> values) {
            addCriterion("operate_time in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<String> values) {
            addCriterion("operate_time not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(String value1, String value2) {
            addCriterion("operate_time between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(String value1, String value2) {
            addCriterion("operate_time not between", value1, value2, "operateTime");
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

        public Criteria andExamTimeIsNull() {
            addCriterion("exam_time is null");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNotNull() {
            addCriterion("exam_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamTimeEqualTo(String value) {
            addCriterion("exam_time =", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotEqualTo(String value) {
            addCriterion("exam_time <>", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThan(String value) {
            addCriterion("exam_time >", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThanOrEqualTo(String value) {
            addCriterion("exam_time >=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThan(String value) {
            addCriterion("exam_time <", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThanOrEqualTo(String value) {
            addCriterion("exam_time <=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLike(String value) {
            addCriterion("exam_time like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotLike(String value) {
            addCriterion("exam_time not like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeIn(List<String> values) {
            addCriterion("exam_time in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotIn(List<String> values) {
            addCriterion("exam_time not in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeBetween(String value1, String value2) {
            addCriterion("exam_time between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotBetween(String value1, String value2) {
            addCriterion("exam_time not between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeIsNull() {
            addCriterion("unpassed_reason_code is null");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeIsNotNull() {
            addCriterion("unpassed_reason_code is not null");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeEqualTo(String value) {
            addCriterion("unpassed_reason_code =", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeNotEqualTo(String value) {
            addCriterion("unpassed_reason_code <>", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeGreaterThan(String value) {
            addCriterion("unpassed_reason_code >", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeGreaterThanOrEqualTo(String value) {
            addCriterion("unpassed_reason_code >=", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeLessThan(String value) {
            addCriterion("unpassed_reason_code <", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeLessThanOrEqualTo(String value) {
            addCriterion("unpassed_reason_code <=", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeLike(String value) {
            addCriterion("unpassed_reason_code like", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeNotLike(String value) {
            addCriterion("unpassed_reason_code not like", value, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeIn(List<String> values) {
            addCriterion("unpassed_reason_code in", values, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeNotIn(List<String> values) {
            addCriterion("unpassed_reason_code not in", values, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeBetween(String value1, String value2) {
            addCriterion("unpassed_reason_code between", value1, value2, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonCodeNotBetween(String value1, String value2) {
            addCriterion("unpassed_reason_code not between", value1, value2, "unpassedReasonCode");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainIsNull() {
            addCriterion("unpassed_reason_explain is null");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainIsNotNull() {
            addCriterion("unpassed_reason_explain is not null");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainEqualTo(String value) {
            addCriterion("unpassed_reason_explain =", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainNotEqualTo(String value) {
            addCriterion("unpassed_reason_explain <>", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainGreaterThan(String value) {
            addCriterion("unpassed_reason_explain >", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainGreaterThanOrEqualTo(String value) {
            addCriterion("unpassed_reason_explain >=", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainLessThan(String value) {
            addCriterion("unpassed_reason_explain <", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainLessThanOrEqualTo(String value) {
            addCriterion("unpassed_reason_explain <=", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainLike(String value) {
            addCriterion("unpassed_reason_explain like", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainNotLike(String value) {
            addCriterion("unpassed_reason_explain not like", value, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainIn(List<String> values) {
            addCriterion("unpassed_reason_explain in", values, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainNotIn(List<String> values) {
            addCriterion("unpassed_reason_explain not in", values, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainBetween(String value1, String value2) {
            addCriterion("unpassed_reason_explain between", value1, value2, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedReasonExplainNotBetween(String value1, String value2) {
            addCriterion("unpassed_reason_explain not between", value1, value2, "unpassedReasonExplain");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberIsNull() {
            addCriterion("replace_course_number is null");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberIsNotNull() {
            addCriterion("replace_course_number is not null");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberEqualTo(String value) {
            addCriterion("replace_course_number =", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberNotEqualTo(String value) {
            addCriterion("replace_course_number <>", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberGreaterThan(String value) {
            addCriterion("replace_course_number >", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("replace_course_number >=", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberLessThan(String value) {
            addCriterion("replace_course_number <", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberLessThanOrEqualTo(String value) {
            addCriterion("replace_course_number <=", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberLike(String value) {
            addCriterion("replace_course_number like", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberNotLike(String value) {
            addCriterion("replace_course_number not like", value, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberIn(List<String> values) {
            addCriterion("replace_course_number in", values, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberNotIn(List<String> values) {
            addCriterion("replace_course_number not in", values, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberBetween(String value1, String value2) {
            addCriterion("replace_course_number between", value1, value2, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andReplaceCourseNumberNotBetween(String value1, String value2) {
            addCriterion("replace_course_number not between", value1, value2, "replaceCourseNumber");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkIsNull() {
            addCriterion("retake_course_mark is null");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkIsNotNull() {
            addCriterion("retake_course_mark is not null");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkEqualTo(String value) {
            addCriterion("retake_course_mark =", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkNotEqualTo(String value) {
            addCriterion("retake_course_mark <>", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkGreaterThan(String value) {
            addCriterion("retake_course_mark >", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkGreaterThanOrEqualTo(String value) {
            addCriterion("retake_course_mark >=", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkLessThan(String value) {
            addCriterion("retake_course_mark <", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkLessThanOrEqualTo(String value) {
            addCriterion("retake_course_mark <=", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkLike(String value) {
            addCriterion("retake_course_mark like", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkNotLike(String value) {
            addCriterion("retake_course_mark not like", value, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkIn(List<String> values) {
            addCriterion("retake_course_mark in", values, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkNotIn(List<String> values) {
            addCriterion("retake_course_mark not in", values, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkBetween(String value1, String value2) {
            addCriterion("retake_course_mark between", value1, value2, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseMarkNotBetween(String value1, String value2) {
            addCriterion("retake_course_mark not between", value1, value2, "retakeCourseMark");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeIsNull() {
            addCriterion("retakeCourse_mode_code is null");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeIsNotNull() {
            addCriterion("retakeCourse_mode_code is not null");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeEqualTo(String value) {
            addCriterion("retakeCourse_mode_code =", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeNotEqualTo(String value) {
            addCriterion("retakeCourse_mode_code <>", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeGreaterThan(String value) {
            addCriterion("retakeCourse_mode_code >", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("retakeCourse_mode_code >=", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeLessThan(String value) {
            addCriterion("retakeCourse_mode_code <", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeLessThanOrEqualTo(String value) {
            addCriterion("retakeCourse_mode_code <=", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeLike(String value) {
            addCriterion("retakeCourse_mode_code like", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeNotLike(String value) {
            addCriterion("retakeCourse_mode_code not like", value, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeIn(List<String> values) {
            addCriterion("retakeCourse_mode_code in", values, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeNotIn(List<String> values) {
            addCriterion("retakeCourse_mode_code not in", values, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeBetween(String value1, String value2) {
            addCriterion("retakeCourse_mode_code between", value1, value2, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakecourseModeCodeNotBetween(String value1, String value2) {
            addCriterion("retakeCourse_mode_code not between", value1, value2, "retakecourseModeCode");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainIsNull() {
            addCriterion("retake_course_mode_explain is null");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainIsNotNull() {
            addCriterion("retake_course_mode_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainEqualTo(String value) {
            addCriterion("retake_course_mode_explain =", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainNotEqualTo(String value) {
            addCriterion("retake_course_mode_explain <>", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainGreaterThan(String value) {
            addCriterion("retake_course_mode_explain >", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainGreaterThanOrEqualTo(String value) {
            addCriterion("retake_course_mode_explain >=", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainLessThan(String value) {
            addCriterion("retake_course_mode_explain <", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainLessThanOrEqualTo(String value) {
            addCriterion("retake_course_mode_explain <=", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainLike(String value) {
            addCriterion("retake_course_mode_explain like", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainNotLike(String value) {
            addCriterion("retake_course_mode_explain not like", value, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainIn(List<String> values) {
            addCriterion("retake_course_mode_explain in", values, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainNotIn(List<String> values) {
            addCriterion("retake_course_mode_explain not in", values, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainBetween(String value1, String value2) {
            addCriterion("retake_course_mode_explain between", value1, value2, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andRetakeCourseModeExplainNotBetween(String value1, String value2) {
            addCriterion("retake_course_mode_explain not between", value1, value2, "retakeCourseModeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardPointIsNull() {
            addCriterion("standard_point is null");
            return (Criteria) this;
        }

        public Criteria andStandardPointIsNotNull() {
            addCriterion("standard_point is not null");
            return (Criteria) this;
        }

        public Criteria andStandardPointEqualTo(String value) {
            addCriterion("standard_point =", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointNotEqualTo(String value) {
            addCriterion("standard_point <>", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointGreaterThan(String value) {
            addCriterion("standard_point >", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointGreaterThanOrEqualTo(String value) {
            addCriterion("standard_point >=", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointLessThan(String value) {
            addCriterion("standard_point <", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointLessThanOrEqualTo(String value) {
            addCriterion("standard_point <=", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointLike(String value) {
            addCriterion("standard_point like", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointNotLike(String value) {
            addCriterion("standard_point not like", value, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointIn(List<String> values) {
            addCriterion("standard_point in", values, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointNotIn(List<String> values) {
            addCriterion("standard_point not in", values, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointBetween(String value1, String value2) {
            addCriterion("standard_point between", value1, value2, "standardPoint");
            return (Criteria) this;
        }

        public Criteria andStandardPointNotBetween(String value1, String value2) {
            addCriterion("standard_point not between", value1, value2, "standardPoint");
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

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
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