package com.balkaned.gladius.dashboard.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardCcosto {

    @Id
    private int iexccosto;
    private String iexdesccosto;
    private int cantidad;

}
