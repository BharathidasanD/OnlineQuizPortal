import { AfterViewChecked, AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { StorageService } from './Services/storage.service';
import { AuthService } from './Services/auth.service';
import { HeaderComponent } from './header/header.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})
export class AppComponent  {
  title = 'OnlineLearningPortal';
  headerColor="blue";
  sColor="Green";
  tColor="grey"
  private roles: string[] = [];
  isLoggedIn = false;
  username?: string;

  @ViewChild('headercomp')
  headerComp!: HeaderComponent;

  constructor(private storageService: StorageService, private authService: AuthService) { }
 /* ngAfterViewInit(): void {
    console.log(this.headerComp);
    console.log('after view init called...');
    if(this.storageService.isLoggedIn()){
      console.log("from app logged in");
    this.headerComp.loadnavContents();
    }
    
  }*/

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;
      this.username = user.username;
    }
  }

}