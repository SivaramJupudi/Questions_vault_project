package com.example.VIT;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.VIT.model.QuestionPaper;
import com.example.VIT.repository.QuestionPaperRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectQPApplicationTests {
	@Autowired
	private QuestionPaperRepository repo;

    @Autowired
	private TestEntityManager entityManager;


	@Test
	@Rollback(false)
	void testInsertDocument() throws IOException {
		File file=new File("D:\\Document\\Hill.pdf");
		QuestionPaper questionPaper=new QuestionPaper();
		questionPaper.setPdf(file.getPath());

		byte[] bytes=Files.readAllBytes(file.toPath());
		questionPaper.setContent(bytes);
		long fileSize= bytes.length;
		questionPaper.setSize(fileSize);


 		QuestionPaper savedDoc=repo.save(questionPaper);

		QuestionPaper existDoc=entityManager.find(QuestionPaper.class,savedDoc.getId());
		assertThat(existDoc.getSize()).isEqualTo(fileSize);



	}
}