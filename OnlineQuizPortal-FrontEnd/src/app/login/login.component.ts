import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public user!: FormGroup;

  constructor( private formBuilder: FormBuilder){

  }

  ngOnInit(){

    this.user=this.formBuilder.group({
      userName:[''],
      password:['']
    }

    );
  }

  login(){
    console.log("logged in");
  }
}
