package cn.kuqi.Pojo;

import java.util.ArrayList;
import java.util.List;

public class ItemsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public ItemsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
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

        public Criteria andItemnameIsNull() {
            addCriterion("itemName is null");
            return (Criteria) this;
        }

        public Criteria andItemnameIsNotNull() {
            addCriterion("itemName is not null");
            return (Criteria) this;
        }

        public Criteria andItemnameEqualTo(String value) {
            addCriterion("itemName =", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotEqualTo(String value) {
            addCriterion("itemName <>", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThan(String value) {
            addCriterion("itemName >", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThanOrEqualTo(String value) {
            addCriterion("itemName >=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThan(String value) {
            addCriterion("itemName <", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThanOrEqualTo(String value) {
            addCriterion("itemName <=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLike(String value) {
            addCriterion("itemName like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotLike(String value) {
            addCriterion("itemName not like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameIn(List<String> values) {
            addCriterion("itemName in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotIn(List<String> values) {
            addCriterion("itemName not in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameBetween(String value1, String value2) {
            addCriterion("itemName between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotBetween(String value1, String value2) {
            addCriterion("itemName not between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andItempriceIsNull() {
            addCriterion("itemPrice is null");
            return (Criteria) this;
        }

        public Criteria andItempriceIsNotNull() {
            addCriterion("itemPrice is not null");
            return (Criteria) this;
        }

        public Criteria andItempriceEqualTo(String value) {
            addCriterion("itemPrice =", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotEqualTo(String value) {
            addCriterion("itemPrice <>", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceGreaterThan(String value) {
            addCriterion("itemPrice >", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceGreaterThanOrEqualTo(String value) {
            addCriterion("itemPrice >=", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceLessThan(String value) {
            addCriterion("itemPrice <", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceLessThanOrEqualTo(String value) {
            addCriterion("itemPrice <=", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceLike(String value) {
            addCriterion("itemPrice like", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotLike(String value) {
            addCriterion("itemPrice not like", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceIn(List<String> values) {
            addCriterion("itemPrice in", values, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotIn(List<String> values) {
            addCriterion("itemPrice not in", values, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceBetween(String value1, String value2) {
            addCriterion("itemPrice between", value1, value2, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotBetween(String value1, String value2) {
            addCriterion("itemPrice not between", value1, value2, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItemnumberIsNull() {
            addCriterion("itemNumber is null");
            return (Criteria) this;
        }

        public Criteria andItemnumberIsNotNull() {
            addCriterion("itemNumber is not null");
            return (Criteria) this;
        }

        public Criteria andItemnumberEqualTo(Integer value) {
            addCriterion("itemNumber =", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberNotEqualTo(Integer value) {
            addCriterion("itemNumber <>", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberGreaterThan(Integer value) {
            addCriterion("itemNumber >", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("itemNumber >=", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberLessThan(Integer value) {
            addCriterion("itemNumber <", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberLessThanOrEqualTo(Integer value) {
            addCriterion("itemNumber <=", value, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberIn(List<Integer> values) {
            addCriterion("itemNumber in", values, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberNotIn(List<Integer> values) {
            addCriterion("itemNumber not in", values, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberBetween(Integer value1, Integer value2) {
            addCriterion("itemNumber between", value1, value2, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItemnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("itemNumber not between", value1, value2, "itemnumber");
            return (Criteria) this;
        }

        public Criteria andItempicIsNull() {
            addCriterion("itemPic is null");
            return (Criteria) this;
        }

        public Criteria andItempicIsNotNull() {
            addCriterion("itemPic is not null");
            return (Criteria) this;
        }

        public Criteria andItempicEqualTo(String value) {
            addCriterion("itemPic =", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicNotEqualTo(String value) {
            addCriterion("itemPic <>", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicGreaterThan(String value) {
            addCriterion("itemPic >", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicGreaterThanOrEqualTo(String value) {
            addCriterion("itemPic >=", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicLessThan(String value) {
            addCriterion("itemPic <", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicLessThanOrEqualTo(String value) {
            addCriterion("itemPic <=", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicLike(String value) {
            addCriterion("itemPic like", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicNotLike(String value) {
            addCriterion("itemPic not like", value, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicIn(List<String> values) {
            addCriterion("itemPic in", values, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicNotIn(List<String> values) {
            addCriterion("itemPic not in", values, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicBetween(String value1, String value2) {
            addCriterion("itemPic between", value1, value2, "itempic");
            return (Criteria) this;
        }

        public Criteria andItempicNotBetween(String value1, String value2) {
            addCriterion("itemPic not between", value1, value2, "itempic");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table items
     *
     * @mbg.generated do_not_delete_during_merge Mon Aug 20 18:28:51 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table items
     *
     * @mbg.generated Mon Aug 20 18:28:51 CST 2018
     */
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