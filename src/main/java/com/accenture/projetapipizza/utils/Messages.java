package com.accenture.projetapipizza.utils;

public class Messages {

    private Messages() {
        throw new IllegalStateException("Utility class");
    }


    // CUSTOMER
    public static final String CUSTOMER_NOT_NULL = "customer.not.null";
    public static final String CUSTOMER_ID_NOT_NULL = "customer.id.not.null";
    public static final String CUSTOMER_NAME_NOT_NULL = "customer.name.not.null";
    public static final String CUSTOMER_NAME_SAME_AS_EXPECTED = "customer.name.same.as.expected";
    public static final String CUSTOMER_EMAIL_NOT_NULL = "customer.email.not.null";
    public static final String CUSTOMER_EMAIL_NOT_VALID = "customer.email.not.valid";
    public static final String CUSTOMER_EMAIL_SAME_AS_EXPECTED = "customer.email.same.as.expected";
    public  static final String CUSTOMER_EMAIL_ALREADY_EXISTS = "customer.email.already.exist";
    public static final String CUSTOMER_CREATED = "customer.created";
    public  static final String CUSTOMER_DTO_RESPONSE_NOT_NULL = "customer.dto.response.not.null";
    public  static final String CUSTOMER_RESPONSE_NAME_MATCH_REQUEST_NAME = "customer.response.name.match.request.name";
    public  static final String CUSTOMER_RESPONSE_EMAIL_MATCH_REQUEST_EMAIL = "customer.response.email.match.request.email";
    public  static final String CUSTOMER_LIST_ORDER_SAME_AS_EXPECTED = "customer.list.order.same.as.expected";
    public  static final String CUSTOMER_BAD_REQUEST = "customer.bad.request";



    // PIZZA
    public static final String PIZZA_ELEMENT_NOT_VALID ="pizza.element.not.valid";
    public static final String PIZZA_NAME_NOT_VALID = "pizza.name.not.valid";
    public static final String PIZZA_SIZE_NOT_VALID = "pizza.size.not.valid";
    public static final String PIZZA_LISTINGREDIENT_NOT_VALID = "pizza.listIngredient.not.valid";
    public static final String PIZZA_ACTIVE_NOT_VALID = "pizza.active.not.valid";
    public static final String PIZZA_SIZECHOICE_NOT_VALID = "pizza.sizeChoice.not.valid";
    public static final String PIZZA_PRICE_NOT_VALID="pizza.price.not.valid";
    public static final String PIZZA_ADD_IS_VALID="pizza.add.is.valid";
    public static final String PIZZA_IS_NOT_EXIST="pizza.is.not.exist";
    public static final String PIZZA_DELETE_UUID_IS_NULL="pizza.delete.uuid.is.null";
    public static final String PIZZA_DELETE="pizza.delete";


    // ORDER
    public static final String ORDER_NOT_NULL="order.not.null";
    public static final String ORDER_STATUS_NOT_NULL="order.status.not.null";
    public static final String ORDER_LIST_PIZZA_NAME_NOT_EMPTY="order.list.pizza.name.not.empty";
    public static final String ORDER_CUSTOMER_EMAIL_NOT_NULL="order.customer.email.not.null";
    public static final String ORDER_ID_NOT_NULL="order.id.not.null";
    public static final String ORDER_PRICE_NOT_NULL="order.price.not.null";
    public static final String ORDER_PIZZAS_NOT_NULL="order.pizzas.not.null";
    public static final String ORDER_STATUS_SAME_AS_EXPECTED="order.status.same.as.expected";
    public static final String ORDER_PIZZA_NAME_SAME_AS_EXPECTED="order.pizza.name.same.as.expected";
    public static final String ORDER_PRICE_SAME_AS_EXPECTED="order.price.same.as.expected";


}
