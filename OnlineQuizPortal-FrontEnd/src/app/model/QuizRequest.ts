
export class QuizRequest{

  constructor(
    private  quizId:string,
	private  quizName:string,
	private  facultyId:string,
  private  studentId:string,
	private  facultyName:string,
	private  facultySchoolName:string,
  private requestStatus:string
  ){

  }

}