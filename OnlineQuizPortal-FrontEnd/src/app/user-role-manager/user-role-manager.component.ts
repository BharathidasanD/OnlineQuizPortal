import { Component } from '@angular/core';
import { User } from '../model/user';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-user-role-manager',
  templateUrl: './user-role-manager.component.html',
  styleUrls: ['./user-role-manager.component.css']
})
export class UserRoleManagerComponent {

  faculty = false;
  admin = false;
  student = false;
  message = "";
  constructor(private userService: UserService, private router: Router, private activatedRouter:ActivatedRoute) {

  }
  userModel = new User( 0, "","", []);
  userRoles=['ROLE_STUDENT','ROLE_FACULTY','ROLE_ADMIN'];
  tempRoles:string[]=[];
  ngOnInit(): void {

this.activatedRouter.paramMap.subscribe((params: ParamMap) => {
  let userId = 0;
   userId = Number(params.get('userId') );
  console.log("parsed userId id:"+ userId);
  this.userService.getUserById(userId).subscribe({
    next:data=>{
      this.userModel=data;
      this.tempRoles=this.userModel.roles;
    },
    error:error=>{
      alert("Something went wrong..");
    }

  });
})
  }
  public addRroles($event: any) {
    console.log($event.target.checked + "--->" + $event.target.value);
   if($event.target.checked){
    var tempIndex=this.userModel.roles.indexOf($event.target.value);
      if(tempIndex<=0){
          this.userModel.roles.push($event.target.value);
      }
      else{
        console.log("already there..");
      }
   }
   else{
    if(this.userModel.roles.length>0){
      var tempIndex=this.userModel.roles.indexOf($event.target.value);
      console.log(tempIndex);
      if(tempIndex>=0){
       console.log( this.userModel.roles.splice(tempIndex,1));
      }
    }
   }
   console.log("User Roles-->"+this.userModel.roles);

  }
  public saveUser() {
    console.log(this.userModel);
    this.userService.updateUserRoles(this.userModel).subscribe({
      next: data => {
        console.log(data);
      },
      error: err => {
        alert("something went wrong");
        console.log(err);
      }
    });
  }
  public goHome() {
    setTimeout(() => {
      this.router.navigate(['/']);
    }, 6000);//6sec

  }


}
