import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Faculty } from '../model/faculty';
import { NewUser } from '../model/NewUser';


const API_URL = '/api/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FacultyManagerService {

 
  constructor(private http: HttpClient) {}

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'factest', { responseType: 'text' });
  }

  addNewUser(newUser:NewUser): Observable<any> {
    console.log("recived user--->"+JSON.stringify(newUser))
    return this.http.post(API_URL + 'user/adduser', 
     newUser
    ,
    httpOptions
    );
  }
  
  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
}
