import { Component, ElementRef, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../Services/quiz.service';
import { StorageService } from '../Services/storage.service';
import { UserService } from '../Services/user.service';
import { QuizRequest } from '../model/QuizRequest';

@Component({
  selector: 'app-quiz-request-status',
  templateUrl: './quiz-request-status.component.html',
  styleUrls: ['./quiz-request-status.component.css']
})
export class QuizRequestStatusComponent {

 /* @ViewChild('quizIdFetcher')
  searchBox!: ElementRef;

  searchQuiz() {

    //console.log(event.target);
    console.log(this.searchBox.nativeElement.value);
    var quizId = this.searchBox.nativeElement.value;
    this.quizService.getQuizForRegister(quizId).subscribe(
      (response) => {
        console.log("data " + response.length);
        this.dataSource = new MatTableDataSource<QuizRequest>(response);
      }
    );
  }*/

  //------------------------------------


  displayedColumns: string[] = ['S no', 'Quiz Id', 'Student Id', 'Status'];
  dataSource = new MatTableDataSource<QuizRequest>();
  i: number = 1;
  @ViewChild(MatPaginator)
  facultyId!: string;
  paginator!: MatPaginator;
  quizId!: string;
  studentId!: string;

  constructor(private quizService: QuizService,
    private route: ActivatedRoute, private router: Router,
    private storageService: StorageService,
    private userService: UserService) {

  }

  ngOnInit() {

    var userid = this.storageService.getUserId();
    this.userService.getStudentId(userid).subscribe(
      {
        next: data => {
          if(this.studentId){
          this.studentId = data;
          console.log("student id status-->"+ typeof data);
            console.log("valid");
          }
          else{
            alert("no student id found...");
          }
        },
        error: err => {

          alert("no student id found...");
        }


      }
    );

    this.quizService.getQuizRequestByStudentId(this.studentId).subscribe({
      next:data=>{
if(data){
        this.dataSource=new MatTableDataSource<QuizRequest>(data);
}
else{
  alert('you dont have student record in our database..');
}
      },
      error:err=>{

        alert("not able to find your request");
      }
    })

    /*this.route.paramMap.subscribe((params: ParamMap) => {
      let facId = params.get('facId');
      console.log("parsed fac id:"+ facId);
      this.facultyId=facId as string;
    })*/


  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  /*register(quizRequest: any) {
    console.log("recived quiz id " + quizRequest.facultyId
      + quizRequest.facultyName +
      quizRequest.facultySchoolName +
      quizRequest.quizId +
      quizRequest.quizName);

    var newRequest = new QuizRequest(quizRequest.quizId, quizRequest.quizName, quizRequest.facultyId, this.studentId,
      quizRequest.facultyName, quizRequest.facultySchoolName, 'Requested');

    console.log(newRequest);

    if(this.studentId){
    this.quizService.requestForQuiz(newRequest).subscribe(
      {
        next: data => {
          if (data) {
            alert(data + "requested sucessfully");
          }
          else {
            alert('your data is missing in student record..');
          }
        },
        error: err => {

          alert("not able to request please try again");
        }
      }
    );
    }



  }*/
}
