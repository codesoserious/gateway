package com.lead.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class Product {

    private Integer id;
    private String name;
    private String message;


}
