package com.balkaned.gladius.dashboard.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardSexoPie {

    @Id
    private int cantidad_m;
    private int cantidad_f;
    private int cantidad_ma;
    private int cantidad_total;

}
