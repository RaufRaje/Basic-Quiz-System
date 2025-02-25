package com.sparkl.Quiz_system.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparkl.Quiz_system.Entity.Question;
import com.sparkl.Quiz_system.Service.QuestionService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/quizzes")
public class QuestionController {
    @Autowired
	QuestionService service;
    
    
    @PostMapping("/create-new-question")
    public ResponseEntity<Question> CreateQuestion(@Validated @RequestBody Question question){
    	Question createdQuestion=service.CreateQuestion(question);
    	return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }
    
    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestion(){
    	List<Question> questions=service.getAllQuestion();
    	return ResponseEntity.ok(questions);
    	
    }
    
    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable long id) throws NotFoundException{
    	Optional<Question> thequestion=service.getQuestionById(id);
    	if(thequestion.isPresent()) {
    		return ResponseEntity.ok(thequestion.get());
    	}else {
    		throw new ChangeSetPersister.NotFoundException();
    	}
    }
    
    @PutMapping("/question/{id}/update")
    public ResponseEntity<Question> updateQuestion(@PathVariable long id,@RequestBody Question question) throws NotFoundException{
    	Question updatedquestion=service.updateQuetion(id, question);
    	return ResponseEntity.ok(updatedquestion);
    }
    
    
    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id){
    	service.deleteByid(id);
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubject(){
    	List<String> subjects=service.getAllsubject();
    	return ResponseEntity.ok(subjects);
    }
    
    @GetMapping("/quiz/fetch-questions-for-user")
    public ResponseEntity<List<Question>> getQuestionsForUser(@RequestParam int numOfQuestions,@RequestParam String subject){
    	List<Question> allQuestions=service.getQuestionsForUser(numOfQuestions, subject);
    	List<Question> mutableQuestions=new ArrayList<>(allQuestions);
    	Collections.shuffle(mutableQuestions);
    	
    	int availableQuestions=Math.min(numOfQuestions, mutableQuestions.size());
    	List<Question> randomeQuestions=mutableQuestions.subList(0, availableQuestions);
    	return ResponseEntity.ok(randomeQuestions);
    }
    
    
    
    
    
    
    
}
