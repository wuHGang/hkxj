package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrpGradeDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpGradeDetailExample() {
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

        public Criteria andGradeIdIsNull() {
            addCriterion("grade_id is null");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNotNull() {
            addCriterion("grade_id is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIdEqualTo(Integer value) {
            addCriterion("grade_id =", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotEqualTo(Integer value) {
            addCriterion("grade_id <>", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThan(Integer value) {
            addCriterion("grade_id >", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_id >=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThan(Integer value) {
            addCriterion("grade_id <", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("grade_id <=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIn(List<Integer> values) {
            addCriterion("grade_id in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotIn(List<Integer> values) {
            addCriterion("grade_id not in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdBetween(Integer value1, Integer value2) {
            addCriterion("grade_id between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_id not between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientIsNull() {
            addCriterion("usualScoreCoefficient is null");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientIsNotNull() {
            addCriterion("usualScoreCoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientEqualTo(String value) {
            addCriterion("usualScoreCoefficient =", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientNotEqualTo(String value) {
            addCriterion("usualScoreCoefficient <>", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientGreaterThan(String value) {
            addCriterion("usualScoreCoefficient >", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("usualScoreCoefficient >=", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientLessThan(String value) {
            addCriterion("usualScoreCoefficient <", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientLessThanOrEqualTo(String value) {
            addCriterion("usualScoreCoefficient <=", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientLike(String value) {
            addCriterion("usualScoreCoefficient like", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientNotLike(String value) {
            addCriterion("usualScoreCoefficient not like", value, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientIn(List<String> values) {
            addCriterion("usualScoreCoefficient in", values, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientNotIn(List<String> values) {
            addCriterion("usualScoreCoefficient not in", values, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientBetween(String value1, String value2) {
            addCriterion("usualScoreCoefficient between", value1, value2, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualscorecoefficientNotBetween(String value1, String value2) {
            addCriterion("usualScoreCoefficient not between", value1, value2, "usualscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientIsNull() {
            addCriterion("midtermScoreCoefficient is null");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientIsNotNull() {
            addCriterion("midtermScoreCoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientEqualTo(String value) {
            addCriterion("midtermScoreCoefficient =", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientNotEqualTo(String value) {
            addCriterion("midtermScoreCoefficient <>", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientGreaterThan(String value) {
            addCriterion("midtermScoreCoefficient >", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("midtermScoreCoefficient >=", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientLessThan(String value) {
            addCriterion("midtermScoreCoefficient <", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientLessThanOrEqualTo(String value) {
            addCriterion("midtermScoreCoefficient <=", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientLike(String value) {
            addCriterion("midtermScoreCoefficient like", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientNotLike(String value) {
            addCriterion("midtermScoreCoefficient not like", value, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientIn(List<String> values) {
            addCriterion("midtermScoreCoefficient in", values, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientNotIn(List<String> values) {
            addCriterion("midtermScoreCoefficient not in", values, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientBetween(String value1, String value2) {
            addCriterion("midtermScoreCoefficient between", value1, value2, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermscorecoefficientNotBetween(String value1, String value2) {
            addCriterion("midtermScoreCoefficient not between", value1, value2, "midtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientIsNull() {
            addCriterion("endtermScoreCoefficient is null");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientIsNotNull() {
            addCriterion("endtermScoreCoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientEqualTo(String value) {
            addCriterion("endtermScoreCoefficient =", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientNotEqualTo(String value) {
            addCriterion("endtermScoreCoefficient <>", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientGreaterThan(String value) {
            addCriterion("endtermScoreCoefficient >", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("endtermScoreCoefficient >=", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientLessThan(String value) {
            addCriterion("endtermScoreCoefficient <", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientLessThanOrEqualTo(String value) {
            addCriterion("endtermScoreCoefficient <=", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientLike(String value) {
            addCriterion("endtermScoreCoefficient like", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientNotLike(String value) {
            addCriterion("endtermScoreCoefficient not like", value, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientIn(List<String> values) {
            addCriterion("endtermScoreCoefficient in", values, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientNotIn(List<String> values) {
            addCriterion("endtermScoreCoefficient not in", values, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientBetween(String value1, String value2) {
            addCriterion("endtermScoreCoefficient between", value1, value2, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermscorecoefficientNotBetween(String value1, String value2) {
            addCriterion("endtermScoreCoefficient not between", value1, value2, "endtermscorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientIsNull() {
            addCriterion("scoreCoefficient is null");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientIsNotNull() {
            addCriterion("scoreCoefficient is not null");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientEqualTo(String value) {
            addCriterion("scoreCoefficient =", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientNotEqualTo(String value) {
            addCriterion("scoreCoefficient <>", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientGreaterThan(String value) {
            addCriterion("scoreCoefficient >", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("scoreCoefficient >=", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientLessThan(String value) {
            addCriterion("scoreCoefficient <", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientLessThanOrEqualTo(String value) {
            addCriterion("scoreCoefficient <=", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientLike(String value) {
            addCriterion("scoreCoefficient like", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientNotLike(String value) {
            addCriterion("scoreCoefficient not like", value, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientIn(List<String> values) {
            addCriterion("scoreCoefficient in", values, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientNotIn(List<String> values) {
            addCriterion("scoreCoefficient not in", values, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientBetween(String value1, String value2) {
            addCriterion("scoreCoefficient between", value1, value2, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andScorecoefficientNotBetween(String value1, String value2) {
            addCriterion("scoreCoefficient not between", value1, value2, "scorecoefficient");
            return (Criteria) this;
        }

        public Criteria andXsRemarkIsNull() {
            addCriterion("xs_remark is null");
            return (Criteria) this;
        }

        public Criteria andXsRemarkIsNotNull() {
            addCriterion("xs_remark is not null");
            return (Criteria) this;
        }

        public Criteria andXsRemarkEqualTo(String value) {
            addCriterion("xs_remark =", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkNotEqualTo(String value) {
            addCriterion("xs_remark <>", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkGreaterThan(String value) {
            addCriterion("xs_remark >", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("xs_remark >=", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkLessThan(String value) {
            addCriterion("xs_remark <", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkLessThanOrEqualTo(String value) {
            addCriterion("xs_remark <=", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkLike(String value) {
            addCriterion("xs_remark like", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkNotLike(String value) {
            addCriterion("xs_remark not like", value, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkIn(List<String> values) {
            addCriterion("xs_remark in", values, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkNotIn(List<String> values) {
            addCriterion("xs_remark not in", values, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkBetween(String value1, String value2) {
            addCriterion("xs_remark between", value1, value2, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andXsRemarkNotBetween(String value1, String value2) {
            addCriterion("xs_remark not between", value1, value2, "xsRemark");
            return (Criteria) this;
        }

        public Criteria andZcjIsNull() {
            addCriterion("zcj is null");
            return (Criteria) this;
        }

        public Criteria andZcjIsNotNull() {
            addCriterion("zcj is not null");
            return (Criteria) this;
        }

        public Criteria andZcjEqualTo(Double value) {
            addCriterion("zcj =", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjNotEqualTo(Double value) {
            addCriterion("zcj <>", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjGreaterThan(Double value) {
            addCriterion("zcj >", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjGreaterThanOrEqualTo(Double value) {
            addCriterion("zcj >=", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjLessThan(Double value) {
            addCriterion("zcj <", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjLessThanOrEqualTo(Double value) {
            addCriterion("zcj <=", value, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjIn(List<Double> values) {
            addCriterion("zcj in", values, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjNotIn(List<Double> values) {
            addCriterion("zcj not in", values, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjBetween(Double value1, Double value2) {
            addCriterion("zcj between", value1, value2, "zcj");
            return (Criteria) this;
        }

        public Criteria andZcjNotBetween(Double value1, Double value2) {
            addCriterion("zcj not between", value1, value2, "zcj");
            return (Criteria) this;
        }

        public Criteria andPscjIsNull() {
            addCriterion("pscj is null");
            return (Criteria) this;
        }

        public Criteria andPscjIsNotNull() {
            addCriterion("pscj is not null");
            return (Criteria) this;
        }

        public Criteria andPscjEqualTo(Double value) {
            addCriterion("pscj =", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjNotEqualTo(Double value) {
            addCriterion("pscj <>", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjGreaterThan(Double value) {
            addCriterion("pscj >", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjGreaterThanOrEqualTo(Double value) {
            addCriterion("pscj >=", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjLessThan(Double value) {
            addCriterion("pscj <", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjLessThanOrEqualTo(Double value) {
            addCriterion("pscj <=", value, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjIn(List<Double> values) {
            addCriterion("pscj in", values, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjNotIn(List<Double> values) {
            addCriterion("pscj not in", values, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjBetween(Double value1, Double value2) {
            addCriterion("pscj between", value1, value2, "pscj");
            return (Criteria) this;
        }

        public Criteria andPscjNotBetween(Double value1, Double value2) {
            addCriterion("pscj not between", value1, value2, "pscj");
            return (Criteria) this;
        }

        public Criteria andQzcjIsNull() {
            addCriterion("qzcj is null");
            return (Criteria) this;
        }

        public Criteria andQzcjIsNotNull() {
            addCriterion("qzcj is not null");
            return (Criteria) this;
        }

        public Criteria andQzcjEqualTo(Double value) {
            addCriterion("qzcj =", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjNotEqualTo(Double value) {
            addCriterion("qzcj <>", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjGreaterThan(Double value) {
            addCriterion("qzcj >", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjGreaterThanOrEqualTo(Double value) {
            addCriterion("qzcj >=", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjLessThan(Double value) {
            addCriterion("qzcj <", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjLessThanOrEqualTo(Double value) {
            addCriterion("qzcj <=", value, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjIn(List<Double> values) {
            addCriterion("qzcj in", values, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjNotIn(List<Double> values) {
            addCriterion("qzcj not in", values, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjBetween(Double value1, Double value2) {
            addCriterion("qzcj between", value1, value2, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQzcjNotBetween(Double value1, Double value2) {
            addCriterion("qzcj not between", value1, value2, "qzcj");
            return (Criteria) this;
        }

        public Criteria andQmcjIsNull() {
            addCriterion("qmcj is null");
            return (Criteria) this;
        }

        public Criteria andQmcjIsNotNull() {
            addCriterion("qmcj is not null");
            return (Criteria) this;
        }

        public Criteria andQmcjEqualTo(Double value) {
            addCriterion("qmcj =", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjNotEqualTo(Double value) {
            addCriterion("qmcj <>", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjGreaterThan(Double value) {
            addCriterion("qmcj >", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjGreaterThanOrEqualTo(Double value) {
            addCriterion("qmcj >=", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjLessThan(Double value) {
            addCriterion("qmcj <", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjLessThanOrEqualTo(Double value) {
            addCriterion("qmcj <=", value, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjIn(List<Double> values) {
            addCriterion("qmcj in", values, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjNotIn(List<Double> values) {
            addCriterion("qmcj not in", values, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjBetween(Double value1, Double value2) {
            addCriterion("qmcj between", value1, value2, "qmcj");
            return (Criteria) this;
        }

        public Criteria andQmcjNotBetween(Double value1, Double value2) {
            addCriterion("qmcj not between", value1, value2, "qmcj");
            return (Criteria) this;
        }

        public Criteria andRoundingIsNull() {
            addCriterion("rounding is null");
            return (Criteria) this;
        }

        public Criteria andRoundingIsNotNull() {
            addCriterion("rounding is not null");
            return (Criteria) this;
        }

        public Criteria andRoundingEqualTo(String value) {
            addCriterion("rounding =", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingNotEqualTo(String value) {
            addCriterion("rounding <>", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingGreaterThan(String value) {
            addCriterion("rounding >", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingGreaterThanOrEqualTo(String value) {
            addCriterion("rounding >=", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingLessThan(String value) {
            addCriterion("rounding <", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingLessThanOrEqualTo(String value) {
            addCriterion("rounding <=", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingLike(String value) {
            addCriterion("rounding like", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingNotLike(String value) {
            addCriterion("rounding not like", value, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingIn(List<String> values) {
            addCriterion("rounding in", values, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingNotIn(List<String> values) {
            addCriterion("rounding not in", values, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingBetween(String value1, String value2) {
            addCriterion("rounding between", value1, value2, "rounding");
            return (Criteria) this;
        }

        public Criteria andRoundingNotBetween(String value1, String value2) {
            addCriterion("rounding not between", value1, value2, "rounding");
            return (Criteria) this;
        }

        public Criteria andMxRemarkIsNull() {
            addCriterion("mx_remark is null");
            return (Criteria) this;
        }

        public Criteria andMxRemarkIsNotNull() {
            addCriterion("mx_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMxRemarkEqualTo(String value) {
            addCriterion("mx_remark =", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkNotEqualTo(String value) {
            addCriterion("mx_remark <>", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkGreaterThan(String value) {
            addCriterion("mx_remark >", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("mx_remark >=", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkLessThan(String value) {
            addCriterion("mx_remark <", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkLessThanOrEqualTo(String value) {
            addCriterion("mx_remark <=", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkLike(String value) {
            addCriterion("mx_remark like", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkNotLike(String value) {
            addCriterion("mx_remark not like", value, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkIn(List<String> values) {
            addCriterion("mx_remark in", values, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkNotIn(List<String> values) {
            addCriterion("mx_remark not in", values, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkBetween(String value1, String value2) {
            addCriterion("mx_remark between", value1, value2, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemarkNotBetween(String value1, String value2) {
            addCriterion("mx_remark not between", value1, value2, "mxRemark");
            return (Criteria) this;
        }

        public Criteria andMxRemark1IsNull() {
            addCriterion("mx_remark1 is null");
            return (Criteria) this;
        }

        public Criteria andMxRemark1IsNotNull() {
            addCriterion("mx_remark1 is not null");
            return (Criteria) this;
        }

        public Criteria andMxRemark1EqualTo(String value) {
            addCriterion("mx_remark1 =", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1NotEqualTo(String value) {
            addCriterion("mx_remark1 <>", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1GreaterThan(String value) {
            addCriterion("mx_remark1 >", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1GreaterThanOrEqualTo(String value) {
            addCriterion("mx_remark1 >=", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1LessThan(String value) {
            addCriterion("mx_remark1 <", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1LessThanOrEqualTo(String value) {
            addCriterion("mx_remark1 <=", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1Like(String value) {
            addCriterion("mx_remark1 like", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1NotLike(String value) {
            addCriterion("mx_remark1 not like", value, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1In(List<String> values) {
            addCriterion("mx_remark1 in", values, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1NotIn(List<String> values) {
            addCriterion("mx_remark1 not in", values, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1Between(String value1, String value2) {
            addCriterion("mx_remark1 between", value1, value2, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark1NotBetween(String value1, String value2) {
            addCriterion("mx_remark1 not between", value1, value2, "mxRemark1");
            return (Criteria) this;
        }

        public Criteria andMxRemark2IsNull() {
            addCriterion("mx_remark2 is null");
            return (Criteria) this;
        }

        public Criteria andMxRemark2IsNotNull() {
            addCriterion("mx_remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andMxRemark2EqualTo(String value) {
            addCriterion("mx_remark2 =", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2NotEqualTo(String value) {
            addCriterion("mx_remark2 <>", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2GreaterThan(String value) {
            addCriterion("mx_remark2 >", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("mx_remark2 >=", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2LessThan(String value) {
            addCriterion("mx_remark2 <", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2LessThanOrEqualTo(String value) {
            addCriterion("mx_remark2 <=", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2Like(String value) {
            addCriterion("mx_remark2 like", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2NotLike(String value) {
            addCriterion("mx_remark2 not like", value, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2In(List<String> values) {
            addCriterion("mx_remark2 in", values, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2NotIn(List<String> values) {
            addCriterion("mx_remark2 not in", values, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2Between(String value1, String value2) {
            addCriterion("mx_remark2 between", value1, value2, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andMxRemark2NotBetween(String value1, String value2) {
            addCriterion("mx_remark2 not between", value1, value2, "mxRemark2");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsIsNull() {
            addCriterion("firstcjxs is null");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsIsNotNull() {
            addCriterion("firstcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsEqualTo(String value) {
            addCriterion("firstcjxs =", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsNotEqualTo(String value) {
            addCriterion("firstcjxs <>", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsGreaterThan(String value) {
            addCriterion("firstcjxs >", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("firstcjxs >=", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsLessThan(String value) {
            addCriterion("firstcjxs <", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsLessThanOrEqualTo(String value) {
            addCriterion("firstcjxs <=", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsLike(String value) {
            addCriterion("firstcjxs like", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsNotLike(String value) {
            addCriterion("firstcjxs not like", value, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsIn(List<String> values) {
            addCriterion("firstcjxs in", values, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsNotIn(List<String> values) {
            addCriterion("firstcjxs not in", values, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsBetween(String value1, String value2) {
            addCriterion("firstcjxs between", value1, value2, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstcjxsNotBetween(String value1, String value2) {
            addCriterion("firstcjxs not between", value1, value2, "firstcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsIsNull() {
            addCriterion("firstpscjxs is null");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsIsNotNull() {
            addCriterion("firstpscjxs is not null");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsEqualTo(String value) {
            addCriterion("firstpscjxs =", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsNotEqualTo(String value) {
            addCriterion("firstpscjxs <>", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsGreaterThan(String value) {
            addCriterion("firstpscjxs >", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsGreaterThanOrEqualTo(String value) {
            addCriterion("firstpscjxs >=", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsLessThan(String value) {
            addCriterion("firstpscjxs <", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsLessThanOrEqualTo(String value) {
            addCriterion("firstpscjxs <=", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsLike(String value) {
            addCriterion("firstpscjxs like", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsNotLike(String value) {
            addCriterion("firstpscjxs not like", value, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsIn(List<String> values) {
            addCriterion("firstpscjxs in", values, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsNotIn(List<String> values) {
            addCriterion("firstpscjxs not in", values, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsBetween(String value1, String value2) {
            addCriterion("firstpscjxs between", value1, value2, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstpscjxsNotBetween(String value1, String value2) {
            addCriterion("firstpscjxs not between", value1, value2, "firstpscjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsIsNull() {
            addCriterion("firstqzcjxs is null");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsIsNotNull() {
            addCriterion("firstqzcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsEqualTo(String value) {
            addCriterion("firstqzcjxs =", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsNotEqualTo(String value) {
            addCriterion("firstqzcjxs <>", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsGreaterThan(String value) {
            addCriterion("firstqzcjxs >", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("firstqzcjxs >=", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsLessThan(String value) {
            addCriterion("firstqzcjxs <", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsLessThanOrEqualTo(String value) {
            addCriterion("firstqzcjxs <=", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsLike(String value) {
            addCriterion("firstqzcjxs like", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsNotLike(String value) {
            addCriterion("firstqzcjxs not like", value, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsIn(List<String> values) {
            addCriterion("firstqzcjxs in", values, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsNotIn(List<String> values) {
            addCriterion("firstqzcjxs not in", values, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsBetween(String value1, String value2) {
            addCriterion("firstqzcjxs between", value1, value2, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqzcjxsNotBetween(String value1, String value2) {
            addCriterion("firstqzcjxs not between", value1, value2, "firstqzcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsIsNull() {
            addCriterion("firstqmcjxs is null");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsIsNotNull() {
            addCriterion("firstqmcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsEqualTo(String value) {
            addCriterion("firstqmcjxs =", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsNotEqualTo(String value) {
            addCriterion("firstqmcjxs <>", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsGreaterThan(String value) {
            addCriterion("firstqmcjxs >", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("firstqmcjxs >=", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsLessThan(String value) {
            addCriterion("firstqmcjxs <", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsLessThanOrEqualTo(String value) {
            addCriterion("firstqmcjxs <=", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsLike(String value) {
            addCriterion("firstqmcjxs like", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsNotLike(String value) {
            addCriterion("firstqmcjxs not like", value, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsIn(List<String> values) {
            addCriterion("firstqmcjxs in", values, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsNotIn(List<String> values) {
            addCriterion("firstqmcjxs not in", values, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsBetween(String value1, String value2) {
            addCriterion("firstqmcjxs between", value1, value2, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andFirstqmcjxsNotBetween(String value1, String value2) {
            addCriterion("firstqmcjxs not between", value1, value2, "firstqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsIsNull() {
            addCriterion("secondcjxs is null");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsIsNotNull() {
            addCriterion("secondcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsEqualTo(String value) {
            addCriterion("secondcjxs =", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsNotEqualTo(String value) {
            addCriterion("secondcjxs <>", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsGreaterThan(String value) {
            addCriterion("secondcjxs >", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("secondcjxs >=", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsLessThan(String value) {
            addCriterion("secondcjxs <", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsLessThanOrEqualTo(String value) {
            addCriterion("secondcjxs <=", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsLike(String value) {
            addCriterion("secondcjxs like", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsNotLike(String value) {
            addCriterion("secondcjxs not like", value, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsIn(List<String> values) {
            addCriterion("secondcjxs in", values, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsNotIn(List<String> values) {
            addCriterion("secondcjxs not in", values, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsBetween(String value1, String value2) {
            addCriterion("secondcjxs between", value1, value2, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondcjxsNotBetween(String value1, String value2) {
            addCriterion("secondcjxs not between", value1, value2, "secondcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsIsNull() {
            addCriterion("secondpscjxs is null");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsIsNotNull() {
            addCriterion("secondpscjxs is not null");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsEqualTo(String value) {
            addCriterion("secondpscjxs =", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsNotEqualTo(String value) {
            addCriterion("secondpscjxs <>", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsGreaterThan(String value) {
            addCriterion("secondpscjxs >", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsGreaterThanOrEqualTo(String value) {
            addCriterion("secondpscjxs >=", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsLessThan(String value) {
            addCriterion("secondpscjxs <", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsLessThanOrEqualTo(String value) {
            addCriterion("secondpscjxs <=", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsLike(String value) {
            addCriterion("secondpscjxs like", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsNotLike(String value) {
            addCriterion("secondpscjxs not like", value, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsIn(List<String> values) {
            addCriterion("secondpscjxs in", values, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsNotIn(List<String> values) {
            addCriterion("secondpscjxs not in", values, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsBetween(String value1, String value2) {
            addCriterion("secondpscjxs between", value1, value2, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondpscjxsNotBetween(String value1, String value2) {
            addCriterion("secondpscjxs not between", value1, value2, "secondpscjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsIsNull() {
            addCriterion("secondqzcjxs is null");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsIsNotNull() {
            addCriterion("secondqzcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsEqualTo(String value) {
            addCriterion("secondqzcjxs =", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsNotEqualTo(String value) {
            addCriterion("secondqzcjxs <>", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsGreaterThan(String value) {
            addCriterion("secondqzcjxs >", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("secondqzcjxs >=", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsLessThan(String value) {
            addCriterion("secondqzcjxs <", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsLessThanOrEqualTo(String value) {
            addCriterion("secondqzcjxs <=", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsLike(String value) {
            addCriterion("secondqzcjxs like", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsNotLike(String value) {
            addCriterion("secondqzcjxs not like", value, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsIn(List<String> values) {
            addCriterion("secondqzcjxs in", values, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsNotIn(List<String> values) {
            addCriterion("secondqzcjxs not in", values, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsBetween(String value1, String value2) {
            addCriterion("secondqzcjxs between", value1, value2, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqzcjxsNotBetween(String value1, String value2) {
            addCriterion("secondqzcjxs not between", value1, value2, "secondqzcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsIsNull() {
            addCriterion("secondqmcjxs is null");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsIsNotNull() {
            addCriterion("secondqmcjxs is not null");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsEqualTo(String value) {
            addCriterion("secondqmcjxs =", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsNotEqualTo(String value) {
            addCriterion("secondqmcjxs <>", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsGreaterThan(String value) {
            addCriterion("secondqmcjxs >", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsGreaterThanOrEqualTo(String value) {
            addCriterion("secondqmcjxs >=", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsLessThan(String value) {
            addCriterion("secondqmcjxs <", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsLessThanOrEqualTo(String value) {
            addCriterion("secondqmcjxs <=", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsLike(String value) {
            addCriterion("secondqmcjxs like", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsNotLike(String value) {
            addCriterion("secondqmcjxs not like", value, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsIn(List<String> values) {
            addCriterion("secondqmcjxs in", values, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsNotIn(List<String> values) {
            addCriterion("secondqmcjxs not in", values, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsBetween(String value1, String value2) {
            addCriterion("secondqmcjxs between", value1, value2, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andSecondqmcjxsNotBetween(String value1, String value2) {
            addCriterion("secondqmcjxs not between", value1, value2, "secondqmcjxs");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeIsNull() {
            addCriterion("scoreTypeCode is null");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeIsNotNull() {
            addCriterion("scoreTypeCode is not null");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeEqualTo(String value) {
            addCriterion("scoreTypeCode =", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeNotEqualTo(String value) {
            addCriterion("scoreTypeCode <>", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeGreaterThan(String value) {
            addCriterion("scoreTypeCode >", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeGreaterThanOrEqualTo(String value) {
            addCriterion("scoreTypeCode >=", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeLessThan(String value) {
            addCriterion("scoreTypeCode <", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeLessThanOrEqualTo(String value) {
            addCriterion("scoreTypeCode <=", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeLike(String value) {
            addCriterion("scoreTypeCode like", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeNotLike(String value) {
            addCriterion("scoreTypeCode not like", value, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeIn(List<String> values) {
            addCriterion("scoreTypeCode in", values, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeNotIn(List<String> values) {
            addCriterion("scoreTypeCode not in", values, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeBetween(String value1, String value2) {
            addCriterion("scoreTypeCode between", value1, value2, "scoretypecode");
            return (Criteria) this;
        }

        public Criteria andScoretypecodeNotBetween(String value1, String value2) {
            addCriterion("scoreTypeCode not between", value1, value2, "scoretypecode");
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