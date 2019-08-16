package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrpCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpCourseExample() {
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

        public Criteria andUndergraduatePostgraduateFlagIsNull() {
            addCriterion("undergraduate_postgraduate_flag is null");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagIsNotNull() {
            addCriterion("undergraduate_postgraduate_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagEqualTo(String value) {
            addCriterion("undergraduate_postgraduate_flag =", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagNotEqualTo(String value) {
            addCriterion("undergraduate_postgraduate_flag <>", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagGreaterThan(String value) {
            addCriterion("undergraduate_postgraduate_flag >", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("undergraduate_postgraduate_flag >=", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagLessThan(String value) {
            addCriterion("undergraduate_postgraduate_flag <", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagLessThanOrEqualTo(String value) {
            addCriterion("undergraduate_postgraduate_flag <=", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagLike(String value) {
            addCriterion("undergraduate_postgraduate_flag like", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagNotLike(String value) {
            addCriterion("undergraduate_postgraduate_flag not like", value, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagIn(List<String> values) {
            addCriterion("undergraduate_postgraduate_flag in", values, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagNotIn(List<String> values) {
            addCriterion("undergraduate_postgraduate_flag not in", values, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagBetween(String value1, String value2) {
            addCriterion("undergraduate_postgraduate_flag between", value1, value2, "undergraduatePostgraduateFlag");
            return (Criteria) this;
        }

        public Criteria andUndergraduatePostgraduateFlagNotBetween(String value1, String value2) {
            addCriterion("undergraduate_postgraduate_flag not between", value1, value2, "undergraduatePostgraduateFlag");
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

        public Criteria andStandardPersonNumberIsNull() {
            addCriterion("standard_person_number is null");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberIsNotNull() {
            addCriterion("standard_person_number is not null");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberEqualTo(Integer value) {
            addCriterion("standard_person_number =", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberNotEqualTo(Integer value) {
            addCriterion("standard_person_number <>", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberGreaterThan(Integer value) {
            addCriterion("standard_person_number >", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("standard_person_number >=", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberLessThan(Integer value) {
            addCriterion("standard_person_number <", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberLessThanOrEqualTo(Integer value) {
            addCriterion("standard_person_number <=", value, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberIn(List<Integer> values) {
            addCriterion("standard_person_number in", values, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberNotIn(List<Integer> values) {
            addCriterion("standard_person_number not in", values, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberBetween(Integer value1, Integer value2) {
            addCriterion("standard_person_number between", value1, value2, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andStandardPersonNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("standard_person_number not between", value1, value2, "standardPersonNumber");
            return (Criteria) this;
        }

        public Criteria andReferenceBookIsNull() {
            addCriterion("reference_book is null");
            return (Criteria) this;
        }

        public Criteria andReferenceBookIsNotNull() {
            addCriterion("reference_book is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceBookEqualTo(String value) {
            addCriterion("reference_book =", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookNotEqualTo(String value) {
            addCriterion("reference_book <>", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookGreaterThan(String value) {
            addCriterion("reference_book >", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookGreaterThanOrEqualTo(String value) {
            addCriterion("reference_book >=", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookLessThan(String value) {
            addCriterion("reference_book <", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookLessThanOrEqualTo(String value) {
            addCriterion("reference_book <=", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookLike(String value) {
            addCriterion("reference_book like", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookNotLike(String value) {
            addCriterion("reference_book not like", value, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookIn(List<String> values) {
            addCriterion("reference_book in", values, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookNotIn(List<String> values) {
            addCriterion("reference_book not in", values, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookBetween(String value1, String value2) {
            addCriterion("reference_book between", value1, value2, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andReferenceBookNotBetween(String value1, String value2) {
            addCriterion("reference_book not between", value1, value2, "referenceBook");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIsNull() {
            addCriterion("teaching_material is null");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIsNotNull() {
            addCriterion("teaching_material is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialEqualTo(String value) {
            addCriterion("teaching_material =", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotEqualTo(String value) {
            addCriterion("teaching_material <>", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialGreaterThan(String value) {
            addCriterion("teaching_material >", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("teaching_material >=", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLessThan(String value) {
            addCriterion("teaching_material <", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLessThanOrEqualTo(String value) {
            addCriterion("teaching_material <=", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLike(String value) {
            addCriterion("teaching_material like", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotLike(String value) {
            addCriterion("teaching_material not like", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIn(List<String> values) {
            addCriterion("teaching_material in", values, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotIn(List<String> values) {
            addCriterion("teaching_material not in", values, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialBetween(String value1, String value2) {
            addCriterion("teaching_material between", value1, value2, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotBetween(String value1, String value2) {
            addCriterion("teaching_material not between", value1, value2, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingIsNull() {
            addCriterion("total_hour_of_teaching is null");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingIsNotNull() {
            addCriterion("total_hour_of_teaching is not null");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingEqualTo(Integer value) {
            addCriterion("total_hour_of_teaching =", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingNotEqualTo(Integer value) {
            addCriterion("total_hour_of_teaching <>", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingGreaterThan(Integer value) {
            addCriterion("total_hour_of_teaching >", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_hour_of_teaching >=", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingLessThan(Integer value) {
            addCriterion("total_hour_of_teaching <", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingLessThanOrEqualTo(Integer value) {
            addCriterion("total_hour_of_teaching <=", value, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingIn(List<Integer> values) {
            addCriterion("total_hour_of_teaching in", values, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingNotIn(List<Integer> values) {
            addCriterion("total_hour_of_teaching not in", values, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingBetween(Integer value1, Integer value2) {
            addCriterion("total_hour_of_teaching between", value1, value2, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andTotalHourOfTeachingNotBetween(Integer value1, Integer value2) {
            addCriterion("total_hour_of_teaching not between", value1, value2, "totalHourOfTeaching");
            return (Criteria) this;
        }

        public Criteria andJsmIsNull() {
            addCriterion("jsm is null");
            return (Criteria) this;
        }

        public Criteria andJsmIsNotNull() {
            addCriterion("jsm is not null");
            return (Criteria) this;
        }

        public Criteria andJsmEqualTo(String value) {
            addCriterion("jsm =", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmNotEqualTo(String value) {
            addCriterion("jsm <>", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmGreaterThan(String value) {
            addCriterion("jsm >", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmGreaterThanOrEqualTo(String value) {
            addCriterion("jsm >=", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmLessThan(String value) {
            addCriterion("jsm <", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmLessThanOrEqualTo(String value) {
            addCriterion("jsm <=", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmLike(String value) {
            addCriterion("jsm like", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmNotLike(String value) {
            addCriterion("jsm not like", value, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmIn(List<String> values) {
            addCriterion("jsm in", values, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmNotIn(List<String> values) {
            addCriterion("jsm not in", values, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmBetween(String value1, String value2) {
            addCriterion("jsm between", value1, value2, "jsm");
            return (Criteria) this;
        }

        public Criteria andJsmNotBetween(String value1, String value2) {
            addCriterion("jsm not between", value1, value2, "jsm");
            return (Criteria) this;
        }

        public Criteria andendDateIsNull() {
            addCriterion("end_dae is null");
            return (Criteria) this;
        }

        public Criteria andendDateIsNotNull() {
            addCriterion("end_dae is not null");
            return (Criteria) this;
        }

        public Criteria andendDateEqualTo(String value) {
            addCriterion("end_dae =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateNotEqualTo(String value) {
            addCriterion("end_dae <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateGreaterThan(String value) {
            addCriterion("end_dae >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_dae >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateLessThan(String value) {
            addCriterion("end_dae <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateLessThanOrEqualTo(String value) {
            addCriterion("end_dae <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateLike(String value) {
            addCriterion("end_dae like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateNotLike(String value) {
            addCriterion("end_dae not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateIn(List<String> values) {
            addCriterion("end_dae in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateNotIn(List<String> values) {
            addCriterion("end_dae not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateBetween(String value1, String value2) {
            addCriterion("end_dae between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andendDateNotBetween(String value1, String value2) {
            addCriterion("end_dae not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andJxdgIsNull() {
            addCriterion("jxdg is null");
            return (Criteria) this;
        }

        public Criteria andJxdgIsNotNull() {
            addCriterion("jxdg is not null");
            return (Criteria) this;
        }

        public Criteria andJxdgEqualTo(String value) {
            addCriterion("jxdg =", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgNotEqualTo(String value) {
            addCriterion("jxdg <>", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgGreaterThan(String value) {
            addCriterion("jxdg >", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgGreaterThanOrEqualTo(String value) {
            addCriterion("jxdg >=", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgLessThan(String value) {
            addCriterion("jxdg <", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgLessThanOrEqualTo(String value) {
            addCriterion("jxdg <=", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgLike(String value) {
            addCriterion("jxdg like", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgNotLike(String value) {
            addCriterion("jxdg not like", value, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgIn(List<String> values) {
            addCriterion("jxdg in", values, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgNotIn(List<String> values) {
            addCriterion("jxdg not in", values, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgBetween(String value1, String value2) {
            addCriterion("jxdg between", value1, value2, "jxdg");
            return (Criteria) this;
        }

        public Criteria andJxdgNotBetween(String value1, String value2) {
            addCriterion("jxdg not between", value1, value2, "jxdg");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodIsNull() {
            addCriterion("teaching_method is null");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodIsNotNull() {
            addCriterion("teaching_method is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodEqualTo(String value) {
            addCriterion("teaching_method =", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodNotEqualTo(String value) {
            addCriterion("teaching_method <>", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodGreaterThan(String value) {
            addCriterion("teaching_method >", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodGreaterThanOrEqualTo(String value) {
            addCriterion("teaching_method >=", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodLessThan(String value) {
            addCriterion("teaching_method <", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodLessThanOrEqualTo(String value) {
            addCriterion("teaching_method <=", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodLike(String value) {
            addCriterion("teaching_method like", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodNotLike(String value) {
            addCriterion("teaching_method not like", value, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodIn(List<String> values) {
            addCriterion("teaching_method in", values, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodNotIn(List<String> values) {
            addCriterion("teaching_method not in", values, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodBetween(String value1, String value2) {
            addCriterion("teaching_method between", value1, value2, "teachingMethod");
            return (Criteria) this;
        }

        public Criteria andTeachingMethodNotBetween(String value1, String value2) {
            addCriterion("teaching_method not between", value1, value2, "teachingMethod");
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

        public Criteria andBasicNameOfCourseIsNull() {
            addCriterion("basic_name_of_course is null");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseIsNotNull() {
            addCriterion("basic_name_of_course is not null");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseEqualTo(String value) {
            addCriterion("basic_name_of_course =", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseNotEqualTo(String value) {
            addCriterion("basic_name_of_course <>", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseGreaterThan(String value) {
            addCriterion("basic_name_of_course >", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseGreaterThanOrEqualTo(String value) {
            addCriterion("basic_name_of_course >=", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseLessThan(String value) {
            addCriterion("basic_name_of_course <", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseLessThanOrEqualTo(String value) {
            addCriterion("basic_name_of_course <=", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseLike(String value) {
            addCriterion("basic_name_of_course like", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseNotLike(String value) {
            addCriterion("basic_name_of_course not like", value, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseIn(List<String> values) {
            addCriterion("basic_name_of_course in", values, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseNotIn(List<String> values) {
            addCriterion("basic_name_of_course not in", values, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseBetween(String value1, String value2) {
            addCriterion("basic_name_of_course between", value1, value2, "basicNameOfCourse");
            return (Criteria) this;
        }

        public Criteria andBasicNameOfCourseNotBetween(String value1, String value2) {
            addCriterion("basic_name_of_course not between", value1, value2, "basicNameOfCourse");
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

        public Criteria andCourseDescriptionIsNull() {
            addCriterion("course_description is null");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionIsNotNull() {
            addCriterion("course_description is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionEqualTo(String value) {
            addCriterion("course_description =", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionNotEqualTo(String value) {
            addCriterion("course_description <>", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionGreaterThan(String value) {
            addCriterion("course_description >", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("course_description >=", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionLessThan(String value) {
            addCriterion("course_description <", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionLessThanOrEqualTo(String value) {
            addCriterion("course_description <=", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionLike(String value) {
            addCriterion("course_description like", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionNotLike(String value) {
            addCriterion("course_description not like", value, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionIn(List<String> values) {
            addCriterion("course_description in", values, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionNotIn(List<String> values) {
            addCriterion("course_description not in", values, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionBetween(String value1, String value2) {
            addCriterion("course_description between", value1, value2, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseDescriptionNotBetween(String value1, String value2) {
            addCriterion("course_description not between", value1, value2, "courseDescription");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIsNull() {
            addCriterion("course_status is null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIsNotNull() {
            addCriterion("course_status is not null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusEqualTo(String value) {
            addCriterion("course_status =", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotEqualTo(String value) {
            addCriterion("course_status <>", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusGreaterThan(String value) {
            addCriterion("course_status >", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusGreaterThanOrEqualTo(String value) {
            addCriterion("course_status >=", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusLessThan(String value) {
            addCriterion("course_status <", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusLessThanOrEqualTo(String value) {
            addCriterion("course_status <=", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusLike(String value) {
            addCriterion("course_status like", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotLike(String value) {
            addCriterion("course_status not like", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIn(List<String> values) {
            addCriterion("course_status in", values, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotIn(List<String> values) {
            addCriterion("course_status not in", values, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusBetween(String value1, String value2) {
            addCriterion("course_status between", value1, value2, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotBetween(String value1, String value2) {
            addCriterion("course_status not between", value1, value2, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andSemesterIsNull() {
            addCriterion("semester is null");
            return (Criteria) this;
        }

        public Criteria andSemesterIsNotNull() {
            addCriterion("semester is not null");
            return (Criteria) this;
        }

        public Criteria andSemesterEqualTo(String value) {
            addCriterion("semester =", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterNotEqualTo(String value) {
            addCriterion("semester <>", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterGreaterThan(String value) {
            addCriterion("semester >", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterGreaterThanOrEqualTo(String value) {
            addCriterion("semester >=", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterLessThan(String value) {
            addCriterion("semester <", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterLessThanOrEqualTo(String value) {
            addCriterion("semester <=", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterLike(String value) {
            addCriterion("semester like", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterNotLike(String value) {
            addCriterion("semester not like", value, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterIn(List<String> values) {
            addCriterion("semester in", values, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterNotIn(List<String> values) {
            addCriterion("semester not in", values, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterBetween(String value1, String value2) {
            addCriterion("semester between", value1, value2, "semester");
            return (Criteria) this;
        }

        public Criteria andSemesterNotBetween(String value1, String value2) {
            addCriterion("semester not between", value1, value2, "semester");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassIsNull() {
            addCriterion("total_hour_in_class is null");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassIsNotNull() {
            addCriterion("total_hour_in_class is not null");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassEqualTo(Integer value) {
            addCriterion("total_hour_in_class =", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassNotEqualTo(Integer value) {
            addCriterion("total_hour_in_class <>", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassGreaterThan(Integer value) {
            addCriterion("total_hour_in_class >", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_hour_in_class >=", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassLessThan(Integer value) {
            addCriterion("total_hour_in_class <", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassLessThanOrEqualTo(Integer value) {
            addCriterion("total_hour_in_class <=", value, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassIn(List<Integer> values) {
            addCriterion("total_hour_in_class in", values, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassNotIn(List<Integer> values) {
            addCriterion("total_hour_in_class not in", values, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassBetween(Integer value1, Integer value2) {
            addCriterion("total_hour_in_class between", value1, value2, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andTotalHourInClassNotBetween(Integer value1, Integer value2) {
            addCriterion("total_hour_in_class not between", value1, value2, "totalHourInClass");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeIsNull() {
            addCriterion("course_fee_class_code is null");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeIsNotNull() {
            addCriterion("course_fee_class_code is not null");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeEqualTo(String value) {
            addCriterion("course_fee_class_code =", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeNotEqualTo(String value) {
            addCriterion("course_fee_class_code <>", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeGreaterThan(String value) {
            addCriterion("course_fee_class_code >", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeGreaterThanOrEqualTo(String value) {
            addCriterion("course_fee_class_code >=", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeLessThan(String value) {
            addCriterion("course_fee_class_code <", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeLessThanOrEqualTo(String value) {
            addCriterion("course_fee_class_code <=", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeLike(String value) {
            addCriterion("course_fee_class_code like", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeNotLike(String value) {
            addCriterion("course_fee_class_code not like", value, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeIn(List<String> values) {
            addCriterion("course_fee_class_code in", values, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeNotIn(List<String> values) {
            addCriterion("course_fee_class_code not in", values, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeBetween(String value1, String value2) {
            addCriterion("course_fee_class_code between", value1, value2, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andCourseFeeClassCodeNotBetween(String value1, String value2) {
            addCriterion("course_fee_class_code not between", value1, value2, "courseFeeClassCode");
            return (Criteria) this;
        }

        public Criteria andExaminationNameIsNull() {
            addCriterion("examination_name is null");
            return (Criteria) this;
        }

        public Criteria andExaminationNameIsNotNull() {
            addCriterion("examination_name is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationNameEqualTo(String value) {
            addCriterion("examination_name =", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameNotEqualTo(String value) {
            addCriterion("examination_name <>", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameGreaterThan(String value) {
            addCriterion("examination_name >", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameGreaterThanOrEqualTo(String value) {
            addCriterion("examination_name >=", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameLessThan(String value) {
            addCriterion("examination_name <", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameLessThanOrEqualTo(String value) {
            addCriterion("examination_name <=", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameLike(String value) {
            addCriterion("examination_name like", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameNotLike(String value) {
            addCriterion("examination_name not like", value, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameIn(List<String> values) {
            addCriterion("examination_name in", values, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameNotIn(List<String> values) {
            addCriterion("examination_name not in", values, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameBetween(String value1, String value2) {
            addCriterion("examination_name between", value1, value2, "examinationName");
            return (Criteria) this;
        }

        public Criteria andExaminationNameNotBetween(String value1, String value2) {
            addCriterion("examination_name not between", value1, value2, "examinationName");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditIsNull() {
            addCriterion("extracurricular_credit is null");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditIsNotNull() {
            addCriterion("extracurricular_credit is not null");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditEqualTo(Integer value) {
            addCriterion("extracurricular_credit =", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditNotEqualTo(Integer value) {
            addCriterion("extracurricular_credit <>", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditGreaterThan(Integer value) {
            addCriterion("extracurricular_credit >", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("extracurricular_credit >=", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditLessThan(Integer value) {
            addCriterion("extracurricular_credit <", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditLessThanOrEqualTo(Integer value) {
            addCriterion("extracurricular_credit <=", value, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditIn(List<Integer> values) {
            addCriterion("extracurricular_credit in", values, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditNotIn(List<Integer> values) {
            addCriterion("extracurricular_credit not in", values, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditBetween(Integer value1, Integer value2) {
            addCriterion("extracurricular_credit between", value1, value2, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("extracurricular_credit not between", value1, value2, "extracurricularCredit");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourIsNull() {
            addCriterion("extracurricular_total_hour is null");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourIsNotNull() {
            addCriterion("extracurricular_total_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourEqualTo(Integer value) {
            addCriterion("extracurricular_total_hour =", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourNotEqualTo(Integer value) {
            addCriterion("extracurricular_total_hour <>", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourGreaterThan(Integer value) {
            addCriterion("extracurricular_total_hour >", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("extracurricular_total_hour >=", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourLessThan(Integer value) {
            addCriterion("extracurricular_total_hour <", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourLessThanOrEqualTo(Integer value) {
            addCriterion("extracurricular_total_hour <=", value, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourIn(List<Integer> values) {
            addCriterion("extracurricular_total_hour in", values, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourNotIn(List<Integer> values) {
            addCriterion("extracurricular_total_hour not in", values, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourBetween(Integer value1, Integer value2) {
            addCriterion("extracurricular_total_hour between", value1, value2, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andExtracurricularTotalHourNotBetween(Integer value1, Integer value2) {
            addCriterion("extracurricular_total_hour not between", value1, value2, "extracurricularTotalHour");
            return (Criteria) this;
        }

        public Criteria andContentAbstractIsNull() {
            addCriterion("content_abstract is null");
            return (Criteria) this;
        }

        public Criteria andContentAbstractIsNotNull() {
            addCriterion("content_abstract is not null");
            return (Criteria) this;
        }

        public Criteria andContentAbstractEqualTo(String value) {
            addCriterion("content_abstract =", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractNotEqualTo(String value) {
            addCriterion("content_abstract <>", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractGreaterThan(String value) {
            addCriterion("content_abstract >", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractGreaterThanOrEqualTo(String value) {
            addCriterion("content_abstract >=", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractLessThan(String value) {
            addCriterion("content_abstract <", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractLessThanOrEqualTo(String value) {
            addCriterion("content_abstract <=", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractLike(String value) {
            addCriterion("content_abstract like", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractNotLike(String value) {
            addCriterion("content_abstract not like", value, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractIn(List<String> values) {
            addCriterion("content_abstract in", values, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractNotIn(List<String> values) {
            addCriterion("content_abstract not in", values, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractBetween(String value1, String value2) {
            addCriterion("content_abstract between", value1, value2, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andContentAbstractNotBetween(String value1, String value2) {
            addCriterion("content_abstract not between", value1, value2, "contentAbstract");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourIsNull() {
            addCriterion("on_computer_hour is null");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourIsNotNull() {
            addCriterion("on_computer_hour is not null");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourEqualTo(Integer value) {
            addCriterion("on_computer_hour =", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourNotEqualTo(Integer value) {
            addCriterion("on_computer_hour <>", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourGreaterThan(Integer value) {
            addCriterion("on_computer_hour >", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("on_computer_hour >=", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourLessThan(Integer value) {
            addCriterion("on_computer_hour <", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourLessThanOrEqualTo(Integer value) {
            addCriterion("on_computer_hour <=", value, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourIn(List<Integer> values) {
            addCriterion("on_computer_hour in", values, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourNotIn(List<Integer> values) {
            addCriterion("on_computer_hour not in", values, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourBetween(Integer value1, Integer value2) {
            addCriterion("on_computer_hour between", value1, value2, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andOnComputerHourNotBetween(Integer value1, Integer value2) {
            addCriterion("on_computer_hour not between", value1, value2, "onComputerHour");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekIsNull() {
            addCriterion("practice_week is null");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekIsNotNull() {
            addCriterion("practice_week is not null");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekEqualTo(Integer value) {
            addCriterion("practice_week =", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekNotEqualTo(Integer value) {
            addCriterion("practice_week <>", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekGreaterThan(Integer value) {
            addCriterion("practice_week >", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("practice_week >=", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekLessThan(Integer value) {
            addCriterion("practice_week <", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekLessThanOrEqualTo(Integer value) {
            addCriterion("practice_week <=", value, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekIn(List<Integer> values) {
            addCriterion("practice_week in", values, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekNotIn(List<Integer> values) {
            addCriterion("practice_week not in", values, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekBetween(Integer value1, Integer value2) {
            addCriterion("practice_week between", value1, value2, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andPracticeWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("practice_week not between", value1, value2, "practiceWeek");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeIsNull() {
            addCriterion("number_coeffcient_code is null");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeIsNotNull() {
            addCriterion("number_coeffcient_code is not null");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeEqualTo(String value) {
            addCriterion("number_coeffcient_code =", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeNotEqualTo(String value) {
            addCriterion("number_coeffcient_code <>", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeGreaterThan(String value) {
            addCriterion("number_coeffcient_code >", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeGreaterThanOrEqualTo(String value) {
            addCriterion("number_coeffcient_code >=", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeLessThan(String value) {
            addCriterion("number_coeffcient_code <", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeLessThanOrEqualTo(String value) {
            addCriterion("number_coeffcient_code <=", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeLike(String value) {
            addCriterion("number_coeffcient_code like", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeNotLike(String value) {
            addCriterion("number_coeffcient_code not like", value, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeIn(List<String> values) {
            addCriterion("number_coeffcient_code in", values, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeNotIn(List<String> values) {
            addCriterion("number_coeffcient_code not in", values, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeBetween(String value1, String value2) {
            addCriterion("number_coeffcient_code between", value1, value2, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andNumberCoeffcientCodeNotBetween(String value1, String value2) {
            addCriterion("number_coeffcient_code not between", value1, value2, "numberCoeffcientCode");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameIsNull() {
            addCriterion("charge_category_name is null");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameIsNotNull() {
            addCriterion("charge_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameEqualTo(String value) {
            addCriterion("charge_category_name =", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameNotEqualTo(String value) {
            addCriterion("charge_category_name <>", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameGreaterThan(String value) {
            addCriterion("charge_category_name >", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("charge_category_name >=", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameLessThan(String value) {
            addCriterion("charge_category_name <", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("charge_category_name <=", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameLike(String value) {
            addCriterion("charge_category_name like", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameNotLike(String value) {
            addCriterion("charge_category_name not like", value, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameIn(List<String> values) {
            addCriterion("charge_category_name in", values, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameNotIn(List<String> values) {
            addCriterion("charge_category_name not in", values, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameBetween(String value1, String value2) {
            addCriterion("charge_category_name between", value1, value2, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeCategoryNameNotBetween(String value1, String value2) {
            addCriterion("charge_category_name not between", value1, value2, "chargeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourIsNull() {
            addCriterion("design_total_hour is null");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourIsNotNull() {
            addCriterion("design_total_hour is not null");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourEqualTo(Integer value) {
            addCriterion("design_total_hour =", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourNotEqualTo(Integer value) {
            addCriterion("design_total_hour <>", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourGreaterThan(Integer value) {
            addCriterion("design_total_hour >", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("design_total_hour >=", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourLessThan(Integer value) {
            addCriterion("design_total_hour <", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourLessThanOrEqualTo(Integer value) {
            addCriterion("design_total_hour <=", value, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourIn(List<Integer> values) {
            addCriterion("design_total_hour in", values, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourNotIn(List<Integer> values) {
            addCriterion("design_total_hour not in", values, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourBetween(Integer value1, Integer value2) {
            addCriterion("design_total_hour between", value1, value2, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignTotalHourNotBetween(Integer value1, Integer value2) {
            addCriterion("design_total_hour not between", value1, value2, "designTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourIsNull() {
            addCriterion("design_homework_total_hour is null");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourIsNotNull() {
            addCriterion("design_homework_total_hour is not null");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourEqualTo(Integer value) {
            addCriterion("design_homework_total_hour =", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourNotEqualTo(Integer value) {
            addCriterion("design_homework_total_hour <>", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourGreaterThan(Integer value) {
            addCriterion("design_homework_total_hour >", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("design_homework_total_hour >=", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourLessThan(Integer value) {
            addCriterion("design_homework_total_hour <", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourLessThanOrEqualTo(Integer value) {
            addCriterion("design_homework_total_hour <=", value, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourIn(List<Integer> values) {
            addCriterion("design_homework_total_hour in", values, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourNotIn(List<Integer> values) {
            addCriterion("design_homework_total_hour not in", values, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourBetween(Integer value1, Integer value2) {
            addCriterion("design_homework_total_hour between", value1, value2, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andDesignHomeworkTotalHourNotBetween(Integer value1, Integer value2) {
            addCriterion("design_homework_total_hour not between", value1, value2, "designHomeworkTotalHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIsNull() {
            addCriterion("experiment_hour is null");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIsNotNull() {
            addCriterion("experiment_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentHourEqualTo(Integer value) {
            addCriterion("experiment_hour =", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotEqualTo(Integer value) {
            addCriterion("experiment_hour <>", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourGreaterThan(Integer value) {
            addCriterion("experiment_hour >", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("experiment_hour >=", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourLessThan(Integer value) {
            addCriterion("experiment_hour <", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourLessThanOrEqualTo(Integer value) {
            addCriterion("experiment_hour <=", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIn(List<Integer> values) {
            addCriterion("experiment_hour in", values, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotIn(List<Integer> values) {
            addCriterion("experiment_hour not in", values, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourBetween(Integer value1, Integer value2) {
            addCriterion("experiment_hour between", value1, value2, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotBetween(Integer value1, Integer value2) {
            addCriterion("experiment_hour not between", value1, value2, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffIsNull() {
            addCriterion("teaching_staff is null");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffIsNotNull() {
            addCriterion("teaching_staff is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffEqualTo(String value) {
            addCriterion("teaching_staff =", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffNotEqualTo(String value) {
            addCriterion("teaching_staff <>", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffGreaterThan(String value) {
            addCriterion("teaching_staff >", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffGreaterThanOrEqualTo(String value) {
            addCriterion("teaching_staff >=", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffLessThan(String value) {
            addCriterion("teaching_staff <", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffLessThanOrEqualTo(String value) {
            addCriterion("teaching_staff <=", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffLike(String value) {
            addCriterion("teaching_staff like", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffNotLike(String value) {
            addCriterion("teaching_staff not like", value, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffIn(List<String> values) {
            addCriterion("teaching_staff in", values, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffNotIn(List<String> values) {
            addCriterion("teaching_staff not in", values, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffBetween(String value1, String value2) {
            addCriterion("teaching_staff between", value1, value2, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andTeachingStaffNotBetween(String value1, String value2) {
            addCriterion("teaching_staff not between", value1, value2, "teachingStaff");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourIsNull() {
            addCriterion("discuss_counseling_total_hour is null");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourIsNotNull() {
            addCriterion("discuss_counseling_total_hour is not null");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourEqualTo(Integer value) {
            addCriterion("discuss_counseling_total_hour =", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourNotEqualTo(Integer value) {
            addCriterion("discuss_counseling_total_hour <>", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourGreaterThan(Integer value) {
            addCriterion("discuss_counseling_total_hour >", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("discuss_counseling_total_hour >=", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourLessThan(Integer value) {
            addCriterion("discuss_counseling_total_hour <", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourLessThanOrEqualTo(Integer value) {
            addCriterion("discuss_counseling_total_hour <=", value, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourIn(List<Integer> values) {
            addCriterion("discuss_counseling_total_hour in", values, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourNotIn(List<Integer> values) {
            addCriterion("discuss_counseling_total_hour not in", values, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourBetween(Integer value1, Integer value2) {
            addCriterion("discuss_counseling_total_hour between", value1, value2, "discussCounselingTotalHour");
            return (Criteria) this;
        }

        public Criteria andDiscussCounselingTotalHourNotBetween(Integer value1, Integer value2) {
            addCriterion("discuss_counseling_total_hour not between", value1, value2, "discussCounselingTotalHour");
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

        public Criteria andSubjectCategoryNumberIsNull() {
            addCriterion("subject_category_number is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberIsNotNull() {
            addCriterion("subject_category_number is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberEqualTo(String value) {
            addCriterion("subject_category_number =", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberNotEqualTo(String value) {
            addCriterion("subject_category_number <>", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberGreaterThan(String value) {
            addCriterion("subject_category_number >", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberGreaterThanOrEqualTo(String value) {
            addCriterion("subject_category_number >=", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberLessThan(String value) {
            addCriterion("subject_category_number <", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberLessThanOrEqualTo(String value) {
            addCriterion("subject_category_number <=", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberLike(String value) {
            addCriterion("subject_category_number like", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberNotLike(String value) {
            addCriterion("subject_category_number not like", value, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberIn(List<String> values) {
            addCriterion("subject_category_number in", values, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberNotIn(List<String> values) {
            addCriterion("subject_category_number not in", values, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberBetween(String value1, String value2) {
            addCriterion("subject_category_number between", value1, value2, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNumberNotBetween(String value1, String value2) {
            addCriterion("subject_category_number not between", value1, value2, "subjectCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameIsNull() {
            addCriterion("subject_category_name is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameIsNotNull() {
            addCriterion("subject_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameEqualTo(String value) {
            addCriterion("subject_category_name =", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameNotEqualTo(String value) {
            addCriterion("subject_category_name <>", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameGreaterThan(String value) {
            addCriterion("subject_category_name >", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("subject_category_name >=", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameLessThan(String value) {
            addCriterion("subject_category_name <", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("subject_category_name <=", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameLike(String value) {
            addCriterion("subject_category_name like", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameNotLike(String value) {
            addCriterion("subject_category_name not like", value, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameIn(List<String> values) {
            addCriterion("subject_category_name in", values, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameNotIn(List<String> values) {
            addCriterion("subject_category_name not in", values, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameBetween(String value1, String value2) {
            addCriterion("subject_category_name between", value1, value2, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andSubjectCategoryNameNotBetween(String value1, String value2) {
            addCriterion("subject_category_name not between", value1, value2, "subjectCategoryName");
            return (Criteria) this;
        }

        public Criteria andCampusNumberIsNull() {
            addCriterion("campus_number is null");
            return (Criteria) this;
        }

        public Criteria andCampusNumberIsNotNull() {
            addCriterion("campus_number is not null");
            return (Criteria) this;
        }

        public Criteria andCampusNumberEqualTo(String value) {
            addCriterion("campus_number =", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberNotEqualTo(String value) {
            addCriterion("campus_number <>", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberGreaterThan(String value) {
            addCriterion("campus_number >", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberGreaterThanOrEqualTo(String value) {
            addCriterion("campus_number >=", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberLessThan(String value) {
            addCriterion("campus_number <", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberLessThanOrEqualTo(String value) {
            addCriterion("campus_number <=", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberLike(String value) {
            addCriterion("campus_number like", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberNotLike(String value) {
            addCriterion("campus_number not like", value, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberIn(List<String> values) {
            addCriterion("campus_number in", values, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberNotIn(List<String> values) {
            addCriterion("campus_number not in", values, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberBetween(String value1, String value2) {
            addCriterion("campus_number between", value1, value2, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNumberNotBetween(String value1, String value2) {
            addCriterion("campus_number not between", value1, value2, "campusNumber");
            return (Criteria) this;
        }

        public Criteria andCampusNameIsNull() {
            addCriterion("campus_name is null");
            return (Criteria) this;
        }

        public Criteria andCampusNameIsNotNull() {
            addCriterion("campus_name is not null");
            return (Criteria) this;
        }

        public Criteria andCampusNameEqualTo(String value) {
            addCriterion("campus_name =", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotEqualTo(String value) {
            addCriterion("campus_name <>", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThan(String value) {
            addCriterion("campus_name >", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThanOrEqualTo(String value) {
            addCriterion("campus_name >=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThan(String value) {
            addCriterion("campus_name <", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThanOrEqualTo(String value) {
            addCriterion("campus_name <=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLike(String value) {
            addCriterion("campus_name like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotLike(String value) {
            addCriterion("campus_name not like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameIn(List<String> values) {
            addCriterion("campus_name in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotIn(List<String> values) {
            addCriterion("campus_name not in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameBetween(String value1, String value2) {
            addCriterion("campus_name between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotBetween(String value1, String value2) {
            addCriterion("campus_name not between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andClassHourIsNull() {
            addCriterion("class_hour is null");
            return (Criteria) this;
        }

        public Criteria andClassHourIsNotNull() {
            addCriterion("class_hour is not null");
            return (Criteria) this;
        }

        public Criteria andClassHourEqualTo(Integer value) {
            addCriterion("class_hour =", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotEqualTo(Integer value) {
            addCriterion("class_hour <>", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourGreaterThan(Integer value) {
            addCriterion("class_hour >", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_hour >=", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourLessThan(Integer value) {
            addCriterion("class_hour <", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourLessThanOrEqualTo(Integer value) {
            addCriterion("class_hour <=", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourIn(List<Integer> values) {
            addCriterion("class_hour in", values, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotIn(List<Integer> values) {
            addCriterion("class_hour not in", values, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourBetween(Integer value1, Integer value2) {
            addCriterion("class_hour between", value1, value2, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotBetween(Integer value1, Integer value2) {
            addCriterion("class_hour not between", value1, value2, "classHour");
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

        public Criteria andXxkchIsNull() {
            addCriterion("xxkch is null");
            return (Criteria) this;
        }

        public Criteria andXxkchIsNotNull() {
            addCriterion("xxkch is not null");
            return (Criteria) this;
        }

        public Criteria andXxkchEqualTo(String value) {
            addCriterion("xxkch =", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchNotEqualTo(String value) {
            addCriterion("xxkch <>", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchGreaterThan(String value) {
            addCriterion("xxkch >", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchGreaterThanOrEqualTo(String value) {
            addCriterion("xxkch >=", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchLessThan(String value) {
            addCriterion("xxkch <", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchLessThanOrEqualTo(String value) {
            addCriterion("xxkch <=", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchLike(String value) {
            addCriterion("xxkch like", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchNotLike(String value) {
            addCriterion("xxkch not like", value, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchIn(List<String> values) {
            addCriterion("xxkch in", values, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchNotIn(List<String> values) {
            addCriterion("xxkch not in", values, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchBetween(String value1, String value2) {
            addCriterion("xxkch between", value1, value2, "xxkch");
            return (Criteria) this;
        }

        public Criteria andXxkchNotBetween(String value1, String value2) {
            addCriterion("xxkch not between", value1, value2, "xxkch");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusIsNull() {
            addCriterion("english_syllabus is null");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusIsNotNull() {
            addCriterion("english_syllabus is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusEqualTo(String value) {
            addCriterion("english_syllabus =", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusNotEqualTo(String value) {
            addCriterion("english_syllabus <>", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusGreaterThan(String value) {
            addCriterion("english_syllabus >", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusGreaterThanOrEqualTo(String value) {
            addCriterion("english_syllabus >=", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusLessThan(String value) {
            addCriterion("english_syllabus <", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusLessThanOrEqualTo(String value) {
            addCriterion("english_syllabus <=", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusLike(String value) {
            addCriterion("english_syllabus like", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusNotLike(String value) {
            addCriterion("english_syllabus not like", value, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusIn(List<String> values) {
            addCriterion("english_syllabus in", values, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusNotIn(List<String> values) {
            addCriterion("english_syllabus not in", values, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusBetween(String value1, String value2) {
            addCriterion("english_syllabus between", value1, value2, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishSyllabusNotBetween(String value1, String value2) {
            addCriterion("english_syllabus not between", value1, value2, "englishSyllabus");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameIsNull() {
            addCriterion("english_course_name is null");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameIsNotNull() {
            addCriterion("english_course_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameEqualTo(String value) {
            addCriterion("english_course_name =", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameNotEqualTo(String value) {
            addCriterion("english_course_name <>", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameGreaterThan(String value) {
            addCriterion("english_course_name >", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("english_course_name >=", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameLessThan(String value) {
            addCriterion("english_course_name <", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameLessThanOrEqualTo(String value) {
            addCriterion("english_course_name <=", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameLike(String value) {
            addCriterion("english_course_name like", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameNotLike(String value) {
            addCriterion("english_course_name not like", value, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameIn(List<String> values) {
            addCriterion("english_course_name in", values, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameNotIn(List<String> values) {
            addCriterion("english_course_name not in", values, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameBetween(String value1, String value2) {
            addCriterion("english_course_name between", value1, value2, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishCourseNameNotBetween(String value1, String value2) {
            addCriterion("english_course_name not between", value1, value2, "englishCourseName");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefIsNull() {
            addCriterion("english_content_brief is null");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefIsNotNull() {
            addCriterion("english_content_brief is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefEqualTo(String value) {
            addCriterion("english_content_brief =", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefNotEqualTo(String value) {
            addCriterion("english_content_brief <>", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefGreaterThan(String value) {
            addCriterion("english_content_brief >", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefGreaterThanOrEqualTo(String value) {
            addCriterion("english_content_brief >=", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefLessThan(String value) {
            addCriterion("english_content_brief <", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefLessThanOrEqualTo(String value) {
            addCriterion("english_content_brief <=", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefLike(String value) {
            addCriterion("english_content_brief like", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefNotLike(String value) {
            addCriterion("english_content_brief not like", value, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefIn(List<String> values) {
            addCriterion("english_content_brief in", values, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefNotIn(List<String> values) {
            addCriterion("english_content_brief not in", values, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefBetween(String value1, String value2) {
            addCriterion("english_content_brief between", value1, value2, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andEnglishContentBriefNotBetween(String value1, String value2) {
            addCriterion("english_content_brief not between", value1, value2, "englishContentBrief");
            return (Criteria) this;
        }

        public Criteria andCourseTargetIsNull() {
            addCriterion("course_target is null");
            return (Criteria) this;
        }

        public Criteria andCourseTargetIsNotNull() {
            addCriterion("course_target is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTargetEqualTo(String value) {
            addCriterion("course_target =", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetNotEqualTo(String value) {
            addCriterion("course_target <>", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetGreaterThan(String value) {
            addCriterion("course_target >", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetGreaterThanOrEqualTo(String value) {
            addCriterion("course_target >=", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetLessThan(String value) {
            addCriterion("course_target <", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetLessThanOrEqualTo(String value) {
            addCriterion("course_target <=", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetLike(String value) {
            addCriterion("course_target like", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetNotLike(String value) {
            addCriterion("course_target not like", value, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetIn(List<String> values) {
            addCriterion("course_target in", values, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetNotIn(List<String> values) {
            addCriterion("course_target not in", values, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetBetween(String value1, String value2) {
            addCriterion("course_target between", value1, value2, "courseTarget");
            return (Criteria) this;
        }

        public Criteria andCourseTargetNotBetween(String value1, String value2) {
            addCriterion("course_target not between", value1, value2, "courseTarget");
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