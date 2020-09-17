package com.lead.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode
@ToString
public class Product {

    private Integer id;
    private String name;
    private String message;


}
