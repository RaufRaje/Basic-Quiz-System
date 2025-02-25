package com.sparkl.Quiz_system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sparkl.Quiz_system.Entity.Question;
import com.sparkl.Quiz_system.Repository.QuestionRepository;

@Repository
@Service
public class QuestionService {
	@Autowired
	QuestionRepository repo;

	public Question CreateQuestion(Question question) {
		return repo.save(question);
	}
	
	public List<Question> getAllQuestion(){
		return repo.findAll();
	}
	
	public Optional<Question> getQuestionById(long id){
		return repo.findById(id);
	}
	
	public List<String> getAllsubject(){
		return repo.findDistinctSubject();
	}
	 
	
	public Question updateQuetion(long id,Question question) throws NotFoundException {
		Optional<Question> theQuestion=getQuestionById(id);
		if(theQuestion.isPresent()) {
			Question updatedQuestion= theQuestion.get();
			updatedQuestion.setQuestion(question.getQuestion());
			updatedQuestion.setChoices(question.getChoices());
			updatedQuestion.setCorrectAnswer(question.getCorrectAnswer());
			return repo.save(updatedQuestion);
		}else {
			throw new ChangeSetPersister.NotFoundException();
		}
	}
	
	public void deleteByid(long id) {
		repo.deleteById(id);
	}
	
	public List<Question> getQuestionsForUser(int numberofQuestions, String subject){
		Pageable pageable=PageRequest.of(0, numberofQuestions);
		return repo.findBySubject(subject,pageable).getContent();
		
	}
	
	
}
