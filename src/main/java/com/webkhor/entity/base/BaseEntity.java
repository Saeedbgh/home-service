package com.webkhor.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    protected BaseEntity() {
    }

    public ID getId() {
        return id;
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;

        Class<?> thisClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        Class<?> otherClass = other instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : other.getClass();

        if (thisClass != otherClass) return false;

        BaseEntity<?> that = (BaseEntity<?>) other;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : this.getClass().hashCode();
    }
}
