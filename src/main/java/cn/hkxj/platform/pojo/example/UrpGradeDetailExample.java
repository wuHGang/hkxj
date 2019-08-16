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

        public Criteria andUsualScoreCoefficientIsNull() {
            addCriterion("usual_score_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientIsNotNull() {
            addCriterion("usual_score_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientEqualTo(String value) {
            addCriterion("usual_score_coefficient =", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientNotEqualTo(String value) {
            addCriterion("usual_score_coefficient <>", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientGreaterThan(String value) {
            addCriterion("usual_score_coefficient >", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("usual_score_coefficient >=", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientLessThan(String value) {
            addCriterion("usual_score_coefficient <", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientLessThanOrEqualTo(String value) {
            addCriterion("usual_score_coefficient <=", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientLike(String value) {
            addCriterion("usual_score_coefficient like", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientNotLike(String value) {
            addCriterion("usual_score_coefficient not like", value, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientIn(List<String> values) {
            addCriterion("usual_score_coefficient in", values, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientNotIn(List<String> values) {
            addCriterion("usual_score_coefficient not in", values, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientBetween(String value1, String value2) {
            addCriterion("usual_score_coefficient between", value1, value2, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andUsualScoreCoefficientNotBetween(String value1, String value2) {
            addCriterion("usual_score_coefficient not between", value1, value2, "usualScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientIsNull() {
            addCriterion("midterm_score_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientIsNotNull() {
            addCriterion("midterm_score_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientEqualTo(String value) {
            addCriterion("midterm_score_coefficient =", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientNotEqualTo(String value) {
            addCriterion("midterm_score_coefficient <>", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientGreaterThan(String value) {
            addCriterion("midterm_score_coefficient >", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("midterm_score_coefficient >=", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientLessThan(String value) {
            addCriterion("midterm_score_coefficient <", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientLessThanOrEqualTo(String value) {
            addCriterion("midterm_score_coefficient <=", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientLike(String value) {
            addCriterion("midterm_score_coefficient like", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientNotLike(String value) {
            addCriterion("midterm_score_coefficient not like", value, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientIn(List<String> values) {
            addCriterion("midterm_score_coefficient in", values, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientNotIn(List<String> values) {
            addCriterion("midterm_score_coefficient not in", values, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientBetween(String value1, String value2) {
            addCriterion("midterm_score_coefficient between", value1, value2, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andMidtermScoreCoefficientNotBetween(String value1, String value2) {
            addCriterion("midterm_score_coefficient not between", value1, value2, "midtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientIsNull() {
            addCriterion("endterm_score_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientIsNotNull() {
            addCriterion("endterm_score_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientEqualTo(String value) {
            addCriterion("endterm_score_coefficient =", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientNotEqualTo(String value) {
            addCriterion("endterm_score_coefficient <>", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientGreaterThan(String value) {
            addCriterion("endterm_score_coefficient >", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("endterm_score_coefficient >=", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientLessThan(String value) {
            addCriterion("endterm_score_coefficient <", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientLessThanOrEqualTo(String value) {
            addCriterion("endterm_score_coefficient <=", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientLike(String value) {
            addCriterion("endterm_score_coefficient like", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientNotLike(String value) {
            addCriterion("endterm_score_coefficient not like", value, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientIn(List<String> values) {
            addCriterion("endterm_score_coefficient in", values, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientNotIn(List<String> values) {
            addCriterion("endterm_score_coefficient not in", values, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientBetween(String value1, String value2) {
            addCriterion("endterm_score_coefficient between", value1, value2, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andEndtermScoreCoefficientNotBetween(String value1, String value2) {
            addCriterion("endterm_score_coefficient not between", value1, value2, "endtermScoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientIsNull() {
            addCriterion("score_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientIsNotNull() {
            addCriterion("score_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientEqualTo(String value) {
            addCriterion("score_coefficient =", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientNotEqualTo(String value) {
            addCriterion("score_coefficient <>", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientGreaterThan(String value) {
            addCriterion("score_coefficient >", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("score_coefficient >=", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientLessThan(String value) {
            addCriterion("score_coefficient <", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientLessThanOrEqualTo(String value) {
            addCriterion("score_coefficient <=", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientLike(String value) {
            addCriterion("score_coefficient like", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientNotLike(String value) {
            addCriterion("score_coefficient not like", value, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientIn(List<String> values) {
            addCriterion("score_coefficient in", values, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientNotIn(List<String> values) {
            addCriterion("score_coefficient not in", values, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientBetween(String value1, String value2) {
            addCriterion("score_coefficient between", value1, value2, "scoreCoefficient");
            return (Criteria) this;
        }

        public Criteria andScoreCoefficientNotBetween(String value1, String value2) {
            addCriterion("score_coefficient not between", value1, value2, "scoreCoefficient");
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

        public Criteria andTotalGradeIsNull() {
            addCriterion("total_grade is null");
            return (Criteria) this;
        }

        public Criteria andTotalGradeIsNotNull() {
            addCriterion("total_grade is not null");
            return (Criteria) this;
        }

        public Criteria andTotalGradeEqualTo(Double value) {
            addCriterion("total_grade =", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotEqualTo(Double value) {
            addCriterion("total_grade <>", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeGreaterThan(Double value) {
            addCriterion("total_grade >", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeGreaterThanOrEqualTo(Double value) {
            addCriterion("total_grade >=", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeLessThan(Double value) {
            addCriterion("total_grade <", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeLessThanOrEqualTo(Double value) {
            addCriterion("total_grade <=", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeIn(List<Double> values) {
            addCriterion("total_grade in", values, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotIn(List<Double> values) {
            addCriterion("total_grade not in", values, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeBetween(Double value1, Double value2) {
            addCriterion("total_grade between", value1, value2, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotBetween(Double value1, Double value2) {
            addCriterion("total_grade not between", value1, value2, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeIsNull() {
            addCriterion("regular_grade is null");
            return (Criteria) this;
        }

        public Criteria andRegularGradeIsNotNull() {
            addCriterion("regular_grade is not null");
            return (Criteria) this;
        }

        public Criteria andRegularGradeEqualTo(Double value) {
            addCriterion("regular_grade =", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeNotEqualTo(Double value) {
            addCriterion("regular_grade <>", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeGreaterThan(Double value) {
            addCriterion("regular_grade >", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeGreaterThanOrEqualTo(Double value) {
            addCriterion("regular_grade >=", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeLessThan(Double value) {
            addCriterion("regular_grade <", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeLessThanOrEqualTo(Double value) {
            addCriterion("regular_grade <=", value, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeIn(List<Double> values) {
            addCriterion("regular_grade in", values, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeNotIn(List<Double> values) {
            addCriterion("regular_grade not in", values, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeBetween(Double value1, Double value2) {
            addCriterion("regular_grade between", value1, value2, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andRegularGradeNotBetween(Double value1, Double value2) {
            addCriterion("regular_grade not between", value1, value2, "regularGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeIsNull() {
            addCriterion("midterm_grade is null");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeIsNotNull() {
            addCriterion("midterm_grade is not null");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeEqualTo(Double value) {
            addCriterion("midterm_grade =", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeNotEqualTo(Double value) {
            addCriterion("midterm_grade <>", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeGreaterThan(Double value) {
            addCriterion("midterm_grade >", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeGreaterThanOrEqualTo(Double value) {
            addCriterion("midterm_grade >=", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeLessThan(Double value) {
            addCriterion("midterm_grade <", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeLessThanOrEqualTo(Double value) {
            addCriterion("midterm_grade <=", value, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeIn(List<Double> values) {
            addCriterion("midterm_grade in", values, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeNotIn(List<Double> values) {
            addCriterion("midterm_grade not in", values, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeBetween(Double value1, Double value2) {
            addCriterion("midterm_grade between", value1, value2, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andMidtermGradeNotBetween(Double value1, Double value2) {
            addCriterion("midterm_grade not between", value1, value2, "midtermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeIsNull() {
            addCriterion("finalterm_grade is null");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeIsNotNull() {
            addCriterion("finalterm_grade is not null");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeEqualTo(Double value) {
            addCriterion("finalterm_grade =", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeNotEqualTo(Double value) {
            addCriterion("finalterm_grade <>", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeGreaterThan(Double value) {
            addCriterion("finalterm_grade >", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeGreaterThanOrEqualTo(Double value) {
            addCriterion("finalterm_grade >=", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeLessThan(Double value) {
            addCriterion("finalterm_grade <", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeLessThanOrEqualTo(Double value) {
            addCriterion("finalterm_grade <=", value, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeIn(List<Double> values) {
            addCriterion("finalterm_grade in", values, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeNotIn(List<Double> values) {
            addCriterion("finalterm_grade not in", values, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeBetween(Double value1, Double value2) {
            addCriterion("finalterm_grade between", value1, value2, "finaltermGrade");
            return (Criteria) this;
        }

        public Criteria andFinaltermGradeNotBetween(Double value1, Double value2) {
            addCriterion("finalterm_grade not between", value1, value2, "finaltermGrade");
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

        public Criteria andScoreTypeCodeIsNull() {
            addCriterion("score_type_code is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeIsNotNull() {
            addCriterion("score_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeEqualTo(String value) {
            addCriterion("score_type_code =", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeNotEqualTo(String value) {
            addCriterion("score_type_code <>", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeGreaterThan(String value) {
            addCriterion("score_type_code >", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("score_type_code >=", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeLessThan(String value) {
            addCriterion("score_type_code <", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("score_type_code <=", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeLike(String value) {
            addCriterion("score_type_code like", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeNotLike(String value) {
            addCriterion("score_type_code not like", value, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeIn(List<String> values) {
            addCriterion("score_type_code in", values, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeNotIn(List<String> values) {
            addCriterion("score_type_code not in", values, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeBetween(String value1, String value2) {
            addCriterion("score_type_code between", value1, value2, "scoreTypeCode");
            return (Criteria) this;
        }

        public Criteria andScoreTypeCodeNotBetween(String value1, String value2) {
            addCriterion("score_type_code not between", value1, value2, "scoreTypeCode");
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