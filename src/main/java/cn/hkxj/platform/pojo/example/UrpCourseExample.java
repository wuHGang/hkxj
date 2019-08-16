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

        public Criteria andBybzIsNull() {
            addCriterion("bybz is null");
            return (Criteria) this;
        }

        public Criteria andBybzIsNotNull() {
            addCriterion("bybz is not null");
            return (Criteria) this;
        }

        public Criteria andBybzEqualTo(String value) {
            addCriterion("bybz =", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzNotEqualTo(String value) {
            addCriterion("bybz <>", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzGreaterThan(String value) {
            addCriterion("bybz >", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzGreaterThanOrEqualTo(String value) {
            addCriterion("bybz >=", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzLessThan(String value) {
            addCriterion("bybz <", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzLessThanOrEqualTo(String value) {
            addCriterion("bybz <=", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzLike(String value) {
            addCriterion("bybz like", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzNotLike(String value) {
            addCriterion("bybz not like", value, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzIn(List<String> values) {
            addCriterion("bybz in", values, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzNotIn(List<String> values) {
            addCriterion("bybz not in", values, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzBetween(String value1, String value2) {
            addCriterion("bybz between", value1, value2, "bybz");
            return (Criteria) this;
        }

        public Criteria andBybzNotBetween(String value1, String value2) {
            addCriterion("bybz not between", value1, value2, "bybz");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("bz is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("bz is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("bz =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("bz <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("bz >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("bz >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("bz <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("bz <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("bz like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("bz not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("bz in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("bz not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("bz between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("bz not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzrsIsNull() {
            addCriterion("bzrs is null");
            return (Criteria) this;
        }

        public Criteria andBzrsIsNotNull() {
            addCriterion("bzrs is not null");
            return (Criteria) this;
        }

        public Criteria andBzrsEqualTo(Integer value) {
            addCriterion("bzrs =", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsNotEqualTo(Integer value) {
            addCriterion("bzrs <>", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsGreaterThan(Integer value) {
            addCriterion("bzrs >", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsGreaterThanOrEqualTo(Integer value) {
            addCriterion("bzrs >=", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsLessThan(Integer value) {
            addCriterion("bzrs <", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsLessThanOrEqualTo(Integer value) {
            addCriterion("bzrs <=", value, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsIn(List<Integer> values) {
            addCriterion("bzrs in", values, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsNotIn(List<Integer> values) {
            addCriterion("bzrs not in", values, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsBetween(Integer value1, Integer value2) {
            addCriterion("bzrs between", value1, value2, "bzrs");
            return (Criteria) this;
        }

        public Criteria andBzrsNotBetween(Integer value1, Integer value2) {
            addCriterion("bzrs not between", value1, value2, "bzrs");
            return (Criteria) this;
        }

        public Criteria andCksIsNull() {
            addCriterion("cks is null");
            return (Criteria) this;
        }

        public Criteria andCksIsNotNull() {
            addCriterion("cks is not null");
            return (Criteria) this;
        }

        public Criteria andCksEqualTo(String value) {
            addCriterion("cks =", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksNotEqualTo(String value) {
            addCriterion("cks <>", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksGreaterThan(String value) {
            addCriterion("cks >", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksGreaterThanOrEqualTo(String value) {
            addCriterion("cks >=", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksLessThan(String value) {
            addCriterion("cks <", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksLessThanOrEqualTo(String value) {
            addCriterion("cks <=", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksLike(String value) {
            addCriterion("cks like", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksNotLike(String value) {
            addCriterion("cks not like", value, "cks");
            return (Criteria) this;
        }

        public Criteria andCksIn(List<String> values) {
            addCriterion("cks in", values, "cks");
            return (Criteria) this;
        }

        public Criteria andCksNotIn(List<String> values) {
            addCriterion("cks not in", values, "cks");
            return (Criteria) this;
        }

        public Criteria andCksBetween(String value1, String value2) {
            addCriterion("cks between", value1, value2, "cks");
            return (Criteria) this;
        }

        public Criteria andCksNotBetween(String value1, String value2) {
            addCriterion("cks not between", value1, value2, "cks");
            return (Criteria) this;
        }

        public Criteria andJcIsNull() {
            addCriterion("jc is null");
            return (Criteria) this;
        }

        public Criteria andJcIsNotNull() {
            addCriterion("jc is not null");
            return (Criteria) this;
        }

        public Criteria andJcEqualTo(String value) {
            addCriterion("jc =", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotEqualTo(String value) {
            addCriterion("jc <>", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcGreaterThan(String value) {
            addCriterion("jc >", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcGreaterThanOrEqualTo(String value) {
            addCriterion("jc >=", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLessThan(String value) {
            addCriterion("jc <", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLessThanOrEqualTo(String value) {
            addCriterion("jc <=", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcLike(String value) {
            addCriterion("jc like", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotLike(String value) {
            addCriterion("jc not like", value, "jc");
            return (Criteria) this;
        }

        public Criteria andJcIn(List<String> values) {
            addCriterion("jc in", values, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotIn(List<String> values) {
            addCriterion("jc not in", values, "jc");
            return (Criteria) this;
        }

        public Criteria andJcBetween(String value1, String value2) {
            addCriterion("jc between", value1, value2, "jc");
            return (Criteria) this;
        }

        public Criteria andJcNotBetween(String value1, String value2) {
            addCriterion("jc not between", value1, value2, "jc");
            return (Criteria) this;
        }

        public Criteria andJkzxsIsNull() {
            addCriterion("jkzxs is null");
            return (Criteria) this;
        }

        public Criteria andJkzxsIsNotNull() {
            addCriterion("jkzxs is not null");
            return (Criteria) this;
        }

        public Criteria andJkzxsEqualTo(Integer value) {
            addCriterion("jkzxs =", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsNotEqualTo(Integer value) {
            addCriterion("jkzxs <>", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsGreaterThan(Integer value) {
            addCriterion("jkzxs >", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("jkzxs >=", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsLessThan(Integer value) {
            addCriterion("jkzxs <", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsLessThanOrEqualTo(Integer value) {
            addCriterion("jkzxs <=", value, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsIn(List<Integer> values) {
            addCriterion("jkzxs in", values, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsNotIn(List<Integer> values) {
            addCriterion("jkzxs not in", values, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsBetween(Integer value1, Integer value2) {
            addCriterion("jkzxs between", value1, value2, "jkzxs");
            return (Criteria) this;
        }

        public Criteria andJkzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("jkzxs not between", value1, value2, "jkzxs");
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

        public Criteria andJsrqIsNull() {
            addCriterion("jsrq is null");
            return (Criteria) this;
        }

        public Criteria andJsrqIsNotNull() {
            addCriterion("jsrq is not null");
            return (Criteria) this;
        }

        public Criteria andJsrqEqualTo(String value) {
            addCriterion("jsrq =", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqNotEqualTo(String value) {
            addCriterion("jsrq <>", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqGreaterThan(String value) {
            addCriterion("jsrq >", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqGreaterThanOrEqualTo(String value) {
            addCriterion("jsrq >=", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqLessThan(String value) {
            addCriterion("jsrq <", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqLessThanOrEqualTo(String value) {
            addCriterion("jsrq <=", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqLike(String value) {
            addCriterion("jsrq like", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqNotLike(String value) {
            addCriterion("jsrq not like", value, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqIn(List<String> values) {
            addCriterion("jsrq in", values, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqNotIn(List<String> values) {
            addCriterion("jsrq not in", values, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqBetween(String value1, String value2) {
            addCriterion("jsrq between", value1, value2, "jsrq");
            return (Criteria) this;
        }

        public Criteria andJsrqNotBetween(String value1, String value2) {
            addCriterion("jsrq not between", value1, value2, "jsrq");
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

        public Criteria andJxfssmIsNull() {
            addCriterion("jxfssm is null");
            return (Criteria) this;
        }

        public Criteria andJxfssmIsNotNull() {
            addCriterion("jxfssm is not null");
            return (Criteria) this;
        }

        public Criteria andJxfssmEqualTo(String value) {
            addCriterion("jxfssm =", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmNotEqualTo(String value) {
            addCriterion("jxfssm <>", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmGreaterThan(String value) {
            addCriterion("jxfssm >", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmGreaterThanOrEqualTo(String value) {
            addCriterion("jxfssm >=", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmLessThan(String value) {
            addCriterion("jxfssm <", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmLessThanOrEqualTo(String value) {
            addCriterion("jxfssm <=", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmLike(String value) {
            addCriterion("jxfssm like", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmNotLike(String value) {
            addCriterion("jxfssm not like", value, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmIn(List<String> values) {
            addCriterion("jxfssm in", values, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmNotIn(List<String> values) {
            addCriterion("jxfssm not in", values, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmBetween(String value1, String value2) {
            addCriterion("jxfssm between", value1, value2, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andJxfssmNotBetween(String value1, String value2) {
            addCriterion("jxfssm not between", value1, value2, "jxfssm");
            return (Criteria) this;
        }

        public Criteria andKchIsNull() {
            addCriterion("kch is null");
            return (Criteria) this;
        }

        public Criteria andKchIsNotNull() {
            addCriterion("kch is not null");
            return (Criteria) this;
        }

        public Criteria andKchEqualTo(String value) {
            addCriterion("kch =", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchNotEqualTo(String value) {
            addCriterion("kch <>", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchGreaterThan(String value) {
            addCriterion("kch >", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchGreaterThanOrEqualTo(String value) {
            addCriterion("kch >=", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchLessThan(String value) {
            addCriterion("kch <", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchLessThanOrEqualTo(String value) {
            addCriterion("kch <=", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchLike(String value) {
            addCriterion("kch like", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchNotLike(String value) {
            addCriterion("kch not like", value, "kch");
            return (Criteria) this;
        }

        public Criteria andKchIn(List<String> values) {
            addCriterion("kch in", values, "kch");
            return (Criteria) this;
        }

        public Criteria andKchNotIn(List<String> values) {
            addCriterion("kch not in", values, "kch");
            return (Criteria) this;
        }

        public Criteria andKchBetween(String value1, String value2) {
            addCriterion("kch between", value1, value2, "kch");
            return (Criteria) this;
        }

        public Criteria andKchNotBetween(String value1, String value2) {
            addCriterion("kch not between", value1, value2, "kch");
            return (Criteria) this;
        }

        public Criteria andKcjbmcIsNull() {
            addCriterion("kcjbmc is null");
            return (Criteria) this;
        }

        public Criteria andKcjbmcIsNotNull() {
            addCriterion("kcjbmc is not null");
            return (Criteria) this;
        }

        public Criteria andKcjbmcEqualTo(String value) {
            addCriterion("kcjbmc =", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcNotEqualTo(String value) {
            addCriterion("kcjbmc <>", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcGreaterThan(String value) {
            addCriterion("kcjbmc >", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcGreaterThanOrEqualTo(String value) {
            addCriterion("kcjbmc >=", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcLessThan(String value) {
            addCriterion("kcjbmc <", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcLessThanOrEqualTo(String value) {
            addCriterion("kcjbmc <=", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcLike(String value) {
            addCriterion("kcjbmc like", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcNotLike(String value) {
            addCriterion("kcjbmc not like", value, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcIn(List<String> values) {
            addCriterion("kcjbmc in", values, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcNotIn(List<String> values) {
            addCriterion("kcjbmc not in", values, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcBetween(String value1, String value2) {
            addCriterion("kcjbmc between", value1, value2, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKcjbmcNotBetween(String value1, String value2) {
            addCriterion("kcjbmc not between", value1, value2, "kcjbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcIsNull() {
            addCriterion("kclbmc is null");
            return (Criteria) this;
        }

        public Criteria andKclbmcIsNotNull() {
            addCriterion("kclbmc is not null");
            return (Criteria) this;
        }

        public Criteria andKclbmcEqualTo(String value) {
            addCriterion("kclbmc =", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcNotEqualTo(String value) {
            addCriterion("kclbmc <>", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcGreaterThan(String value) {
            addCriterion("kclbmc >", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcGreaterThanOrEqualTo(String value) {
            addCriterion("kclbmc >=", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcLessThan(String value) {
            addCriterion("kclbmc <", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcLessThanOrEqualTo(String value) {
            addCriterion("kclbmc <=", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcLike(String value) {
            addCriterion("kclbmc like", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcNotLike(String value) {
            addCriterion("kclbmc not like", value, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcIn(List<String> values) {
            addCriterion("kclbmc in", values, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcNotIn(List<String> values) {
            addCriterion("kclbmc not in", values, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcBetween(String value1, String value2) {
            addCriterion("kclbmc between", value1, value2, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKclbmcNotBetween(String value1, String value2) {
            addCriterion("kclbmc not between", value1, value2, "kclbmc");
            return (Criteria) this;
        }

        public Criteria andKcmIsNull() {
            addCriterion("kcm is null");
            return (Criteria) this;
        }

        public Criteria andKcmIsNotNull() {
            addCriterion("kcm is not null");
            return (Criteria) this;
        }

        public Criteria andKcmEqualTo(String value) {
            addCriterion("kcm =", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmNotEqualTo(String value) {
            addCriterion("kcm <>", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmGreaterThan(String value) {
            addCriterion("kcm >", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmGreaterThanOrEqualTo(String value) {
            addCriterion("kcm >=", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmLessThan(String value) {
            addCriterion("kcm <", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmLessThanOrEqualTo(String value) {
            addCriterion("kcm <=", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmLike(String value) {
            addCriterion("kcm like", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmNotLike(String value) {
            addCriterion("kcm not like", value, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmIn(List<String> values) {
            addCriterion("kcm in", values, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmNotIn(List<String> values) {
            addCriterion("kcm not in", values, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmBetween(String value1, String value2) {
            addCriterion("kcm between", value1, value2, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcmNotBetween(String value1, String value2) {
            addCriterion("kcm not between", value1, value2, "kcm");
            return (Criteria) this;
        }

        public Criteria andKcsmIsNull() {
            addCriterion("kcsm is null");
            return (Criteria) this;
        }

        public Criteria andKcsmIsNotNull() {
            addCriterion("kcsm is not null");
            return (Criteria) this;
        }

        public Criteria andKcsmEqualTo(String value) {
            addCriterion("kcsm =", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmNotEqualTo(String value) {
            addCriterion("kcsm <>", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmGreaterThan(String value) {
            addCriterion("kcsm >", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmGreaterThanOrEqualTo(String value) {
            addCriterion("kcsm >=", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmLessThan(String value) {
            addCriterion("kcsm <", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmLessThanOrEqualTo(String value) {
            addCriterion("kcsm <=", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmLike(String value) {
            addCriterion("kcsm like", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmNotLike(String value) {
            addCriterion("kcsm not like", value, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmIn(List<String> values) {
            addCriterion("kcsm in", values, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmNotIn(List<String> values) {
            addCriterion("kcsm not in", values, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmBetween(String value1, String value2) {
            addCriterion("kcsm between", value1, value2, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcsmNotBetween(String value1, String value2) {
            addCriterion("kcsm not between", value1, value2, "kcsm");
            return (Criteria) this;
        }

        public Criteria andKcztdmIsNull() {
            addCriterion("kcztdm is null");
            return (Criteria) this;
        }

        public Criteria andKcztdmIsNotNull() {
            addCriterion("kcztdm is not null");
            return (Criteria) this;
        }

        public Criteria andKcztdmEqualTo(String value) {
            addCriterion("kcztdm =", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmNotEqualTo(String value) {
            addCriterion("kcztdm <>", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmGreaterThan(String value) {
            addCriterion("kcztdm >", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmGreaterThanOrEqualTo(String value) {
            addCriterion("kcztdm >=", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmLessThan(String value) {
            addCriterion("kcztdm <", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmLessThanOrEqualTo(String value) {
            addCriterion("kcztdm <=", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmLike(String value) {
            addCriterion("kcztdm like", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmNotLike(String value) {
            addCriterion("kcztdm not like", value, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmIn(List<String> values) {
            addCriterion("kcztdm in", values, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmNotIn(List<String> values) {
            addCriterion("kcztdm not in", values, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmBetween(String value1, String value2) {
            addCriterion("kcztdm between", value1, value2, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKcztdmNotBetween(String value1, String value2) {
            addCriterion("kcztdm not between", value1, value2, "kcztdm");
            return (Criteria) this;
        }

        public Criteria andKkxqIsNull() {
            addCriterion("kkxq is null");
            return (Criteria) this;
        }

        public Criteria andKkxqIsNotNull() {
            addCriterion("kkxq is not null");
            return (Criteria) this;
        }

        public Criteria andKkxqEqualTo(String value) {
            addCriterion("kkxq =", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqNotEqualTo(String value) {
            addCriterion("kkxq <>", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqGreaterThan(String value) {
            addCriterion("kkxq >", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqGreaterThanOrEqualTo(String value) {
            addCriterion("kkxq >=", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqLessThan(String value) {
            addCriterion("kkxq <", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqLessThanOrEqualTo(String value) {
            addCriterion("kkxq <=", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqLike(String value) {
            addCriterion("kkxq like", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqNotLike(String value) {
            addCriterion("kkxq not like", value, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqIn(List<String> values) {
            addCriterion("kkxq in", values, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqNotIn(List<String> values) {
            addCriterion("kkxq not in", values, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqBetween(String value1, String value2) {
            addCriterion("kkxq between", value1, value2, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKkxqNotBetween(String value1, String value2) {
            addCriterion("kkxq not between", value1, value2, "kkxq");
            return (Criteria) this;
        }

        public Criteria andKnzxsIsNull() {
            addCriterion("knzxs is null");
            return (Criteria) this;
        }

        public Criteria andKnzxsIsNotNull() {
            addCriterion("knzxs is not null");
            return (Criteria) this;
        }

        public Criteria andKnzxsEqualTo(Integer value) {
            addCriterion("knzxs =", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsNotEqualTo(Integer value) {
            addCriterion("knzxs <>", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsGreaterThan(Integer value) {
            addCriterion("knzxs >", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("knzxs >=", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsLessThan(Integer value) {
            addCriterion("knzxs <", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsLessThanOrEqualTo(Integer value) {
            addCriterion("knzxs <=", value, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsIn(List<Integer> values) {
            addCriterion("knzxs in", values, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsNotIn(List<Integer> values) {
            addCriterion("knzxs not in", values, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsBetween(Integer value1, Integer value2) {
            addCriterion("knzxs between", value1, value2, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKnzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("knzxs not between", value1, value2, "knzxs");
            return (Criteria) this;
        }

        public Criteria andKsflbdmIsNull() {
            addCriterion("ksflbdm is null");
            return (Criteria) this;
        }

        public Criteria andKsflbdmIsNotNull() {
            addCriterion("ksflbdm is not null");
            return (Criteria) this;
        }

        public Criteria andKsflbdmEqualTo(String value) {
            addCriterion("ksflbdm =", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmNotEqualTo(String value) {
            addCriterion("ksflbdm <>", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmGreaterThan(String value) {
            addCriterion("ksflbdm >", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmGreaterThanOrEqualTo(String value) {
            addCriterion("ksflbdm >=", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmLessThan(String value) {
            addCriterion("ksflbdm <", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmLessThanOrEqualTo(String value) {
            addCriterion("ksflbdm <=", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmLike(String value) {
            addCriterion("ksflbdm like", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmNotLike(String value) {
            addCriterion("ksflbdm not like", value, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmIn(List<String> values) {
            addCriterion("ksflbdm in", values, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmNotIn(List<String> values) {
            addCriterion("ksflbdm not in", values, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmBetween(String value1, String value2) {
            addCriterion("ksflbdm between", value1, value2, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKsflbdmNotBetween(String value1, String value2) {
            addCriterion("ksflbdm not between", value1, value2, "ksflbdm");
            return (Criteria) this;
        }

        public Criteria andKslxmcIsNull() {
            addCriterion("kslxmc is null");
            return (Criteria) this;
        }

        public Criteria andKslxmcIsNotNull() {
            addCriterion("kslxmc is not null");
            return (Criteria) this;
        }

        public Criteria andKslxmcEqualTo(String value) {
            addCriterion("kslxmc =", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcNotEqualTo(String value) {
            addCriterion("kslxmc <>", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcGreaterThan(String value) {
            addCriterion("kslxmc >", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcGreaterThanOrEqualTo(String value) {
            addCriterion("kslxmc >=", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcLessThan(String value) {
            addCriterion("kslxmc <", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcLessThanOrEqualTo(String value) {
            addCriterion("kslxmc <=", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcLike(String value) {
            addCriterion("kslxmc like", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcNotLike(String value) {
            addCriterion("kslxmc not like", value, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcIn(List<String> values) {
            addCriterion("kslxmc in", values, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcNotIn(List<String> values) {
            addCriterion("kslxmc not in", values, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcBetween(String value1, String value2) {
            addCriterion("kslxmc between", value1, value2, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKslxmcNotBetween(String value1, String value2) {
            addCriterion("kslxmc not between", value1, value2, "kslxmc");
            return (Criteria) this;
        }

        public Criteria andKsrqIsNull() {
            addCriterion("ksrq is null");
            return (Criteria) this;
        }

        public Criteria andKsrqIsNotNull() {
            addCriterion("ksrq is not null");
            return (Criteria) this;
        }

        public Criteria andKsrqEqualTo(String value) {
            addCriterion("ksrq =", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqNotEqualTo(String value) {
            addCriterion("ksrq <>", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqGreaterThan(String value) {
            addCriterion("ksrq >", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqGreaterThanOrEqualTo(String value) {
            addCriterion("ksrq >=", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqLessThan(String value) {
            addCriterion("ksrq <", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqLessThanOrEqualTo(String value) {
            addCriterion("ksrq <=", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqLike(String value) {
            addCriterion("ksrq like", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqNotLike(String value) {
            addCriterion("ksrq not like", value, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqIn(List<String> values) {
            addCriterion("ksrq in", values, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqNotIn(List<String> values) {
            addCriterion("ksrq not in", values, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqBetween(String value1, String value2) {
            addCriterion("ksrq between", value1, value2, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKsrqNotBetween(String value1, String value2) {
            addCriterion("ksrq not between", value1, value2, "ksrq");
            return (Criteria) this;
        }

        public Criteria andKwxfIsNull() {
            addCriterion("kwxf is null");
            return (Criteria) this;
        }

        public Criteria andKwxfIsNotNull() {
            addCriterion("kwxf is not null");
            return (Criteria) this;
        }

        public Criteria andKwxfEqualTo(Integer value) {
            addCriterion("kwxf =", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfNotEqualTo(Integer value) {
            addCriterion("kwxf <>", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfGreaterThan(Integer value) {
            addCriterion("kwxf >", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfGreaterThanOrEqualTo(Integer value) {
            addCriterion("kwxf >=", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfLessThan(Integer value) {
            addCriterion("kwxf <", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfLessThanOrEqualTo(Integer value) {
            addCriterion("kwxf <=", value, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfIn(List<Integer> values) {
            addCriterion("kwxf in", values, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfNotIn(List<Integer> values) {
            addCriterion("kwxf not in", values, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfBetween(Integer value1, Integer value2) {
            addCriterion("kwxf between", value1, value2, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwxfNotBetween(Integer value1, Integer value2) {
            addCriterion("kwxf not between", value1, value2, "kwxf");
            return (Criteria) this;
        }

        public Criteria andKwzxsIsNull() {
            addCriterion("kwzxs is null");
            return (Criteria) this;
        }

        public Criteria andKwzxsIsNotNull() {
            addCriterion("kwzxs is not null");
            return (Criteria) this;
        }

        public Criteria andKwzxsEqualTo(Integer value) {
            addCriterion("kwzxs =", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsNotEqualTo(Integer value) {
            addCriterion("kwzxs <>", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsGreaterThan(Integer value) {
            addCriterion("kwzxs >", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("kwzxs >=", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsLessThan(Integer value) {
            addCriterion("kwzxs <", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsLessThanOrEqualTo(Integer value) {
            addCriterion("kwzxs <=", value, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsIn(List<Integer> values) {
            addCriterion("kwzxs in", values, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsNotIn(List<Integer> values) {
            addCriterion("kwzxs not in", values, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsBetween(Integer value1, Integer value2) {
            addCriterion("kwzxs between", value1, value2, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andKwzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("kwzxs not between", value1, value2, "kwzxs");
            return (Criteria) this;
        }

        public Criteria andNrjjIsNull() {
            addCriterion("nrjj is null");
            return (Criteria) this;
        }

        public Criteria andNrjjIsNotNull() {
            addCriterion("nrjj is not null");
            return (Criteria) this;
        }

        public Criteria andNrjjEqualTo(String value) {
            addCriterion("nrjj =", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjNotEqualTo(String value) {
            addCriterion("nrjj <>", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjGreaterThan(String value) {
            addCriterion("nrjj >", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjGreaterThanOrEqualTo(String value) {
            addCriterion("nrjj >=", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjLessThan(String value) {
            addCriterion("nrjj <", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjLessThanOrEqualTo(String value) {
            addCriterion("nrjj <=", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjLike(String value) {
            addCriterion("nrjj like", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjNotLike(String value) {
            addCriterion("nrjj not like", value, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjIn(List<String> values) {
            addCriterion("nrjj in", values, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjNotIn(List<String> values) {
            addCriterion("nrjj not in", values, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjBetween(String value1, String value2) {
            addCriterion("nrjj between", value1, value2, "nrjj");
            return (Criteria) this;
        }

        public Criteria andNrjjNotBetween(String value1, String value2) {
            addCriterion("nrjj not between", value1, value2, "nrjj");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsIsNull() {
            addCriterion("qzsjzxs is null");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsIsNotNull() {
            addCriterion("qzsjzxs is not null");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsEqualTo(Integer value) {
            addCriterion("qzsjzxs =", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsNotEqualTo(Integer value) {
            addCriterion("qzsjzxs <>", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsGreaterThan(Integer value) {
            addCriterion("qzsjzxs >", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("qzsjzxs >=", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsLessThan(Integer value) {
            addCriterion("qzsjzxs <", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsLessThanOrEqualTo(Integer value) {
            addCriterion("qzsjzxs <=", value, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsIn(List<Integer> values) {
            addCriterion("qzsjzxs in", values, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsNotIn(List<Integer> values) {
            addCriterion("qzsjzxs not in", values, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsBetween(Integer value1, Integer value2) {
            addCriterion("qzsjzxs between", value1, value2, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andQzsjzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("qzsjzxs not between", value1, value2, "qzsjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzsIsNull() {
            addCriterion("sjzs is null");
            return (Criteria) this;
        }

        public Criteria andSjzsIsNotNull() {
            addCriterion("sjzs is not null");
            return (Criteria) this;
        }

        public Criteria andSjzsEqualTo(Integer value) {
            addCriterion("sjzs =", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsNotEqualTo(Integer value) {
            addCriterion("sjzs <>", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsGreaterThan(Integer value) {
            addCriterion("sjzs >", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjzs >=", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsLessThan(Integer value) {
            addCriterion("sjzs <", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsLessThanOrEqualTo(Integer value) {
            addCriterion("sjzs <=", value, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsIn(List<Integer> values) {
            addCriterion("sjzs in", values, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsNotIn(List<Integer> values) {
            addCriterion("sjzs not in", values, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsBetween(Integer value1, Integer value2) {
            addCriterion("sjzs between", value1, value2, "sjzs");
            return (Criteria) this;
        }

        public Criteria andSjzsNotBetween(Integer value1, Integer value2) {
            addCriterion("sjzs not between", value1, value2, "sjzs");
            return (Criteria) this;
        }

        public Criteria andRsxsdmIsNull() {
            addCriterion("rsxsdm is null");
            return (Criteria) this;
        }

        public Criteria andRsxsdmIsNotNull() {
            addCriterion("rsxsdm is not null");
            return (Criteria) this;
        }

        public Criteria andRsxsdmEqualTo(String value) {
            addCriterion("rsxsdm =", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmNotEqualTo(String value) {
            addCriterion("rsxsdm <>", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmGreaterThan(String value) {
            addCriterion("rsxsdm >", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmGreaterThanOrEqualTo(String value) {
            addCriterion("rsxsdm >=", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmLessThan(String value) {
            addCriterion("rsxsdm <", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmLessThanOrEqualTo(String value) {
            addCriterion("rsxsdm <=", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmLike(String value) {
            addCriterion("rsxsdm like", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmNotLike(String value) {
            addCriterion("rsxsdm not like", value, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmIn(List<String> values) {
            addCriterion("rsxsdm in", values, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmNotIn(List<String> values) {
            addCriterion("rsxsdm not in", values, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmBetween(String value1, String value2) {
            addCriterion("rsxsdm between", value1, value2, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andRsxsdmNotBetween(String value1, String value2) {
            addCriterion("rsxsdm not between", value1, value2, "rsxsdm");
            return (Criteria) this;
        }

        public Criteria andSflbmcIsNull() {
            addCriterion("sflbmc is null");
            return (Criteria) this;
        }

        public Criteria andSflbmcIsNotNull() {
            addCriterion("sflbmc is not null");
            return (Criteria) this;
        }

        public Criteria andSflbmcEqualTo(String value) {
            addCriterion("sflbmc =", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcNotEqualTo(String value) {
            addCriterion("sflbmc <>", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcGreaterThan(String value) {
            addCriterion("sflbmc >", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcGreaterThanOrEqualTo(String value) {
            addCriterion("sflbmc >=", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcLessThan(String value) {
            addCriterion("sflbmc <", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcLessThanOrEqualTo(String value) {
            addCriterion("sflbmc <=", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcLike(String value) {
            addCriterion("sflbmc like", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcNotLike(String value) {
            addCriterion("sflbmc not like", value, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcIn(List<String> values) {
            addCriterion("sflbmc in", values, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcNotIn(List<String> values) {
            addCriterion("sflbmc not in", values, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcBetween(String value1, String value2) {
            addCriterion("sflbmc between", value1, value2, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSflbmcNotBetween(String value1, String value2) {
            addCriterion("sflbmc not between", value1, value2, "sflbmc");
            return (Criteria) this;
        }

        public Criteria andSjzxsIsNull() {
            addCriterion("sjzxs is null");
            return (Criteria) this;
        }

        public Criteria andSjzxsIsNotNull() {
            addCriterion("sjzxs is not null");
            return (Criteria) this;
        }

        public Criteria andSjzxsEqualTo(Integer value) {
            addCriterion("sjzxs =", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsNotEqualTo(Integer value) {
            addCriterion("sjzxs <>", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsGreaterThan(Integer value) {
            addCriterion("sjzxs >", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjzxs >=", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsLessThan(Integer value) {
            addCriterion("sjzxs <", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsLessThanOrEqualTo(Integer value) {
            addCriterion("sjzxs <=", value, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsIn(List<Integer> values) {
            addCriterion("sjzxs in", values, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsNotIn(List<Integer> values) {
            addCriterion("sjzxs not in", values, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsBetween(Integer value1, Integer value2) {
            addCriterion("sjzxs between", value1, value2, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("sjzxs not between", value1, value2, "sjzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsIsNull() {
            addCriterion("sjzyzxs is null");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsIsNotNull() {
            addCriterion("sjzyzxs is not null");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsEqualTo(Integer value) {
            addCriterion("sjzyzxs =", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsNotEqualTo(Integer value) {
            addCriterion("sjzyzxs <>", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsGreaterThan(Integer value) {
            addCriterion("sjzyzxs >", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjzyzxs >=", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsLessThan(Integer value) {
            addCriterion("sjzyzxs <", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsLessThanOrEqualTo(Integer value) {
            addCriterion("sjzyzxs <=", value, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsIn(List<Integer> values) {
            addCriterion("sjzyzxs in", values, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsNotIn(List<Integer> values) {
            addCriterion("sjzyzxs not in", values, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsBetween(Integer value1, Integer value2) {
            addCriterion("sjzyzxs between", value1, value2, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSjzyzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("sjzyzxs not between", value1, value2, "sjzyzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsIsNull() {
            addCriterion("syzxs is null");
            return (Criteria) this;
        }

        public Criteria andSyzxsIsNotNull() {
            addCriterion("syzxs is not null");
            return (Criteria) this;
        }

        public Criteria andSyzxsEqualTo(Integer value) {
            addCriterion("syzxs =", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsNotEqualTo(Integer value) {
            addCriterion("syzxs <>", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsGreaterThan(Integer value) {
            addCriterion("syzxs >", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("syzxs >=", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsLessThan(Integer value) {
            addCriterion("syzxs <", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsLessThanOrEqualTo(Integer value) {
            addCriterion("syzxs <=", value, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsIn(List<Integer> values) {
            addCriterion("syzxs in", values, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsNotIn(List<Integer> values) {
            addCriterion("syzxs not in", values, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsBetween(Integer value1, Integer value2) {
            addCriterion("syzxs between", value1, value2, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSyzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("syzxs not between", value1, value2, "syzxs");
            return (Criteria) this;
        }

        public Criteria andSzdwIsNull() {
            addCriterion("szdw is null");
            return (Criteria) this;
        }

        public Criteria andSzdwIsNotNull() {
            addCriterion("szdw is not null");
            return (Criteria) this;
        }

        public Criteria andSzdwEqualTo(String value) {
            addCriterion("szdw =", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwNotEqualTo(String value) {
            addCriterion("szdw <>", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwGreaterThan(String value) {
            addCriterion("szdw >", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwGreaterThanOrEqualTo(String value) {
            addCriterion("szdw >=", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwLessThan(String value) {
            addCriterion("szdw <", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwLessThanOrEqualTo(String value) {
            addCriterion("szdw <=", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwLike(String value) {
            addCriterion("szdw like", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwNotLike(String value) {
            addCriterion("szdw not like", value, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwIn(List<String> values) {
            addCriterion("szdw in", values, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwNotIn(List<String> values) {
            addCriterion("szdw not in", values, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwBetween(String value1, String value2) {
            addCriterion("szdw between", value1, value2, "szdw");
            return (Criteria) this;
        }

        public Criteria andSzdwNotBetween(String value1, String value2) {
            addCriterion("szdw not between", value1, value2, "szdw");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsIsNull() {
            addCriterion("tlfdzxs is null");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsIsNotNull() {
            addCriterion("tlfdzxs is not null");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsEqualTo(Integer value) {
            addCriterion("tlfdzxs =", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsNotEqualTo(Integer value) {
            addCriterion("tlfdzxs <>", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsGreaterThan(Integer value) {
            addCriterion("tlfdzxs >", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsGreaterThanOrEqualTo(Integer value) {
            addCriterion("tlfdzxs >=", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsLessThan(Integer value) {
            addCriterion("tlfdzxs <", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsLessThanOrEqualTo(Integer value) {
            addCriterion("tlfdzxs <=", value, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsIn(List<Integer> values) {
            addCriterion("tlfdzxs in", values, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsNotIn(List<Integer> values) {
            addCriterion("tlfdzxs not in", values, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsBetween(Integer value1, Integer value2) {
            addCriterion("tlfdzxs between", value1, value2, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andTlfdzxsNotBetween(Integer value1, Integer value2) {
            addCriterion("tlfdzxs not between", value1, value2, "tlfdzxs");
            return (Criteria) this;
        }

        public Criteria andXfIsNull() {
            addCriterion("xf is null");
            return (Criteria) this;
        }

        public Criteria andXfIsNotNull() {
            addCriterion("xf is not null");
            return (Criteria) this;
        }

        public Criteria andXfEqualTo(Double value) {
            addCriterion("xf =", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfNotEqualTo(Double value) {
            addCriterion("xf <>", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfGreaterThan(Double value) {
            addCriterion("xf >", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfGreaterThanOrEqualTo(Double value) {
            addCriterion("xf >=", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfLessThan(Double value) {
            addCriterion("xf <", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfLessThanOrEqualTo(Double value) {
            addCriterion("xf <=", value, "xf");
            return (Criteria) this;
        }

        public Criteria andXfIn(List<Double> values) {
            addCriterion("xf in", values, "xf");
            return (Criteria) this;
        }

        public Criteria andXfNotIn(List<Double> values) {
            addCriterion("xf not in", values, "xf");
            return (Criteria) this;
        }

        public Criteria andXfBetween(Double value1, Double value2) {
            addCriterion("xf between", value1, value2, "xf");
            return (Criteria) this;
        }

        public Criteria andXfNotBetween(Double value1, Double value2) {
            addCriterion("xf not between", value1, value2, "xf");
            return (Criteria) this;
        }

        public Criteria andXkmlhIsNull() {
            addCriterion("xkmlh is null");
            return (Criteria) this;
        }

        public Criteria andXkmlhIsNotNull() {
            addCriterion("xkmlh is not null");
            return (Criteria) this;
        }

        public Criteria andXkmlhEqualTo(String value) {
            addCriterion("xkmlh =", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhNotEqualTo(String value) {
            addCriterion("xkmlh <>", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhGreaterThan(String value) {
            addCriterion("xkmlh >", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhGreaterThanOrEqualTo(String value) {
            addCriterion("xkmlh >=", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhLessThan(String value) {
            addCriterion("xkmlh <", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhLessThanOrEqualTo(String value) {
            addCriterion("xkmlh <=", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhLike(String value) {
            addCriterion("xkmlh like", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhNotLike(String value) {
            addCriterion("xkmlh not like", value, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhIn(List<String> values) {
            addCriterion("xkmlh in", values, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhNotIn(List<String> values) {
            addCriterion("xkmlh not in", values, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhBetween(String value1, String value2) {
            addCriterion("xkmlh between", value1, value2, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlhNotBetween(String value1, String value2) {
            addCriterion("xkmlh not between", value1, value2, "xkmlh");
            return (Criteria) this;
        }

        public Criteria andXkmlmIsNull() {
            addCriterion("xkmlm is null");
            return (Criteria) this;
        }

        public Criteria andXkmlmIsNotNull() {
            addCriterion("xkmlm is not null");
            return (Criteria) this;
        }

        public Criteria andXkmlmEqualTo(String value) {
            addCriterion("xkmlm =", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmNotEqualTo(String value) {
            addCriterion("xkmlm <>", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmGreaterThan(String value) {
            addCriterion("xkmlm >", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmGreaterThanOrEqualTo(String value) {
            addCriterion("xkmlm >=", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmLessThan(String value) {
            addCriterion("xkmlm <", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmLessThanOrEqualTo(String value) {
            addCriterion("xkmlm <=", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmLike(String value) {
            addCriterion("xkmlm like", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmNotLike(String value) {
            addCriterion("xkmlm not like", value, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmIn(List<String> values) {
            addCriterion("xkmlm in", values, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmNotIn(List<String> values) {
            addCriterion("xkmlm not in", values, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmBetween(String value1, String value2) {
            addCriterion("xkmlm between", value1, value2, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXkmlmNotBetween(String value1, String value2) {
            addCriterion("xkmlm not between", value1, value2, "xkmlm");
            return (Criteria) this;
        }

        public Criteria andXqhIsNull() {
            addCriterion("xqh is null");
            return (Criteria) this;
        }

        public Criteria andXqhIsNotNull() {
            addCriterion("xqh is not null");
            return (Criteria) this;
        }

        public Criteria andXqhEqualTo(String value) {
            addCriterion("xqh =", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhNotEqualTo(String value) {
            addCriterion("xqh <>", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhGreaterThan(String value) {
            addCriterion("xqh >", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhGreaterThanOrEqualTo(String value) {
            addCriterion("xqh >=", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhLessThan(String value) {
            addCriterion("xqh <", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhLessThanOrEqualTo(String value) {
            addCriterion("xqh <=", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhLike(String value) {
            addCriterion("xqh like", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhNotLike(String value) {
            addCriterion("xqh not like", value, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhIn(List<String> values) {
            addCriterion("xqh in", values, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhNotIn(List<String> values) {
            addCriterion("xqh not in", values, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhBetween(String value1, String value2) {
            addCriterion("xqh between", value1, value2, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqhNotBetween(String value1, String value2) {
            addCriterion("xqh not between", value1, value2, "xqh");
            return (Criteria) this;
        }

        public Criteria andXqmIsNull() {
            addCriterion("xqm is null");
            return (Criteria) this;
        }

        public Criteria andXqmIsNotNull() {
            addCriterion("xqm is not null");
            return (Criteria) this;
        }

        public Criteria andXqmEqualTo(String value) {
            addCriterion("xqm =", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmNotEqualTo(String value) {
            addCriterion("xqm <>", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmGreaterThan(String value) {
            addCriterion("xqm >", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmGreaterThanOrEqualTo(String value) {
            addCriterion("xqm >=", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmLessThan(String value) {
            addCriterion("xqm <", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmLessThanOrEqualTo(String value) {
            addCriterion("xqm <=", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmLike(String value) {
            addCriterion("xqm like", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmNotLike(String value) {
            addCriterion("xqm not like", value, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmIn(List<String> values) {
            addCriterion("xqm in", values, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmNotIn(List<String> values) {
            addCriterion("xqm not in", values, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmBetween(String value1, String value2) {
            addCriterion("xqm between", value1, value2, "xqm");
            return (Criteria) this;
        }

        public Criteria andXqmNotBetween(String value1, String value2) {
            addCriterion("xqm not between", value1, value2, "xqm");
            return (Criteria) this;
        }

        public Criteria andXsIsNull() {
            addCriterion("xs is null");
            return (Criteria) this;
        }

        public Criteria andXsIsNotNull() {
            addCriterion("xs is not null");
            return (Criteria) this;
        }

        public Criteria andXsEqualTo(Integer value) {
            addCriterion("xs =", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotEqualTo(Integer value) {
            addCriterion("xs <>", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThan(Integer value) {
            addCriterion("xs >", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThanOrEqualTo(Integer value) {
            addCriterion("xs >=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThan(Integer value) {
            addCriterion("xs <", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThanOrEqualTo(Integer value) {
            addCriterion("xs <=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsIn(List<Integer> values) {
            addCriterion("xs in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotIn(List<Integer> values) {
            addCriterion("xs not in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsBetween(Integer value1, Integer value2) {
            addCriterion("xs between", value1, value2, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotBetween(Integer value1, Integer value2) {
            addCriterion("xs not between", value1, value2, "xs");
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

        public Criteria andYwjxdgIsNull() {
            addCriterion("ywjxdg is null");
            return (Criteria) this;
        }

        public Criteria andYwjxdgIsNotNull() {
            addCriterion("ywjxdg is not null");
            return (Criteria) this;
        }

        public Criteria andYwjxdgEqualTo(String value) {
            addCriterion("ywjxdg =", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgNotEqualTo(String value) {
            addCriterion("ywjxdg <>", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgGreaterThan(String value) {
            addCriterion("ywjxdg >", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgGreaterThanOrEqualTo(String value) {
            addCriterion("ywjxdg >=", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgLessThan(String value) {
            addCriterion("ywjxdg <", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgLessThanOrEqualTo(String value) {
            addCriterion("ywjxdg <=", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgLike(String value) {
            addCriterion("ywjxdg like", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgNotLike(String value) {
            addCriterion("ywjxdg not like", value, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgIn(List<String> values) {
            addCriterion("ywjxdg in", values, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgNotIn(List<String> values) {
            addCriterion("ywjxdg not in", values, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgBetween(String value1, String value2) {
            addCriterion("ywjxdg between", value1, value2, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwjxdgNotBetween(String value1, String value2) {
            addCriterion("ywjxdg not between", value1, value2, "ywjxdg");
            return (Criteria) this;
        }

        public Criteria andYwkcmIsNull() {
            addCriterion("ywkcm is null");
            return (Criteria) this;
        }

        public Criteria andYwkcmIsNotNull() {
            addCriterion("ywkcm is not null");
            return (Criteria) this;
        }

        public Criteria andYwkcmEqualTo(String value) {
            addCriterion("ywkcm =", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmNotEqualTo(String value) {
            addCriterion("ywkcm <>", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmGreaterThan(String value) {
            addCriterion("ywkcm >", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmGreaterThanOrEqualTo(String value) {
            addCriterion("ywkcm >=", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmLessThan(String value) {
            addCriterion("ywkcm <", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmLessThanOrEqualTo(String value) {
            addCriterion("ywkcm <=", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmLike(String value) {
            addCriterion("ywkcm like", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmNotLike(String value) {
            addCriterion("ywkcm not like", value, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmIn(List<String> values) {
            addCriterion("ywkcm in", values, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmNotIn(List<String> values) {
            addCriterion("ywkcm not in", values, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmBetween(String value1, String value2) {
            addCriterion("ywkcm between", value1, value2, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwkcmNotBetween(String value1, String value2) {
            addCriterion("ywkcm not between", value1, value2, "ywkcm");
            return (Criteria) this;
        }

        public Criteria andYwnrjjIsNull() {
            addCriterion("ywnrjj is null");
            return (Criteria) this;
        }

        public Criteria andYwnrjjIsNotNull() {
            addCriterion("ywnrjj is not null");
            return (Criteria) this;
        }

        public Criteria andYwnrjjEqualTo(String value) {
            addCriterion("ywnrjj =", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjNotEqualTo(String value) {
            addCriterion("ywnrjj <>", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjGreaterThan(String value) {
            addCriterion("ywnrjj >", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjGreaterThanOrEqualTo(String value) {
            addCriterion("ywnrjj >=", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjLessThan(String value) {
            addCriterion("ywnrjj <", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjLessThanOrEqualTo(String value) {
            addCriterion("ywnrjj <=", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjLike(String value) {
            addCriterion("ywnrjj like", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjNotLike(String value) {
            addCriterion("ywnrjj not like", value, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjIn(List<String> values) {
            addCriterion("ywnrjj in", values, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjNotIn(List<String> values) {
            addCriterion("ywnrjj not in", values, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjBetween(String value1, String value2) {
            addCriterion("ywnrjj between", value1, value2, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andYwnrjjNotBetween(String value1, String value2) {
            addCriterion("ywnrjj not between", value1, value2, "ywnrjj");
            return (Criteria) this;
        }

        public Criteria andZyxkdxIsNull() {
            addCriterion("zyxkdx is null");
            return (Criteria) this;
        }

        public Criteria andZyxkdxIsNotNull() {
            addCriterion("zyxkdx is not null");
            return (Criteria) this;
        }

        public Criteria andZyxkdxEqualTo(String value) {
            addCriterion("zyxkdx =", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxNotEqualTo(String value) {
            addCriterion("zyxkdx <>", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxGreaterThan(String value) {
            addCriterion("zyxkdx >", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxGreaterThanOrEqualTo(String value) {
            addCriterion("zyxkdx >=", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxLessThan(String value) {
            addCriterion("zyxkdx <", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxLessThanOrEqualTo(String value) {
            addCriterion("zyxkdx <=", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxLike(String value) {
            addCriterion("zyxkdx like", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxNotLike(String value) {
            addCriterion("zyxkdx not like", value, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxIn(List<String> values) {
            addCriterion("zyxkdx in", values, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxNotIn(List<String> values) {
            addCriterion("zyxkdx not in", values, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxBetween(String value1, String value2) {
            addCriterion("zyxkdx between", value1, value2, "zyxkdx");
            return (Criteria) this;
        }

        public Criteria andZyxkdxNotBetween(String value1, String value2) {
            addCriterion("zyxkdx not between", value1, value2, "zyxkdx");
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