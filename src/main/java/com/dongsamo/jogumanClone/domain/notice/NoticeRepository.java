package com.dongsamo.jogumanClone.domain.notice;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAll();
}
