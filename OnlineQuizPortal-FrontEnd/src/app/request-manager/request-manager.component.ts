import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { QuizService } from '../Services/quiz.service';
import { Quiz } from '../model/quiz';
import { QuizRequestStatus } from '../model/QuizRequestStatus';
import { UserService } from '../Services/user.service';
import { StorageService } from '../Services/storage.service';

@Component({
  selector: 'app-request-manager',
  templateUrl: './request-manager.component.html',
  styleUrls: ['./request-manager.component.css']
})
export class RequestManagerComponent {

  displayedColumns: string[] = ['S no', 'Quiz Id', 'Student Id' ,'Request Status','Faculty Id', 'Quiz Name'];
  dataSource = new MatTableDataSource<QuizRequestStatus>();
   i:number=1;
  @ViewChild(MatPaginator)
  facultyId!: string;
  paginator!: MatPaginator;
  quizId!:string;
   
  constructor(private quizService:QuizService,
    private userService:UserService,
    private storageService:StorageService){

  }
  
  ngOnInit(){
    
    this.userService.getFacultyId(this.storageService.getUserId()).subscribe(
      {
        next: data => {
          this.facultyId = data;
          this.quizService.getMyQuizRequest(this.facultyId).subscribe(
            (response) => {
               console.log("data " + response.length);
            this.dataSource=new MatTableDataSource<QuizRequestStatus>(response);
          }
         );
        },
        error: err => {
          console.log(err);
          alert("Something went wrong...");
        }
      }
    )
    
   
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}
