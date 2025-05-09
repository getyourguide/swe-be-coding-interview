package com.getourguide.interview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(schema="getyourguide", name = "supplier")
@NoArgsConstructor
public class Supplier {
    @Id
    private Long id;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String country;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Activity> activities;
}
