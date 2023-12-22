import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubjectResponse } from '../models/subject.interface';
import { environment } from '../enviroment/enviroment';
import { PostAsignaturaDTO } from '../models/created-asignatura.interface';
import { CreatedAsignaturaResponse } from '../models/created-asignatura-request.interface';

@Injectable({
  providedIn: 'root',
})
export class SubjectService {
  constructor(private http: HttpClient) {}

  getAsignaturas(page: number): Observable<SubjectResponse> {
    return this.http.get<SubjectResponse>(
      `${environment.apiBaseUrl}teacher/asignatura/?page=${page}`
    );
  }
  createdAsignatura(
    nuevaAsig: PostAsignaturaDTO
  ): Observable<CreatedAsignaturaResponse> {
    return this.http.post<CreatedAsignaturaResponse>(
      `${environment.apiBaseUrl}teacher/asignatura/`,
      {
        nombre: nuevaAsig.nombre,
        horas: nuevaAsig.horas,
        idProfesor: nuevaAsig.idProfesor,
        descripcion: nuevaAsig.descripcion,
        color: nuevaAsig.color,
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }
  getAsignaturaByProfesor(page: number, id: string):Observable<SubjectResponse>{
    return this.http.get<SubjectResponse>(
      `${environment.apiBaseUrl}teacher/${id}/asignatura/?page=${page}`
    );
  }
}
