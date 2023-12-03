import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
 
  userType!: String;
  studentOptions: String[] = ['New Test', 'Test History'];
  facultyOptions: String[] = ['New Quiz', 'My Quiz'];
  generalOtions: String[] = ['Faculty', 'Student']
  optionsBasedOnUser!:String[];

  constructor() {

  }

  ngOnInit() {
this.userType='faculty';
if(this.userType.toLowerCase() === 'student'.toLowerCase()){
  console.log(this.userType);
  console.log("student");
  this.optionsBasedOnUser=this.studentOptions;
}
else if(this.userType.toLowerCase() === 'faculty'.toLowerCase()){
  console.log("faculty");
  this.optionsBasedOnUser=this.facultyOptions;
}
else{
  console.log("other");
  this.optionsBasedOnUser=this.generalOtions;
}

  }
}
