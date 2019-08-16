package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MajorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MajorExample() {
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

        public Criteria andZyhIsNull() {
            addCriterion("zyh is null");
            return (Criteria) this;
        }

        public Criteria andZyhIsNotNull() {
            addCriterion("zyh is not null");
            return (Criteria) this;
        }

        public Criteria andZyhEqualTo(String value) {
            addCriterion("zyh =", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhNotEqualTo(String value) {
            addCriterion("zyh <>", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhGreaterThan(String value) {
            addCriterion("zyh >", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhGreaterThanOrEqualTo(String value) {
            addCriterion("zyh >=", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhLessThan(String value) {
            addCriterion("zyh <", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhLessThanOrEqualTo(String value) {
            addCriterion("zyh <=", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhLike(String value) {
            addCriterion("zyh like", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhNotLike(String value) {
            addCriterion("zyh not like", value, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhIn(List<String> values) {
            addCriterion("zyh in", values, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhNotIn(List<String> values) {
            addCriterion("zyh not in", values, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhBetween(String value1, String value2) {
            addCriterion("zyh between", value1, value2, "zyh");
            return (Criteria) this;
        }

        public Criteria andZyhNotBetween(String value1, String value2) {
            addCriterion("zyh not between", value1, value2, "zyh");
            return (Criteria) this;
        }

        public Criteria andZymIsNull() {
            addCriterion("zym is null");
            return (Criteria) this;
        }

        public Criteria andZymIsNotNull() {
            addCriterion("zym is not null");
            return (Criteria) this;
        }

        public Criteria andZymEqualTo(String value) {
            addCriterion("zym =", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymNotEqualTo(String value) {
            addCriterion("zym <>", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymGreaterThan(String value) {
            addCriterion("zym >", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymGreaterThanOrEqualTo(String value) {
            addCriterion("zym >=", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymLessThan(String value) {
            addCriterion("zym <", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymLessThanOrEqualTo(String value) {
            addCriterion("zym <=", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymLike(String value) {
            addCriterion("zym like", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymNotLike(String value) {
            addCriterion("zym not like", value, "zym");
            return (Criteria) this;
        }

        public Criteria andZymIn(List<String> values) {
            addCriterion("zym in", values, "zym");
            return (Criteria) this;
        }

        public Criteria andZymNotIn(List<String> values) {
            addCriterion("zym not in", values, "zym");
            return (Criteria) this;
        }

        public Criteria andZymBetween(String value1, String value2) {
            addCriterion("zym between", value1, value2, "zym");
            return (Criteria) this;
        }

        public Criteria andZymNotBetween(String value1, String value2) {
            addCriterion("zym not between", value1, value2, "zym");
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