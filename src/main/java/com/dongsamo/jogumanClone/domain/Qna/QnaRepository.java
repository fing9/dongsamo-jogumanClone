package com.dongsamo.jogumanClone.domain.Qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findAll();
}
