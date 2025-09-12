package com.example.ProductCatalogService.Models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
public class BaseModel implements Serializable {
    private long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean deleted;
}
