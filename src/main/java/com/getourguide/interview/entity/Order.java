package com.getourguide.interview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(schema = "getyourguide", name = "orders")
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @ToString.Exclude
    private Activity activity;
    private String bookingReference;
    private double price;
    private String status;
}
