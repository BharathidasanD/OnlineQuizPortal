import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Quiz } from '../model/quiz';
import { QuizService } from '../Services/quiz.service';
import { Router } from '@angular/router';
import { UserService } from '../Services/user.service';
import { StorageService } from '../Services/storage.service';


@Component({
  selector: 'app-create-new-quiz',
  templateUrl: './create-new-quiz.component.html',
  styleUrls: ['./create-new-quiz.component.css']
})
export class CreateNewQuizComponent {
  public myQuiz!: Quiz;
  public errMsg!: string;
  public newQuiz!: FormGroup;
  responseStatus: Object = [];
  public localFacId!: string;
  currentTab = 1;
  constructor(private quizService: QuizService, private formBuilder: FormBuilder,
    private route: Router, private userService: UserService, private storageService: StorageService) {

  }
  ngOnInit() {
    this.newQuiz = this.formBuilder.group({
      quizName: [''],
      facultyId: [this.localFacId],
      listOfQuestions: this.formBuilder.array([

      ])

    });
    this.userService.getFacultyId(this.storageService.getUserId()).subscribe(
      {
        next: data => {

          console.log("new quiz comp" + data);
          this.localFacId = data;
          this.newQuiz = this.formBuilder.group({
            quizName: [''],
            facultyId: [this.localFacId],
            listOfQuestions: this.formBuilder.array([

            ])

          });
          this.addQuestion();
        },
        error: err => {

          console.log(err);
          alert("Something went wrong...");
        }
      }
    )

   


  }
  get listOfQuestions(): FormArray {
    return this.newQuiz.get('listOfQuestions') as FormArray;
  }

  addQuestion() {
    console.log("add question called");
    const question = this.formBuilder.group({
      question: [''],
      answer: [''],
      maximumMark: [''],
      options:
        this.formBuilder.group({
          option1: [''],
          option2: [''],
          option3: [''],
          option4: [''],
          option5: ['']
        })

    });
    this.listOfQuestions.push(question);
    let len = this.listOfQuestions.length;
    len++;
    this.currentTab = len;

  }
  removeQuestion(questionNo: number) {
    if (this.currentTab > 0) {
      this.listOfQuestions.removeAt(questionNo);
      this.currentTab--;
    }
  }

  submit() {
    console.log("Submitted DATA")
    console.log(this.newQuiz.value.listOfQuestions.forEach(
      (a: any) => console.log(a)
    ));
    this.myQuiz = this.newQuiz.value;
    console.log("transformed-->" + this.myQuiz.quizName);
    console.log("facid-->" + this.myQuiz.facultyId);
    this.quizService.createNewQuiz(this.myQuiz).subscribe(
      data => {

        if (data.facultyId !== null && data.facultyId.length > 3) {
          this.route.navigate(['/myquiz', data.facultyId]);
        }
      },
      error => console.error(error)
    );
  }
}
