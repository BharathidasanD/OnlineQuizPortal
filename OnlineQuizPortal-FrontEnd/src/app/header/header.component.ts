import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { StorageService } from '../Services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  title = 'OnlineLearningPortal';
  headerColor = "blue";
  sColor = "Green";
  tColor = "grey"
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  showFacultyBoard=false;
  username?: string;

  userType!: String;
  studentOptions: String[] = ['New Test', 'Test History'];
  facultyOptions: String[] = ['New Quiz', 'My Quiz'];
  generalOtions: String[] = ['Faculty', 'Student'];
  adminOptions: String[] = ['Add User', 'Manage User'];
  userId!: string;
  optionsBasedOnUser: String[] = ['Test', 'Test2'];


  constructor(private storageService: StorageService, private authService: AuthService, private route: Router) { }


  ngOnInit() {

    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_STUDENT');
      this.showFacultyBoard=this.roles.includes('ROLE_FACULTY');

      this.username = user.username;

      if (this.showAdminBoard) {
        this.optionsBasedOnUser.concat(this.adminOptions);
      }
      else if (this.showModeratorBoard) {
        this.optionsBasedOnUser = this.generalOtions;
      }
      else if(this.showFacultyBoard){
        this.optionsBasedOnUser=this.facultyOptions;
      }
    }



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

  navigateToNextPage(option: any) {
    console.log("selected option" + option);
    let link;
    if (option == this.facultyOptions[1]) {
      link = option.replace(" ", "").toLowerCase() as string;
      this.route.navigate([link, this.userId]);
    }
    else {
      link = option.replace(" ", "").toLowerCase() as string;
      this.route.navigate([link]);
    }

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
