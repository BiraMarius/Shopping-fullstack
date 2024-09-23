package com.example.shoppingfullstack.entity;

import com.example.shoppingfullstack.util.DeliveryStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    @Column
    private DeliveryStatus status;

    @OneToOne
    @JoinColumn(name = "deliveryAddress_id")
    private DeliveryAddress deliveryAddress;

    @OneToOne
    @JoinColumn(name="customerContact_id")
    private CustomerContact customerContactInfo;

    public CustomerOrder(ShoppingCart cart, DeliveryStatus status, DeliveryAddress deliveryAddress, CustomerContact customerContactInfo) {
        this.cart = cart;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.customerContactInfo = customerContactInfo;
    }
}
