import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReferenteListResponse } from '../models/referente-list.interface';
import { environment } from '../enviroment/enviroment';
import { Observable } from 'rxjs';
import { POSTReferenteDTO } from '../models/create-referente.interface';
import { CreatedReferenteResponse } from '../models/create-referente-request.interface';
import { ReferenteDetailsResponse } from '../models/referente-details.interface';
import { ReferenteInstrumentoResponse } from '../models/referente-instrumento.interface';

@Injectable({
  providedIn: 'root',
})
export class ReferentsService {
  constructor(private http: HttpClient) {}

  getReferentesFromAsignatura(
    id: string,
    page: number
  ): Observable<ReferenteListResponse> {
    return this.http.get<ReferenteListResponse>(
      `${environment.apiBaseUrl}teacher/asignatura/${id}/referentes?page=${page}`
    );
  }
  getReferenteById(id:string):Observable<ReferenteDetailsResponse>{
    return this.http.get<ReferenteDetailsResponse>(`${environment.apiBaseUrl}teacher/referente/${id}`);
  }
  editReferente(idAsig:string, id:String, desc:string):Observable<ReferenteDetailsResponse>{
    return this.http.put<ReferenteDetailsResponse>(`${environment.apiBaseUrl}teacher/asignatura/${idAsig}/referente/${id}`, {
      descripcion: desc
    })
  }
  createReferente(id: string, newRef: POSTReferenteDTO): Observable<any> {
    return this.http.post<CreatedReferenteResponse>(
      `${environment.apiBaseUrl}teacher/asignatura/${id}/referente`,
      {
        codReferente: newRef.codReferente,
        descripcion: newRef.descripcion,
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }
  getReferentesInstrumento(id:string):Observable<ReferenteInstrumentoResponse>{
    return this.http.get<ReferenteInstrumentoResponse>(`${environment.apiBaseUrl}student/instrumento/${id}/referentes`);
  }
  deleteRef(id:string):Observable<any>{
    return this.http.delete<any>(`${environment.apiBaseUrl}teacher/referente/${id}`);
  }
}
