import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
 
  userType!: String;
  studentOptions: String[] = ['New Test', 'Test History'];
  facultyOptions: String[] = ['New Quiz', 'My Quiz'];
  generalOtions: String[] = ['Faculty', 'Student'];
  userId!: string;
  optionsBasedOnUser!:String[];

constructor(private route:Router){}

  ngOnInit() {

      sessionStorage.setItem("userId","FAC00001");
  this.userId=sessionStorage.getItem("userId") as string;
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

  navigateToNextPage(option:any){
    console.log("selected option"+ option);
    let link;
    if(option == this.facultyOptions[1]){
    link=option.replace(" ","").toLowerCase() as string;
    this.route.navigate([link,this.userId]);
    }
    else{
      link=option.replace(" ","").toLowerCase() as string;
      this.route.navigate([link]);
    }
   
  }
}
