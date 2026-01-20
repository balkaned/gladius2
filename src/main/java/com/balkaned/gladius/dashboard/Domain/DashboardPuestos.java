package com.balkaned.gladius.dashboard.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardPuestos {

    @Id
    private int iexpuesto;
    private String iexdespuesto;
    private int cantidad;

}
