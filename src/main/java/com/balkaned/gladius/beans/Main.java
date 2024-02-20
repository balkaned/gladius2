package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Main {

    private double val = -1;

    public void setValue(double x) {
        val = x;
    }

    public double getValue() {
        return val;
    }

}
