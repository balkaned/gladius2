package com.balkaned.gladius.models;

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
