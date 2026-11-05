package com.webkhor.entity.service;

import com.webkhor.entity.base.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.math.BigDecimal;

@Entity
@Table(
        name = "service_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_service_items_parent_name",
                        columnNames = {"parent_id", "name"}
                )
        }
)
public class ServiceItem extends AuditableEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_price", nullable = false, precision = 18, scale = 0)
    private BigDecimal basePrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private ServiceItem parent;

    public ServiceItem(String name, BigDecimal basePrice, String description, ServiceItem parent) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.parent = parent;
    }

    protected ServiceItem() {
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public String getDescription() {
        return description;
    }

    public ServiceItem getParent() {
        return parent;
    }
}
