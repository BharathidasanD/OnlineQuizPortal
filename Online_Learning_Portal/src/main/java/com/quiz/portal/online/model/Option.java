package com.quiz.portal.online.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Option {

	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	@Override
	public String toString() {
		return "Option [option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
				+ ", option5=" + option5 + "]";
	}

}
