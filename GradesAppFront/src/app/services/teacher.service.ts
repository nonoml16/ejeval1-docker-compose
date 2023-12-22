import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TeacherListResponse } from '../models/teacher-list-response';
import { Observable } from 'rxjs';
import { environment } from '../enviroment/enviroment';
import { TeacherResponse } from '../models/teacher-response.interface';
import { TeacherEditResponse } from '../models/teacher-edit-response-interface';

@Injectable({
  providedIn: 'root',
})
export class TeacherService {
  constructor(private http: HttpClient) {}

  getAllTeacher(): Observable<TeacherListResponse> {
    return this.http.get<TeacherListResponse>(
      `${environment.apiBaseUrl}profesor`
    );
  }

  getTeacherListByPage(page: number): Observable<TeacherListResponse> {
    return this.http.get<TeacherListResponse>(
      `${environment.apiBaseUrl}profesor?page=` + page
    );
  }

  createTeacher(
    username: any,
    email: any,
    password: any,
    repeatPassword: any,
    titulacion: any,
    name: any,
    surname: any
  ): Observable<TeacherResponse> {
    return this.http.post<TeacherResponse>(
      `${environment.apiBaseUrl}profesor/register`,
      {
        username: username,
        nombre: name,
        apellidos: surname,
        email: email,
        titulacion: titulacion,
        repeatPassword: repeatPassword,
        password: password,
      }
    );
  }

  editTeacher(id: any, titulacion: any, name: any, surname: any): Observable<TeacherEditResponse>{
    return this.http.put<TeacherEditResponse>(`${environment.apiBaseUrl}profesor/${id}/edit`,
    {
      "apellidos": surname,
      "titulacion": titulacion,
      "nombre": name
  }
    );
  }

  deleteTeacher(id: any): Observable<any> {
    return this.http.delete<any>(`${environment.apiBaseUrl}profesor/${id}`)
  }
}
