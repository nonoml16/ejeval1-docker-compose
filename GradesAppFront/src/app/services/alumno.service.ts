import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AlumnoProfesorListResponse } from '../models/alumno-profesor-list.inteface';
import { environment } from '../enviroment/enviroment';
import { POSTAlumnoDTO } from '../models/create-alumno.interface';
import { CreatedAlumnoResponse } from '../models/create-alumno-request.interface';
import { AlumnoDetailsResponse } from '../models/alumno-details.interface';
import { EDITAlumnoDTO } from '../models/edit-alumno-request.interface';

const API_BASE_URL = 'profesor';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  constructor(private http: HttpClient) { }

  getAlumnoProfesor(id: string): Observable<AlumnoProfesorListResponse>{
    return this.http.get<AlumnoProfesorListResponse>(`${environment.apiBaseUrl}${API_BASE_URL}/${id}/alumnos`);
  }

  getAlumnoDetails(id: string): Observable<AlumnoDetailsResponse>{
    return this.http.get<AlumnoDetailsResponse>(`${environment.apiBaseUrl}alumno/${id}`);
  }

  createAlumno(alumno: POSTAlumnoDTO):Observable<CreatedAlumnoResponse>{
    return this.http.post<CreatedAlumnoResponse>(`${environment.apiBaseUrl}alumno/`,
    {
      "nombre": alumno.nombre,
      "apellidos": alumno.apellidos,
      "fechaNacimiento": alumno.fechaNacimiento,
      "email": alumno.email,
      "telefono": alumno.telefono,
      "username": alumno.username,
      "password": alumno.password
    });
  }

  editAlumno(id: string, alumnoAEditar: EDITAlumnoDTO):Observable<AlumnoDetailsResponse>{
    return this.http.put<AlumnoDetailsResponse>(`${environment.apiBaseUrl}alumno/edit/${id}`,
    {
      "nombre": alumnoAEditar.nombre,
      "apellidos": alumnoAEditar.apellidos,
      "email": alumnoAEditar.email,
      "telefono": alumnoAEditar.telefono
    });
  }

  deleteAlumno(id:string):Observable<any>{
    return this.http.delete<any>(`${environment.apiBaseUrl}alumno/delete/${id}`);
  }

  







  getAlumnoByAsignatura(id:string):Observable<any>{
    return this.http.get<any>(`${environment.apiBaseUrl}teacher/asignatura/${id}/alumnos`);
  }
}
