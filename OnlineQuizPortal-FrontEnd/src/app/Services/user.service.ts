import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';

//const API_URL = 'http://localhost:8080/api/user/';

const API_URL = '/api';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }
  
  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getAllUsers(): Observable<any> {
    return this.http.get<User[]>(API_URL+'/user/' + 'allusers');
  }

  getUserById(userId:number): Observable<any> {
    return this.http.get<User>(API_URL +'/user/' + 'user/'+userId);
  }

  updateUserRoles(editUser:User):Observable<any>{
    console.log("Recived user-->"+editUser+"user"+editUser.email);
    return this.http.post<User>(API_URL+'/user/' + 'updateuser',editUser,httpOptions);
  }
}