import { Component, OnInit, inject } from '@angular/core';
import { Calificacion } from '../../models/calificaciones-instrumento.interface';
import { CalificacionesService } from '../../services/calificaciones.service';
import { ReferentsService } from '../../services/referents.service';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../enviroment/enviroment';
import { ReferenteInstrumentoResponse } from '../../models/referente-instrumento.interface';

@Component({
  selector: 'app-instrument-list',
  templateUrl: './instrument-list.component.html',
  styleUrl: './instrument-list.component.css'
})
export class InstrumentListComponent implements OnInit{
  referenteList!: any[];
  calificaciones!: Calificacion[];
  route: ActivatedRoute = inject(ActivatedRoute);
  instrumentoId: string;

  constructor(private calificacioneService: CalificacionesService, private referenteService: ReferentsService) {
    this.instrumentoId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
      this.calificacioneService.getCalificacionsAlumno(this.instrumentoId).subscribe({
        next: data => {
          this.calificaciones = data.content;
          
        }, error: err => {
          if(err.status == 404){
            window.location.href = `${environment.localHost}not-found`;
          }
        }
      });
      this.referenteService.getReferentesInstrumento(this.instrumentoId).subscribe({
        next: data => {
          this.referenteList.push(data);
        }, error: err => {
          if(err.status == 404){
            window.location.href = `${environment.localHost}not-found`;
          }
        }
      });
  }
}
