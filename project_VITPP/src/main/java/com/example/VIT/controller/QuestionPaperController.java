package com.example.VIT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VIT.model.QuestionPaper;
import com.example.VIT.repository.QuestionPaperRepository;
import com.example.VIT.service.QuestionPaperService;

import java.io.IOException;
import java.util.List;


@Controller
public class QuestionPaperController {
	    @Autowired
	    private QuestionPaperRepository repo;

		@Autowired
		private QuestionPaperService questionPaperService;





		@GetMapping("/")
		public String viewHomePage(){
			return "index";
		}

		@PostMapping("/upload")
	    public String uploadFile(@RequestParam("pdf")MultipartFile multipartFile,
		                         @RequestParam("courseCode") String courseCode,
		                         @RequestParam("examName") String examName,
		                         @RequestParam("questionPaperName") String questionPaperName,
		                         RedirectAttributes ra) throws IOException {
			String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
			QuestionPaper questionPaper = new QuestionPaper();

			questionPaper.setContent(multipartFile.getBytes());
			validateContentSize(questionPaper.getContent());
			questionPaper.setSize(multipartFile.getSize());
			questionPaper.setCourseCode(courseCode);
			questionPaper.setExamName(examName);
			questionPaper.setPdf(fileName);
			questionPaper.setQuestionPaperName(questionPaperName);

			repo.save(questionPaper);




			ra.addFlashAttribute("message","File uploaded successfully");

			return "redirect:/";

		}
	  private void validateContentSize(byte[] content) {
	        // Set your maximum allowed size (adjust as needed)
	        int maxAllowedSize = 10 * 1024 * 1024;  // 10 MB

	        if (content.length > maxAllowedSize) {
	            throw new IllegalArgumentException("Content size exceeds the maximum allowed size.");
	        }
	    }
	  
	   @GetMapping("/student") public String getStudentPage() { return "student"; }

		@GetMapping("/question-papers")
		public String getQuestionPapersByExamName(@RequestParam("exam") String examName, Model model) {
			List<QuestionPaper> questionPapers = questionPaperService.getQuestionPapersByExamName(examName);
			model.addAttribute("questionPapers", questionPapers);
			return "question-papers";
		}
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadQuestionPaper(@PathVariable Long id) {
		// Retrieve the QuestionPaper entity by ID
		QuestionPaper questionPaper = questionPaperService.getQuestionPaperById(id);

		// Create a ByteArrayResource from the content of the PDF
		ByteArrayResource resource = new ByteArrayResource(questionPaper.getContent());

		// Set the headers for the response
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + questionPaper.getPdf());
		headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

		// Return the ResponseEntity with the resource and headers
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(questionPaper.getSize())
				.body(resource);
	}








}
