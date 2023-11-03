package com.quiz.portal.online.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "QUIZ_QUESTIONS_INFO")
public class QuizQuestions {

	@Id
	@GeneratedValue(generator = "question-id-generator")
	@GenericGenerator(name = "question-id-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "question-id-generator", value = "quiz_question_id"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private long questionId;
	private String question;
	private String answer;
	private int maximumMark;
	/*
	 * @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL)
	 * 
	 * @JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
	 * property = "optionId", scope = Option.class)
	 */
	@Embedded
	private Option options;
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "quizId", scope = Quiz.class)
	private Quiz questionBelongsTo;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getMaximumMark() {
		return maximumMark;
	}

	public void setMaximumMark(int maximumMark) {
		this.maximumMark = maximumMark;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Quiz getQuestionBelongsTo() {
		return questionBelongsTo;
	}

	public void setQuestionBelongsTo(Quiz questionBelongsTo) {
		this.questionBelongsTo = questionBelongsTo;
	}

	public Option getOptions() {
		return options;
	}

	public void setOptions(Option options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "QuizQuestions [questionId=" + questionId + ", question=" + question + ", answer=" + answer+", maximumMark=" + maximumMark
				+ ", options=" + options  + ", questionBelongsTo=" + ((questionBelongsTo!=null)?questionBelongsTo.getQuizId():"NA")+"]";
	}

}
