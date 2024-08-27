import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { StorageService } from '../Services/storage.service';
import { MatSidenav } from '@angular/material/sidenav';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  @ViewChild('snav')
  sideNav!: MatSidenav;

  title = 'OnlineLearningPortal';
  headerColor = "blue";
  sColor = "Green";
  tColor = "grey"
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  showFacultyBoard = false;
  username?: string;
  facultyId!:string;
  userType!: String;
  studentOptions: String[] = ['New Test', 'Test History'];
  facultyOptions: String[] = ['New Quiz', 'My Quiz','Request Manager'];
  generalOtions: String[] = ['Faculty', 'Student'];
  adminOptions: String[] = ['Add User', 'Manage User'];
  userId!: string;
  optionsBasedOnUser: String[] = ['About us','Contact us'];


  constructor(private storageService: StorageService, private authService: AuthService, private route: Router
    ,private userService:UserService) { }


  ngOnInit() {
    /* sessionStorage.setItem("userId", "FAC00001");
     this.userId = sessionStorage.getItem("userId") as string;
     this.userType = 'faculty';
     if (this.userType.toLowerCase() === 'student'.toLowerCase()) {
       console.log(this.userType);
       console.log("student");
       this.optionsBasedOnUser = this.studentOptions;
     }
     else if (this.userType.toLowerCase() === 'faculty'.toLowerCase()) {
       console.log("faculty");
       this.optionsBasedOnUser = this.facultyOptions;
     }
     else {
       console.log("other");
       this.optionsBasedOnUser = this.generalOtions;
     }*/

  }


  toggleNav(){
    console.log('toggle nav called...');
    if(this.optionsBasedOnUser.length<=2){
    this.loadnavContents();
    }
    this.sideNav.toggle();
  }

  loadnavContents() {
    console.log("header component load nav components called...");
    this.isLoggedIn = this.storageService.isLoggedIn();
    console.log("is logged in-->" + this.isLoggedIn);
    
    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;
      if(!this.facultyId){
        this.userService.getFacultyId(user.id).subscribe(
         {
          next:data=>{

            console.log(data);
            this.facultyId=data;
          },
          error:err=>{

            console.log(err);
            alert("Something went wrong...");
          }
         }
        )
       }
      console.log(this.roles);
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_STUDENT');
      this.showFacultyBoard = this.roles.includes('ROLE_FACULTY');


      this.roles.forEach(role => {
        switch (role) {
          case 'ROLE_ADMIN':
            this.optionsBasedOnUser = this.optionsBasedOnUser.concat(this.adminOptions);
            break;
          case 'ROLE_FACULTY':
            this.optionsBasedOnUser = this.optionsBasedOnUser.concat(this.facultyOptions);
            break;
          case 'ROLE_STUDENT':
            this.optionsBasedOnUser = this.optionsBasedOnUser.concat(this.studentOptions);
            break;

          default:
            this.optionsBasedOnUser = this.optionsBasedOnUser.concat(this.generalOtions);
            break;
        }
      })



      this.username = user.username;

      /* if (this.showAdminBoard) {
         console.log('admin');
         this.optionsBasedOnUser= this.optionsBasedOnUser.concat(this.adminOptions);
         console.log(this.optionsBasedOnUser);
       }
       else if (this.showModeratorBoard) {
         console.log('stu');
         this.optionsBasedOnUser = this.generalOtions;
       }
       else if(this.showFacultyBoard){
         console.log('fac');
         this.optionsBasedOnUser=this.facultyOptions;
       }

       */
      console.log(this.optionsBasedOnUser);
    }
  }
 
  navigateToNextPage(option: any) {
    console.log("selected option" + option);
    let link;
    if (option === 'Manage User') {
      link = 'usermanager';
      this.route.navigate([link]);
    }
    else if (option === 'New Quiz') {
      link = 'newquiz';
      this.route.navigate([link]);
    }
    else if (option === 'My Quiz') {
      link = 'myquiz';
      console.log(this.facultyId);
      this.route.navigate([link,this.facultyId]);
    }
    else if(option === 'Add User'){
      link='adduser';
      this.route.navigate([link]);
    }
    else if(option === 'New Test'){
      link='registerquiz';
      this.route.navigate([link]);
    }
    else if(option === 'Request Manager'){
      link='requestmanager';
      this.route.navigate([link]);
    }
    
    else {
      link = option.replace(" ", "").toLowerCase() as string;
      this.route.navigate([link]);
    }
    this.sideNav.toggle();

  }

  logout(): void {
    console.log("logged out");
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();
        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
    alert("LOgged out sucessfully..");
    this.route.navigate(['/home']);
  }
}
