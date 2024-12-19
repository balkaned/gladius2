package com.balkaned.gladius.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DashboardAreaBar {

    @Id
    private int codarea;
    private String desarea;
    private int cantidad;

}
