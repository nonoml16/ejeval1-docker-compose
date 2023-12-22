import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserResponse } from '../models/user.interface';
import { environment } from '../enviroment/enviroment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createStudent(username: any, fecha: any, email: any, password: any, repeatPassword: any): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${environment.apiBaseUrl}register`,
      {
        "username": username,
        "email": email,
        "password": password,
        "repeatPassword": repeatPassword,
        "date": fecha
      }
    );
  }

  login(username: any, password: any): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}login`,
      {
        "username": username,
        "password": password
      }, httpOptions);
  }
}
