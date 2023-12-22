import { Component, OnInit, inject } from '@angular/core';
import { InstrumentoDetailResponse, Referente } from '../../models/instrumento-details.interface';
import { Alumno, Calificacion } from '../../models/calificaciones-instrumento.interface';
import { InstrumentService } from '../../services/instrument.service';
import { CalificacionesService } from '../../services/calificaciones.service';
import { ActivatedRoute } from '@angular/router';
import { error } from 'console';
import { environment } from '../../enviroment/enviroment';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-instrument-details',
  templateUrl: './instrument-details.component.html',
  styleUrl: './instrument-details.component.css'
})
export class InstrumentDetailsComponent implements OnInit{
  referentes!: Referente[];
  calificaciones !: Calificacion[];
  alumnos : Alumno[] = [];
  instrumentoId: string ="";
  instrumento!: InstrumentoDetailResponse;
  asignaturaId: string ="";
  route: ActivatedRoute = inject(ActivatedRoute);


  constructor(private instrumentService: InstrumentService, private calificacionService: CalificacionesService){
    this.instrumentoId = this.route.snapshot.params['id'];
    this.asignaturaId = this.route.snapshot.params['id_asig'];
  }

  ngOnInit(): void {

      this.instrumentService.getInstrumentoDetails(this.instrumentoId).subscribe({
        next: resp => {
          this.instrumento = resp;
          this.referentes = resp.referentes;
        },
        error: err => {
          if(err.status = 404){
            console.log("hola error")
          }
        }
      });
      
  }
  backPage(){
    window.location.href=`${environment.localHost}teacher/subject/${this.asignaturaId}`
  }
}
