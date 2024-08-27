import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FacultyManagerService } from '../Services/faculty-manager.service';
import { Faculty } from '../model/faculty';
import { NewUser } from '../model/NewUser';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {

  faculty = false;
  admin = false;
  student=false;
  message="";
  constructor(private facultyService: FacultyManagerService,private router:Router) {

  }
   userModel = new NewUser("", "", "", "", "", []);
  ngOnInit(): void {

  }
  public addRroles($event: any){
   console.log($event.target.checked +"--->"+$event.target.value);
   if($event.target.checked){
          this.userModel.role.push($event.target.value);
   }
   else{
    if(this.userModel.role.length>0){
      var tempIndex=this.userModel.role.indexOf($event.target.value);
      console.log(tempIndex);
      if(tempIndex>=0){
       console.log( this.userModel.role.splice(tempIndex,1));
      }
    }
   }
   console.log("User Roles-->"+this.userModel.role);
   //this.facultyModel.roles.push(roleName);

  }
  public userRegistration() {
    console.log(this.userModel);
    this.facultyService.addNewUser(this.userModel).subscribe({
      next:data=>{

        console.log(data);
        alert("Data saved sucessfully..");
        this.goHome();
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
      this.router.navigate(['/usermanager']);
    },600);//6sec
       
  }

}
