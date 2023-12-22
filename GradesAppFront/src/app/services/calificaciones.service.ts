import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CalificacionesInstrumentoResponse } from '../models/calificaciones-instrumento.interface';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../enviroment/enviroment';
import { POSTCalificacionDTO } from '../models/create-calificacion.interface';
import { AlumnoDetailsResponse } from '../models/alumno-details.interface';

@Injectable({
  providedIn: 'root'
})
export class CalificacionesService {

  constructor(private http: HttpClient) { }

  getInstrumentoDetails(id:string):Observable<CalificacionesInstrumentoResponse>{
    return this.http.get<CalificacionesInstrumentoResponse>(`${environment.apiBaseUrl}teacher/instrumento/${id}/calificaciones`);
  }
  getCalificacionsAlumno(id:string):Observable<CalificacionesInstrumentoResponse>{
    return this.http.get<CalificacionesInstrumentoResponse>(`${environment.apiBaseUrl}student/instrumento/${id}/calificaciones`);
  }
  createCalificacion(id:string, newCalf:POSTCalificacionDTO):Observable<any>{
    return this.http.post<any>(`${environment.apiBaseUrl}teacher/instrumento/${id}/calificacion`,{
      codRef: newCalf.codRef,
      idAlumno: newCalf.idAlumno,
      calificacion: newCalf.calificacion
    });
  }
  deleteCalificacion(id:string):Observable<AlumnoDetailsResponse[]>{
    return this.http.delete<AlumnoDetailsResponse[]>(`${environment.apiBaseUrl}teacher/calificacion/${id}`);
  }
}
