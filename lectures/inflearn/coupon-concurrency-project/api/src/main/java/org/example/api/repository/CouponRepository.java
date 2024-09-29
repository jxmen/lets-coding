package org.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.api.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
