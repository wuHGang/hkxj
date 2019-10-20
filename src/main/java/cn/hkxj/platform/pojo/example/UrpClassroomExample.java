package cn.hkxj.platform.pojo.example;

import java.util.ArrayList;
import java.util.List;

public class UrpClassroomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UrpClassroomExample() {
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

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andSeatsIsNull() {
            addCriterion("seats is null");
            return (Criteria) this;
        }

        public Criteria andSeatsIsNotNull() {
            addCriterion("seats is not null");
            return (Criteria) this;
        }

        public Criteria andSeatsEqualTo(Integer value) {
            addCriterion("seats =", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsNotEqualTo(Integer value) {
            addCriterion("seats <>", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsGreaterThan(Integer value) {
            addCriterion("seats >", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsGreaterThanOrEqualTo(Integer value) {
            addCriterion("seats >=", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsLessThan(Integer value) {
            addCriterion("seats <", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsLessThanOrEqualTo(Integer value) {
            addCriterion("seats <=", value, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsIn(List<Integer> values) {
            addCriterion("seats in", values, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsNotIn(List<Integer> values) {
            addCriterion("seats not in", values, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsBetween(Integer value1, Integer value2) {
            addCriterion("seats between", value1, value2, "seats");
            return (Criteria) this;
        }

        public Criteria andSeatsNotBetween(Integer value1, Integer value2) {
            addCriterion("seats not between", value1, value2, "seats");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameIsNull() {
            addCriterion("teaching_building_name is null");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameIsNotNull() {
            addCriterion("teaching_building_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameEqualTo(String value) {
            addCriterion("teaching_building_name =", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameNotEqualTo(String value) {
            addCriterion("teaching_building_name <>", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameGreaterThan(String value) {
            addCriterion("teaching_building_name >", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("teaching_building_name >=", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameLessThan(String value) {
            addCriterion("teaching_building_name <", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("teaching_building_name <=", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameLike(String value) {
            addCriterion("teaching_building_name like", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameNotLike(String value) {
            addCriterion("teaching_building_name not like", value, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameIn(List<String> values) {
            addCriterion("teaching_building_name in", values, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameNotIn(List<String> values) {
            addCriterion("teaching_building_name not in", values, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameBetween(String value1, String value2) {
            addCriterion("teaching_building_name between", value1, value2, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNameNotBetween(String value1, String value2) {
            addCriterion("teaching_building_name not between", value1, value2, "teachingBuildingName");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberIsNull() {
            addCriterion("teaching_building_number is null");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberIsNotNull() {
            addCriterion("teaching_building_number is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberEqualTo(String value) {
            addCriterion("teaching_building_number =", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberNotEqualTo(String value) {
            addCriterion("teaching_building_number <>", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberGreaterThan(String value) {
            addCriterion("teaching_building_number >", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("teaching_building_number >=", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberLessThan(String value) {
            addCriterion("teaching_building_number <", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberLessThanOrEqualTo(String value) {
            addCriterion("teaching_building_number <=", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberLike(String value) {
            addCriterion("teaching_building_number like", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberNotLike(String value) {
            addCriterion("teaching_building_number not like", value, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberIn(List<String> values) {
            addCriterion("teaching_building_number in", values, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberNotIn(List<String> values) {
            addCriterion("teaching_building_number not in", values, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberBetween(String value1, String value2) {
            addCriterion("teaching_building_number between", value1, value2, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andTeachingBuildingNumberNotBetween(String value1, String value2) {
            addCriterion("teaching_building_number not between", value1, value2, "teachingBuildingNumber");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(Integer value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(Integer value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(Integer value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(Integer value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(Integer value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<Integer> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<Integer> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(Integer value1, Integer value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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