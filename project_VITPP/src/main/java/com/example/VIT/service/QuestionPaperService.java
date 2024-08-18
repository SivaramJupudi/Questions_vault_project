package com.example.VIT.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VIT.model.QuestionPaper;
import com.example.VIT.repository.QuestionPaperRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionPaperService {

	@Autowired
	private QuestionPaperRepository questionPaperRepository;

	public List<QuestionPaper> getQuestionPapersByExamName(String examName) {
		System.out.println("Received exam name: " + examName);
		List<QuestionPaper> questionPapers = questionPaperRepository.findByExamName(examName);
		System.out.println("Number of question papers found: " + questionPapers.size());
		return questionPapers;
	}

	public QuestionPaper getQuestionPaperById(Long id) {
		return questionPaperRepository.findById(id).orElse(null);
	}
}
