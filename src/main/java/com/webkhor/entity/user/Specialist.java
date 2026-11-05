package com.webkhor.entity.user;

import com.webkhor.entity.service.ServiceItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialists")
public class Specialist extends User {

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecialistStatus status;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "specialist_services",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<ServiceItem> services = new HashSet<>();

    public Specialist(String firstName, String lastName, String email, String passwordHash, byte[] photo) {
        super(firstName, lastName, email, passwordHash);
        this.status = SpecialistStatus.NEW;
        this.photo = photo;
    }

    protected Specialist() {
    }

    public SpecialistStatus getStatus() {
        return status;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Set<ServiceItem> getServices() {
        return services;
    }
}
