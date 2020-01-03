package cn.hkxj.platform.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExamTimetableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExamTimetableExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdIsNull() {
            addCriterion("exam_timetable_id is null");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdIsNotNull() {
            addCriterion("exam_timetable_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdEqualTo(Integer value) {
            addCriterion("exam_timetable_id =", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdNotEqualTo(Integer value) {
            addCriterion("exam_timetable_id <>", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdGreaterThan(Integer value) {
            addCriterion("exam_timetable_id >", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_timetable_id >=", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdLessThan(Integer value) {
            addCriterion("exam_timetable_id <", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_timetable_id <=", value, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdIn(List<Integer> values) {
            addCriterion("exam_timetable_id in", values, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdNotIn(List<Integer> values) {
            addCriterion("exam_timetable_id not in", values, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_timetable_id between", value1, value2, "examTimetableId");
            return (Criteria) this;
        }

        public Criteria andExamTimetableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_timetable_id not between", value1, value2, "examTimetableId");
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