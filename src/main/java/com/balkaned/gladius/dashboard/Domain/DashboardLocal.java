package com.balkaned.gladius.dashboard.Domain;

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
