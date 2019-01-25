package cn.kuqi.Pojo;

import java.util.ArrayList;
import java.util.List;

public class LinkExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public LinkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
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
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
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

        public Criteria andLNumberIsNull() {
            addCriterion("L_Number is null");
            return (Criteria) this;
        }

        public Criteria andLNumberIsNotNull() {
            addCriterion("L_Number is not null");
            return (Criteria) this;
        }

        public Criteria andLNumberEqualTo(Integer value) {
            addCriterion("L_Number =", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberNotEqualTo(Integer value) {
            addCriterion("L_Number <>", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberGreaterThan(Integer value) {
            addCriterion("L_Number >", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("L_Number >=", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberLessThan(Integer value) {
            addCriterion("L_Number <", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberLessThanOrEqualTo(Integer value) {
            addCriterion("L_Number <=", value, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberIn(List<Integer> values) {
            addCriterion("L_Number in", values, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberNotIn(List<Integer> values) {
            addCriterion("L_Number not in", values, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberBetween(Integer value1, Integer value2) {
            addCriterion("L_Number between", value1, value2, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("L_Number not between", value1, value2, "lNumber");
            return (Criteria) this;
        }

        public Criteria andLNameIsNull() {
            addCriterion("L_Name is null");
            return (Criteria) this;
        }

        public Criteria andLNameIsNotNull() {
            addCriterion("L_Name is not null");
            return (Criteria) this;
        }

        public Criteria andLNameEqualTo(String value) {
            addCriterion("L_Name =", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameNotEqualTo(String value) {
            addCriterion("L_Name <>", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameGreaterThan(String value) {
            addCriterion("L_Name >", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameGreaterThanOrEqualTo(String value) {
            addCriterion("L_Name >=", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameLessThan(String value) {
            addCriterion("L_Name <", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameLessThanOrEqualTo(String value) {
            addCriterion("L_Name <=", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameLike(String value) {
            addCriterion("L_Name like", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameNotLike(String value) {
            addCriterion("L_Name not like", value, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameIn(List<String> values) {
            addCriterion("L_Name in", values, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameNotIn(List<String> values) {
            addCriterion("L_Name not in", values, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameBetween(String value1, String value2) {
            addCriterion("L_Name between", value1, value2, "lName");
            return (Criteria) this;
        }

        public Criteria andLNameNotBetween(String value1, String value2) {
            addCriterion("L_Name not between", value1, value2, "lName");
            return (Criteria) this;
        }

        public Criteria andLAddtimeIsNull() {
            addCriterion("L_AddTime is null");
            return (Criteria) this;
        }

        public Criteria andLAddtimeIsNotNull() {
            addCriterion("L_AddTime is not null");
            return (Criteria) this;
        }

        public Criteria andLAddtimeEqualTo(String value) {
            addCriterion("L_AddTime =", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeNotEqualTo(String value) {
            addCriterion("L_AddTime <>", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeGreaterThan(String value) {
            addCriterion("L_AddTime >", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeGreaterThanOrEqualTo(String value) {
            addCriterion("L_AddTime >=", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeLessThan(String value) {
            addCriterion("L_AddTime <", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeLessThanOrEqualTo(String value) {
            addCriterion("L_AddTime <=", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeLike(String value) {
            addCriterion("L_AddTime like", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeNotLike(String value) {
            addCriterion("L_AddTime not like", value, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeIn(List<String> values) {
            addCriterion("L_AddTime in", values, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeNotIn(List<String> values) {
            addCriterion("L_AddTime not in", values, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeBetween(String value1, String value2) {
            addCriterion("L_AddTime between", value1, value2, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLAddtimeNotBetween(String value1, String value2) {
            addCriterion("L_AddTime not between", value1, value2, "lAddtime");
            return (Criteria) this;
        }

        public Criteria andLClickcountIsNull() {
            addCriterion("L_ClickCount is null");
            return (Criteria) this;
        }

        public Criteria andLClickcountIsNotNull() {
            addCriterion("L_ClickCount is not null");
            return (Criteria) this;
        }

        public Criteria andLClickcountEqualTo(Integer value) {
            addCriterion("L_ClickCount =", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountNotEqualTo(Integer value) {
            addCriterion("L_ClickCount <>", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountGreaterThan(Integer value) {
            addCriterion("L_ClickCount >", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("L_ClickCount >=", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountLessThan(Integer value) {
            addCriterion("L_ClickCount <", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountLessThanOrEqualTo(Integer value) {
            addCriterion("L_ClickCount <=", value, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountIn(List<Integer> values) {
            addCriterion("L_ClickCount in", values, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountNotIn(List<Integer> values) {
            addCriterion("L_ClickCount not in", values, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountBetween(Integer value1, Integer value2) {
            addCriterion("L_ClickCount between", value1, value2, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLClickcountNotBetween(Integer value1, Integer value2) {
            addCriterion("L_ClickCount not between", value1, value2, "lClickcount");
            return (Criteria) this;
        }

        public Criteria andLLinkIsNull() {
            addCriterion("L_Link is null");
            return (Criteria) this;
        }

        public Criteria andLLinkIsNotNull() {
            addCriterion("L_Link is not null");
            return (Criteria) this;
        }

        public Criteria andLLinkEqualTo(String value) {
            addCriterion("L_Link =", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkNotEqualTo(String value) {
            addCriterion("L_Link <>", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkGreaterThan(String value) {
            addCriterion("L_Link >", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkGreaterThanOrEqualTo(String value) {
            addCriterion("L_Link >=", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkLessThan(String value) {
            addCriterion("L_Link <", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkLessThanOrEqualTo(String value) {
            addCriterion("L_Link <=", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkLike(String value) {
            addCriterion("L_Link like", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkNotLike(String value) {
            addCriterion("L_Link not like", value, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkIn(List<String> values) {
            addCriterion("L_Link in", values, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkNotIn(List<String> values) {
            addCriterion("L_Link not in", values, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkBetween(String value1, String value2) {
            addCriterion("L_Link between", value1, value2, "lLink");
            return (Criteria) this;
        }

        public Criteria andLLinkNotBetween(String value1, String value2) {
            addCriterion("L_Link not between", value1, value2, "lLink");
            return (Criteria) this;
        }

        public Criteria andLShowIsNull() {
            addCriterion("L_Show is null");
            return (Criteria) this;
        }

        public Criteria andLShowIsNotNull() {
            addCriterion("L_Show is not null");
            return (Criteria) this;
        }

        public Criteria andLShowEqualTo(Integer value) {
            addCriterion("L_Show =", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowNotEqualTo(Integer value) {
            addCriterion("L_Show <>", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowGreaterThan(Integer value) {
            addCriterion("L_Show >", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("L_Show >=", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowLessThan(Integer value) {
            addCriterion("L_Show <", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowLessThanOrEqualTo(Integer value) {
            addCriterion("L_Show <=", value, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowIn(List<Integer> values) {
            addCriterion("L_Show in", values, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowNotIn(List<Integer> values) {
            addCriterion("L_Show not in", values, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowBetween(Integer value1, Integer value2) {
            addCriterion("L_Show between", value1, value2, "lShow");
            return (Criteria) this;
        }

        public Criteria andLShowNotBetween(Integer value1, Integer value2) {
            addCriterion("L_Show not between", value1, value2, "lShow");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table link
     *
     * @mbg.generated do_not_delete_during_merge Wed Jan 16 15:43:06 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table link
     *
     * @mbg.generated Wed Jan 16 15:43:06 CST 2019
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