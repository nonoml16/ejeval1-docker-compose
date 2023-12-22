import { Component, Input, OnInit, TemplateRef, inject } from '@angular/core';
import { environment } from '../../enviroment/enviroment';
import { CalificacionesService } from '../../services/calificaciones.service';
import { Alumno, Calificacion, Referente } from '../../models/calificaciones-instrumento.interface';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { POSTCalificacionDTO } from '../../models/create-calificacion.interface';
import { AlumnoService } from '../../services/alumno.service';
import { AlumnoDetailsResponse } from '../../models/alumno-details.interface';
import { error } from 'console';
import { Duplex } from 'stream';

@Component({
  selector: 'app-teacher-calificacion-table',
  templateUrl: './teacher-calificacion-table.component.html',
  styleUrl: './teacher-calificacion-table.component.css'
})
export class TeacherCalificacionTableComponent implements OnInit{
  @Input() referentes!: Referente[];
  calificaciones !: Calificacion[];
  alumnos : AlumnoDetailsResponse[] = [];
  instrumentoId: string ="";
  asignaturaId: string ="";
  route: ActivatedRoute = inject(ActivatedRoute);
  calf: number = 0;
  calfErr: string = "";
  codRef : string = "";
  codAlumn : string = "";


  constructor( private calificacionService: CalificacionesService, private modalService: NgbModal, private alumnoService: AlumnoService ){
    this.instrumentoId = this.route.snapshot.params['id'];
    this.asignaturaId = this.route.snapshot.params['id_asig'];
  }

  ngOnInit(): void {
      
      this.calificacionService.getInstrumentoDetails(this.instrumentoId).subscribe({
        next: resp => {
          
          this.calificaciones = resp.content;
          console.log(this.alumnos);
          
        },
        error: err => {
          if(err.status = 404){
            window.location.href = `${environment.localHost}not-found`;
          }
        }
      });
      this.alumnoService.getAlumnoByAsignatura(this.asignaturaId).subscribe({
        next: data =>{  
          this.alumnos = data;
        }, error: err=>{
          

          window.location.href = `${environment.localHost}notfound`
        }
      })
      
  }
  open(content: TemplateRef<any>, numberRef: number, numberAl: number) {
    this.codRef = this.referentes[numberRef].codReferente;
    this.codAlumn = this.alumnos[numberAl].id;
    this.modalService.open(content, { scrollable: true });
  }
  toSave(){
    let toSave: POSTCalificacionDTO = new POSTCalificacionDTO(this.codRef, this.codAlumn,this.calf);
    this.calificacionService.createCalificacion(this.instrumentoId, toSave).subscribe({
      next: data=> {
        window.location.href = `${environment.localHost}teacher/subject/${this.asignaturaId}/instrument/${this.instrumentoId}`
      }, error: err => {
        if(err.status == 400){
          let badReq = err.error;
          console.log(badReq);
          let errors = badReq.body.fields_errors;
          errors.forEach((error: { field: string; message: string; }) => {
            this.calfErr = error.message;
          });
        }
      }
    });
  }
  delete(id:string){
    this.calificacionService.deleteCalificacion(id).subscribe({
      next: data=>{
        window.location.reload();
      }, error: err =>{
        if(err == 404){
          window.location.href = `${environment.apiBaseUrl}not-found`
        }
      }
    });
  }
}
