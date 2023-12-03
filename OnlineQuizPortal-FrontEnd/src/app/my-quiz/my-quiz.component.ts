import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Quiz } from '../model/quiz';
import { QuizService } from '../Services/quiz.service';

@Component({
  selector: 'app-my-quiz',
  templateUrl: './my-quiz.component.html',
  styleUrls: ['./my-quiz.component.css']
})
export class MyQuizComponent {

  displayedColumns: string[] = ['S no', 'Quiz Id', 'Quiz Name'];
  dataSource = new MatTableDataSource<Quiz>();
   i:number=1;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private quizService:QuizService){

  }
  
  ngOnInit(){
    this.quizService.getQuizByFacultyId('Fac1001').subscribe(
      (response) => {
         console.log("data " + response.length);
      this.dataSource=new MatTableDataSource<Quiz>(response);
    }
   );
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}
