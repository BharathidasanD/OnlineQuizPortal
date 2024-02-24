import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { FacultyManagerService } from '../Services/faculty-manager.service';
import { Faculty } from '../model/faculty';

@Component({
  selector: 'app-add-faculty',
  templateUrl: './add-faculty.component.html',
  styleUrls: ['./add-faculty.component.css']
})
export class AddFacultyComponent  implements OnInit {

  faculty = false;
  admin = false;
  student=false;
  message="";
  constructor(private facultyService: FacultyManagerService,private router:Router) {

  }
   facultyModel = new Faculty("", "", "", "", "", []);
  ngOnInit(): void {

  }
  public addRroles($event: any){
   console.log($event.target.checked +"--->"+$event.target.value);
   if($event.target.checked){
          this.facultyModel.roles.push($event.target.value);
   }
   else{
    if(this.facultyModel.roles.length>0){
      var tempIndex=this.facultyModel.roles.indexOf($event.target.value);
      console.log(tempIndex);
      if(tempIndex>=0){
       console.log( this.facultyModel.roles.splice(tempIndex,1));
      }
    }
   }
   console.log("User Roles-->"+this.facultyModel.roles);
   //this.facultyModel.roles.push(roleName);

  }
  public userRegistration() {
    console.log(this.facultyModel);
    this.facultyService.addNewFaculty(this.facultyModel).subscribe({
      next:data=>{

        console.log(data);
      },
     error:err=>{
alert("something went wrong")
      console.log(err);
      }
    });
  }
  public goHome()
  {
    setTimeout(()=>{
      this.router.navigate(['/']);
    },6000);//6sec
       
  }


}