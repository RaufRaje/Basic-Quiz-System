package com.sparkl.Quiz_system.Entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

private String question;

private String subject;

private String Questiontype;

@ElementCollection
private List<String> choices;

@ElementCollection
@CollectionTable(name = "question_correct_answers")
private List<String> correctAnswer;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getQuestiontype() {
	return Questiontype;
}

public void setQuestiontype(String questiontype) {
	Questiontype = questiontype;
}

public List<String> getChoices() {
	return choices;
}

public void setChoices(List<String> choices) {
	this.choices = choices;
}

public List<String> getCorrectAnswer() {
	return correctAnswer;
}

public void setCorrectAnswer(List<String> correctAnswer) {
	this.correctAnswer = correctAnswer;
}


}
