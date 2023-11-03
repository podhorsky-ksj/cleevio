package com.example.cleevio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@ToString
@Table
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    @NotEmpty(message = "Name is mandatory")
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    String name;

    @Column
    @NotEmpty(message = "Description is mandatory")
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    String description;

    @Column
    @Min(value = 0, message = "Product price must be greater or equal to 0")
    Double price;

    @Column
    @DateTimeFormat
    LocalDateTime date;

    public Product(final String name, final String description, final Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}
