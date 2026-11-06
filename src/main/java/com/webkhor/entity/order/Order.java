package com.webkhor.entity.order;

import com.webkhor.entity.base.AuditableEntity;
import com.webkhor.entity.service.ServiceItem;
import com.webkhor.entity.user.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends AuditableEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false, unique = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_item_id", nullable = false, unique = false)
    private ServiceItem serviceItem;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(
            name = "customer_proposed_price",
            nullable = false,
            precision = 18,
            scale = 0
    )
    private BigDecimal customerProposedPrice;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    public Order(
            Customer customer,
            ServiceItem serviceItem,
            String description,
            BigDecimal customerProposedPrice,
            LocalDateTime startAt,
            String address
    ) {
        this.customer = customer;
        this.serviceItem = serviceItem;
        this.description = description;
        this.customerProposedPrice = customerProposedPrice;
        this.startAt = startAt;
        this.address = address;
        this.orderStatus = OrderStatus.WAITING_FOR_OFFERS;
    }

    protected Order() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCustomerProposedPrice() {
        return customerProposedPrice;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public String getAddress() {
        return address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
