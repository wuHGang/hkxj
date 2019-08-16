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
            addCriterion("account =", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(Integer value) {
            addCriterion("account <>", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(Integer value) {
            addCriterion("account >", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("account >=", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(Integer value) {
            addCriterion("account <", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(Integer value) {
            addCriterion("account <=", value, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<Integer> values) {
            addCriterion("account in", values, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<Integer> values) {
            addCriterion("account not in", values, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(Integer value1, Integer value2) {
            addCriterion("account between", value1, value2, "Account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("account not between", value1, value2, "Account");
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

        public Criteria andUnpassedreasoncodeIsNull() {
            addCriterion("unpassedReasonCode is null");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeIsNotNull() {
            addCriterion("unpassedReasonCode is not null");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeEqualTo(String value) {
            addCriterion("unpassedReasonCode =", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeNotEqualTo(String value) {
            addCriterion("unpassedReasonCode <>", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeGreaterThan(String value) {
            addCriterion("unpassedReasonCode >", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeGreaterThanOrEqualTo(String value) {
            addCriterion("unpassedReasonCode >=", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeLessThan(String value) {
            addCriterion("unpassedReasonCode <", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeLessThanOrEqualTo(String value) {
            addCriterion("unpassedReasonCode <=", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeLike(String value) {
            addCriterion("unpassedReasonCode like", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeNotLike(String value) {
            addCriterion("unpassedReasonCode not like", value, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeIn(List<String> values) {
            addCriterion("unpassedReasonCode in", values, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeNotIn(List<String> values) {
            addCriterion("unpassedReasonCode not in", values, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeBetween(String value1, String value2) {
            addCriterion("unpassedReasonCode between", value1, value2, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasoncodeNotBetween(String value1, String value2) {
            addCriterion("unpassedReasonCode not between", value1, value2, "unpassedreasoncode");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainIsNull() {
            addCriterion("unpassedReasonExplain is null");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainIsNotNull() {
            addCriterion("unpassedReasonExplain is not null");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainEqualTo(String value) {
            addCriterion("unpassedReasonExplain =", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainNotEqualTo(String value) {
            addCriterion("unpassedReasonExplain <>", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainGreaterThan(String value) {
            addCriterion("unpassedReasonExplain >", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainGreaterThanOrEqualTo(String value) {
            addCriterion("unpassedReasonExplain >=", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainLessThan(String value) {
            addCriterion("unpassedReasonExplain <", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainLessThanOrEqualTo(String value) {
            addCriterion("unpassedReasonExplain <=", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainLike(String value) {
            addCriterion("unpassedReasonExplain like", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainNotLike(String value) {
            addCriterion("unpassedReasonExplain not like", value, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainIn(List<String> values) {
            addCriterion("unpassedReasonExplain in", values, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainNotIn(List<String> values) {
            addCriterion("unpassedReasonExplain not in", values, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainBetween(String value1, String value2) {
            addCriterion("unpassedReasonExplain between", value1, value2, "unpassedreasonexplain");
            return (Criteria) this;
        }

        public Criteria andUnpassedreasonexplainNotBetween(String value1, String value2) {
            addCriterion("unpassedReasonExplain not between", value1, value2, "unpassedreasonexplain");
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

        public Criteria andReplacecoursenumberIsNull() {
            addCriterion("replaceCourseNumber is null");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberIsNotNull() {
            addCriterion("replaceCourseNumber is not null");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberEqualTo(String value) {
            addCriterion("replaceCourseNumber =", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberNotEqualTo(String value) {
            addCriterion("replaceCourseNumber <>", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberGreaterThan(String value) {
            addCriterion("replaceCourseNumber >", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberGreaterThanOrEqualTo(String value) {
            addCriterion("replaceCourseNumber >=", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberLessThan(String value) {
            addCriterion("replaceCourseNumber <", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberLessThanOrEqualTo(String value) {
            addCriterion("replaceCourseNumber <=", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberLike(String value) {
            addCriterion("replaceCourseNumber like", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberNotLike(String value) {
            addCriterion("replaceCourseNumber not like", value, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberIn(List<String> values) {
            addCriterion("replaceCourseNumber in", values, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberNotIn(List<String> values) {
            addCriterion("replaceCourseNumber not in", values, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberBetween(String value1, String value2) {
            addCriterion("replaceCourseNumber between", value1, value2, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andReplacecoursenumberNotBetween(String value1, String value2) {
            addCriterion("replaceCourseNumber not between", value1, value2, "replacecoursenumber");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkIsNull() {
            addCriterion("retakeCourseMark is null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkIsNotNull() {
            addCriterion("retakeCourseMark is not null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkEqualTo(String value) {
            addCriterion("retakeCourseMark =", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkNotEqualTo(String value) {
            addCriterion("retakeCourseMark <>", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkGreaterThan(String value) {
            addCriterion("retakeCourseMark >", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkGreaterThanOrEqualTo(String value) {
            addCriterion("retakeCourseMark >=", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkLessThan(String value) {
            addCriterion("retakeCourseMark <", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkLessThanOrEqualTo(String value) {
            addCriterion("retakeCourseMark <=", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkLike(String value) {
            addCriterion("retakeCourseMark like", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkNotLike(String value) {
            addCriterion("retakeCourseMark not like", value, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkIn(List<String> values) {
            addCriterion("retakeCourseMark in", values, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkNotIn(List<String> values) {
            addCriterion("retakeCourseMark not in", values, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkBetween(String value1, String value2) {
            addCriterion("retakeCourseMark between", value1, value2, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemarkNotBetween(String value1, String value2) {
            addCriterion("retakeCourseMark not between", value1, value2, "retakecoursemark");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeIsNull() {
            addCriterion("retakeCourseModeCode is null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeIsNotNull() {
            addCriterion("retakeCourseModeCode is not null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeEqualTo(String value) {
            addCriterion("retakeCourseModeCode =", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeNotEqualTo(String value) {
            addCriterion("retakeCourseModeCode <>", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeGreaterThan(String value) {
            addCriterion("retakeCourseModeCode >", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeGreaterThanOrEqualTo(String value) {
            addCriterion("retakeCourseModeCode >=", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeLessThan(String value) {
            addCriterion("retakeCourseModeCode <", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeLessThanOrEqualTo(String value) {
            addCriterion("retakeCourseModeCode <=", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeLike(String value) {
            addCriterion("retakeCourseModeCode like", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeNotLike(String value) {
            addCriterion("retakeCourseModeCode not like", value, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeIn(List<String> values) {
            addCriterion("retakeCourseModeCode in", values, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeNotIn(List<String> values) {
            addCriterion("retakeCourseModeCode not in", values, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeBetween(String value1, String value2) {
            addCriterion("retakeCourseModeCode between", value1, value2, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodecodeNotBetween(String value1, String value2) {
            addCriterion("retakeCourseModeCode not between", value1, value2, "retakecoursemodecode");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainIsNull() {
            addCriterion("retakeCourseModeExplain is null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainIsNotNull() {
            addCriterion("retakeCourseModeExplain is not null");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainEqualTo(String value) {
            addCriterion("retakeCourseModeExplain =", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainNotEqualTo(String value) {
            addCriterion("retakeCourseModeExplain <>", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainGreaterThan(String value) {
            addCriterion("retakeCourseModeExplain >", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainGreaterThanOrEqualTo(String value) {
            addCriterion("retakeCourseModeExplain >=", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainLessThan(String value) {
            addCriterion("retakeCourseModeExplain <", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainLessThanOrEqualTo(String value) {
            addCriterion("retakeCourseModeExplain <=", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainLike(String value) {
            addCriterion("retakeCourseModeExplain like", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainNotLike(String value) {
            addCriterion("retakeCourseModeExplain not like", value, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainIn(List<String> values) {
            addCriterion("retakeCourseModeExplain in", values, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainNotIn(List<String> values) {
            addCriterion("retakeCourseModeExplain not in", values, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainBetween(String value1, String value2) {
            addCriterion("retakeCourseModeExplain between", value1, value2, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andRetakecoursemodeexplainNotBetween(String value1, String value2) {
            addCriterion("retakeCourseModeExplain not between", value1, value2, "retakecoursemodeexplain");
            return (Criteria) this;
        }

        public Criteria andStandardpointIsNull() {
            addCriterion("standardPoint is null");
            return (Criteria) this;
        }

        public Criteria andStandardpointIsNotNull() {
            addCriterion("standardPoint is not null");
            return (Criteria) this;
        }

        public Criteria andStandardpointEqualTo(String value) {
            addCriterion("standardPoint =", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointNotEqualTo(String value) {
            addCriterion("standardPoint <>", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointGreaterThan(String value) {
            addCriterion("standardPoint >", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointGreaterThanOrEqualTo(String value) {
            addCriterion("standardPoint >=", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointLessThan(String value) {
            addCriterion("standardPoint <", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointLessThanOrEqualTo(String value) {
            addCriterion("standardPoint <=", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointLike(String value) {
            addCriterion("standardPoint like", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointNotLike(String value) {
            addCriterion("standardPoint not like", value, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointIn(List<String> values) {
            addCriterion("standardPoint in", values, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointNotIn(List<String> values) {
            addCriterion("standardPoint not in", values, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointBetween(String value1, String value2) {
            addCriterion("standardPoint between", value1, value2, "standardpoint");
            return (Criteria) this;
        }

        public Criteria andStandardpointNotBetween(String value1, String value2) {
            addCriterion("standardPoint not between", value1, value2, "standardpoint");
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