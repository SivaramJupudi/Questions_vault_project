package com.example.VIT.model;


import jakarta.persistence.*;

@Entity
@Table(name="question_paper")

public class QuestionPaper {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "course_code")
	private String courseCode;

	@Column(name = "exam_name")
	private String examName;

	@Column(name = "question_paper_name")
	private String questionPaperName;

	@Column(length=512)
	private String pdf;
	
	@Lob
	@Column(name = "content", nullable = false, columnDefinition = "MEDIUMBLOB")
	private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	private long size;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getQuestionPaperName() {
		return questionPaperName;
	}

	public void setQuestionPaperName(String questionPaperName) {
		this.questionPaperName = questionPaperName;
	}




	public byte[] getPdfBytes() {
		return new byte[0];
	}
}
