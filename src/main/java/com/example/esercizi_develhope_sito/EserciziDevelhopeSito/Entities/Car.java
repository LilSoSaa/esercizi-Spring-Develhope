package com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Entities;
/*
ha una entità chiamata Car con le seguenti colonne:
id
modelName - obbligatorio
type - Enum - obbligatorio
color - Enum - obbligatorio
description - String- non obbligatorio */

import com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Enumerates.CarColor;
import com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Enumerates.CarType;
import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "modelName", nullable = false)
    private String modelName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private CarColor color;

    @Column(name = "description")
    private String description;

    public Car () {}

    public Car(Long id, String modelName, CarType type, CarColor color, String description) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
        this.color = color;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
