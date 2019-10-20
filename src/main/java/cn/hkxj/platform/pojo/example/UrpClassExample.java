package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.List;

public class UrpClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpClassExample() {
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

        public Criteria andAdmissionGradeIsNull() {
            addCriterion("admission_grade is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeIsNotNull() {
            addCriterion("admission_grade is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeEqualTo(String value) {
            addCriterion("admission_grade =", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeNotEqualTo(String value) {
            addCriterion("admission_grade <>", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeGreaterThan(String value) {
            addCriterion("admission_grade >", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeGreaterThanOrEqualTo(String value) {
            addCriterion("admission_grade >=", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeLessThan(String value) {
            addCriterion("admission_grade <", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeLessThanOrEqualTo(String value) {
            addCriterion("admission_grade <=", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeLike(String value) {
            addCriterion("admission_grade like", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeNotLike(String value) {
            addCriterion("admission_grade not like", value, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeIn(List<String> values) {
            addCriterion("admission_grade in", values, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeNotIn(List<String> values) {
            addCriterion("admission_grade not in", values, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeBetween(String value1, String value2) {
            addCriterion("admission_grade between", value1, value2, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andAdmissionGradeNotBetween(String value1, String value2) {
            addCriterion("admission_grade not between", value1, value2, "admissionGrade");
            return (Criteria) this;
        }

        public Criteria andClassNumIsNull() {
            addCriterion("class_num is null");
            return (Criteria) this;
        }

        public Criteria andClassNumIsNotNull() {
            addCriterion("class_num is not null");
            return (Criteria) this;
        }

        public Criteria andClassNumEqualTo(String value) {
            addCriterion("class_num =", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotEqualTo(String value) {
            addCriterion("class_num <>", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumGreaterThan(String value) {
            addCriterion("class_num >", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumGreaterThanOrEqualTo(String value) {
            addCriterion("class_num >=", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLessThan(String value) {
            addCriterion("class_num <", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLessThanOrEqualTo(String value) {
            addCriterion("class_num <=", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumLike(String value) {
            addCriterion("class_num like", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotLike(String value) {
            addCriterion("class_num not like", value, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumIn(List<String> values) {
            addCriterion("class_num in", values, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotIn(List<String> values) {
            addCriterion("class_num not in", values, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumBetween(String value1, String value2) {
            addCriterion("class_num between", value1, value2, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNumNotBetween(String value1, String value2) {
            addCriterion("class_num not between", value1, value2, "classNum");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIsNull() {
            addCriterion("academy_name is null");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIsNotNull() {
            addCriterion("academy_name is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyNameEqualTo(String value) {
            addCriterion("academy_name =", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotEqualTo(String value) {
            addCriterion("academy_name <>", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameGreaterThan(String value) {
            addCriterion("academy_name >", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameGreaterThanOrEqualTo(String value) {
            addCriterion("academy_name >=", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLessThan(String value) {
            addCriterion("academy_name <", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLessThanOrEqualTo(String value) {
            addCriterion("academy_name <=", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameLike(String value) {
            addCriterion("academy_name like", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotLike(String value) {
            addCriterion("academy_name not like", value, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameIn(List<String> values) {
            addCriterion("academy_name in", values, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotIn(List<String> values) {
            addCriterion("academy_name not in", values, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameBetween(String value1, String value2) {
            addCriterion("academy_name between", value1, value2, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNameNotBetween(String value1, String value2) {
            addCriterion("academy_name not between", value1, value2, "academyName");
            return (Criteria) this;
        }

        public Criteria andAcademyNumIsNull() {
            addCriterion("academy_num is null");
            return (Criteria) this;
        }

        public Criteria andAcademyNumIsNotNull() {
            addCriterion("academy_num is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyNumEqualTo(String value) {
            addCriterion("academy_num =", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumNotEqualTo(String value) {
            addCriterion("academy_num <>", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumGreaterThan(String value) {
            addCriterion("academy_num >", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumGreaterThanOrEqualTo(String value) {
            addCriterion("academy_num >=", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumLessThan(String value) {
            addCriterion("academy_num <", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumLessThanOrEqualTo(String value) {
            addCriterion("academy_num <=", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumLike(String value) {
            addCriterion("academy_num like", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumNotLike(String value) {
            addCriterion("academy_num not like", value, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumIn(List<String> values) {
            addCriterion("academy_num in", values, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumNotIn(List<String> values) {
            addCriterion("academy_num not in", values, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumBetween(String value1, String value2) {
            addCriterion("academy_num between", value1, value2, "academyNum");
            return (Criteria) this;
        }

        public Criteria andAcademyNumNotBetween(String value1, String value2) {
            addCriterion("academy_num not between", value1, value2, "academyNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("subject_name is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("subject_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("subject_name =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("subject_name <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("subject_name >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("subject_name >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("subject_name <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("subject_name <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("subject_name like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("subject_name not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("subject_name in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("subject_name not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("subject_name between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("subject_name not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIsNull() {
            addCriterion("subject_num is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIsNotNull() {
            addCriterion("subject_num is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNumEqualTo(String value) {
            addCriterion("subject_num =", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotEqualTo(String value) {
            addCriterion("subject_num <>", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumGreaterThan(String value) {
            addCriterion("subject_num >", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumGreaterThanOrEqualTo(String value) {
            addCriterion("subject_num >=", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumLessThan(String value) {
            addCriterion("subject_num <", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumLessThanOrEqualTo(String value) {
            addCriterion("subject_num <=", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumLike(String value) {
            addCriterion("subject_num like", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotLike(String value) {
            addCriterion("subject_num not like", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIn(List<String> values) {
            addCriterion("subject_num in", values, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotIn(List<String> values) {
            addCriterion("subject_num not in", values, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumBetween(String value1, String value2) {
            addCriterion("subject_num between", value1, value2, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotBetween(String value1, String value2) {
            addCriterion("subject_num not between", value1, value2, "subjectNum");
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