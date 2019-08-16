package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrpGradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpGradeExample() {
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

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
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