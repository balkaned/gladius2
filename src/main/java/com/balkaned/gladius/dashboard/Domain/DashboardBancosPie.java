package com.balkaned.gladius.dashboard.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardBancosPie {

    @Id
    private int codtab;
    private String desdet;
    private int cantidad;

}
