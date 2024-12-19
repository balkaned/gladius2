package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardLocal {

    @Id
    private int iexubicod;
    private String iexubides;
    private int cantidad;

}
