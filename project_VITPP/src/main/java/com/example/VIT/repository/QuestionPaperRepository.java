package com.example.VIT.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VIT.model.QuestionPaper;

import java.util.List;

@Repository()
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long> {


	List<QuestionPaper> findByExamName(String examName);
}
