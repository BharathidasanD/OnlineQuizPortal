import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Quiz } from '../model/quiz';
import { QuizService } from '../Services/quiz.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-my-quiz',
  templateUrl: './my-quiz.component.html',
  styleUrls: ['./my-quiz.component.css']
})
export class MyQuizComponent {

  displayedColumns: string[] = ['S no', 'Quiz Id', 'Quiz Name', 'View'];
  dataSource = new MatTableDataSource<Quiz>();
   i:number=1;
  @ViewChild(MatPaginator)
  facultyId!: string;
  paginator!: MatPaginator;
  quizId!:string;
   
  constructor(private quizService:QuizService,
    private route: ActivatedRoute,private router:Router){

  }
  
  ngOnInit(){
    
    this.route.paramMap.subscribe((params: ParamMap) => {
      let facId = params.get('facId');
      console.log("parsed fac id:"+ facId);
      this.facultyId=facId as string;
    })
    
    this.quizService.getQuizByFacultyId(this.facultyId).subscribe(
      (response) => {
         console.log("data " + response.length);
      this.dataSource=new MatTableDataSource<Quiz>(response);
    }
   );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  navigateToView(quizId:string){
    console.log("recived quiz id "+quizId);
  this.router.navigate(['quizdetail',quizId]);

  }
}
