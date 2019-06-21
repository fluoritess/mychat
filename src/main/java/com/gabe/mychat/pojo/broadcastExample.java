package com.gabe.mychat.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class broadcastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public broadcastExample() {
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

        public Criteria andBroadcastIdIsNull() {
            addCriterion("broadcast_id is null");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdIsNotNull() {
            addCriterion("broadcast_id is not null");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdEqualTo(Integer value) {
            addCriterion("broadcast_id =", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdNotEqualTo(Integer value) {
            addCriterion("broadcast_id <>", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdGreaterThan(Integer value) {
            addCriterion("broadcast_id >", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("broadcast_id >=", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdLessThan(Integer value) {
            addCriterion("broadcast_id <", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdLessThanOrEqualTo(Integer value) {
            addCriterion("broadcast_id <=", value, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdIn(List<Integer> values) {
            addCriterion("broadcast_id in", values, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdNotIn(List<Integer> values) {
            addCriterion("broadcast_id not in", values, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdBetween(Integer value1, Integer value2) {
            addCriterion("broadcast_id between", value1, value2, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastIdNotBetween(Integer value1, Integer value2) {
            addCriterion("broadcast_id not between", value1, value2, "broadcastId");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentIsNull() {
            addCriterion("broadcast_content is null");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentIsNotNull() {
            addCriterion("broadcast_content is not null");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentEqualTo(String value) {
            addCriterion("broadcast_content =", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentNotEqualTo(String value) {
            addCriterion("broadcast_content <>", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentGreaterThan(String value) {
            addCriterion("broadcast_content >", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentGreaterThanOrEqualTo(String value) {
            addCriterion("broadcast_content >=", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentLessThan(String value) {
            addCriterion("broadcast_content <", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentLessThanOrEqualTo(String value) {
            addCriterion("broadcast_content <=", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentLike(String value) {
            addCriterion("broadcast_content like", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentNotLike(String value) {
            addCriterion("broadcast_content not like", value, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentIn(List<String> values) {
            addCriterion("broadcast_content in", values, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentNotIn(List<String> values) {
            addCriterion("broadcast_content not in", values, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentBetween(String value1, String value2) {
            addCriterion("broadcast_content between", value1, value2, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastContentNotBetween(String value1, String value2) {
            addCriterion("broadcast_content not between", value1, value2, "broadcastContent");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeIsNull() {
            addCriterion("broadcast_time is null");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeIsNotNull() {
            addCriterion("broadcast_time is not null");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeEqualTo(Date value) {
            addCriterion("broadcast_time =", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeNotEqualTo(Date value) {
            addCriterion("broadcast_time <>", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeGreaterThan(Date value) {
            addCriterion("broadcast_time >", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("broadcast_time >=", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeLessThan(Date value) {
            addCriterion("broadcast_time <", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeLessThanOrEqualTo(Date value) {
            addCriterion("broadcast_time <=", value, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeIn(List<Date> values) {
            addCriterion("broadcast_time in", values, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeNotIn(List<Date> values) {
            addCriterion("broadcast_time not in", values, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeBetween(Date value1, Date value2) {
            addCriterion("broadcast_time between", value1, value2, "broadcastTime");
            return (Criteria) this;
        }

        public Criteria andBroadcastTimeNotBetween(Date value1, Date value2) {
            addCriterion("broadcast_time not between", value1, value2, "broadcastTime");
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