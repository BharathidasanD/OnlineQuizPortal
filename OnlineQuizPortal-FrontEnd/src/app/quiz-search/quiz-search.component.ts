import { Component, ElementRef, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { QuizService } from '../Services/quiz.service';
import { Quiz } from '../model/quiz';
import { QuizRequest } from '../model/QuizRequest';
import { StorageService } from '../Services/storage.service';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-quiz-search',
  templateUrl: './quiz-search.component.html',
  styleUrls: ['./quiz-search.component.css']
})
export class QuizSearchComponent {

  @ViewChild('quizIdFetcher')
  searchBox!: ElementRef;

  searchQuiz(){

    //console.log(event.target);
    console.log(this.searchBox.nativeElement.value);
    var quizId=this.searchBox.nativeElement.value;
    this.quizService.getQuizForRegister(quizId).subscribe(
      (response) => {
         console.log("data " + response.length);
      this.dataSource=new MatTableDataSource<QuizRequest>(response);
    }
   );
  }

  //------------------------------------

  
  displayedColumns: string[] = ['S no', 'Quiz Id', 'Quiz Name', 'Faculty Id','Faculty Name','Faculty School Name','Register'];
  dataSource = new MatTableDataSource<QuizRequest>();
   i:number=1;
  @ViewChild(MatPaginator)
  facultyId!: string;
  paginator!: MatPaginator;
  quizId!:string;
  studentId!:string;
   
  constructor(private quizService:QuizService,
    private route: ActivatedRoute,private router:Router,
    private storageService:StorageService,
    private userService:UserService){

  }
  
  ngOnInit(){
    
   var userid= this.storageService.getUserId();
this.userService.getStudentId(userid).subscribe(
    {
      next:data=>{
      this.studentId=data;
      console.log(data);
      },
     error:err=>{

      alert("no student id found...");
      }
    
    
    }
  );
    /*this.route.paramMap.subscribe((params: ParamMap) => {
      let facId = params.get('facId');
      console.log("parsed fac id:"+ facId);
      this.facultyId=facId as string;
    })*/
    
   
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  register(quizRequest:any){
    console.log("recived quiz id "+quizRequest.facultyId
    +quizRequest.facultyName+
    quizRequest.facultySchoolName+
    quizRequest.quizId+
    quizRequest.quizName);

    var newRequest = new QuizRequest(quizRequest.quizId,quizRequest.quizName,quizRequest.facultyId,this.studentId,
      quizRequest.facultyName,quizRequest.facultySchoolName,'Requested');

      console.log(newRequest);

      this.quizService.requestForQuiz(newRequest).subscribe(
        {
          next:data=>{
if(data){
            alert(data+"requested sucessfully");
}
else{
  alert('your data is missing in student record..');
}
          },
          error:err=>{

            alert("not able to request please try again");
          }
        }
      )
      
  

  }
}
