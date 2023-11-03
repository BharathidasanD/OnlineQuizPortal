package com.quiz.portal.online.model;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "QUIZ_INFO")
public class Quiz {

	@Id
	@GenericGenerator(name = "sequence_quiz_id", strategy = "com.quiz.portal.online.idgenerators.QuizIdGenerators")
	@GeneratedValue(generator = "sequence_quiz_id")
	private String quizId;
	private String quizName;
	private String facultyId;
	@OneToMany(mappedBy = "questionBelongsTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIdentityInfo(
//			generator = ObjectIdGenerators.PropertyGenerator.class,
//			property = "questionId", scope = QuizQuestions.class)
	private List<QuizQuestions> listOfQuestions;

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public List<QuizQuestions> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(List<QuizQuestions> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizName=" + quizName + ", facultyId=" + facultyId + ", listOfQuestions="
				+ listOfQuestions + "]";
	}

}
