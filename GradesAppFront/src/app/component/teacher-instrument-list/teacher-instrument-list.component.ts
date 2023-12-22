import { Component, OnInit, TemplateRef, inject } from '@angular/core';
import { InstrumentService } from '../../services/instrument.service';
import { AllInstrumentoDTO, InstrumentoListResponse } from '../../models/instrumento-list.interface';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { POSTInstrumentoDTO } from '../../models/create-instrumento.interface';
import { ReferentsService } from '../../services/referents.service';
import { AllReferenteDTO } from '../../models/referente-list.interface';

@Component({
  selector: 'app-teacher-instrument-list',
  templateUrl: './teacher-instrument-list.component.html',
  styleUrl: './teacher-instrument-list.component.css'
})
export class TeacherInstrumentListComponent implements OnInit{
  instrumentoList!: AllInstrumentoDTO[];
  instrumentoInfo !: InstrumentoListResponse;
  page:number = 0;
  route: ActivatedRoute = inject(ActivatedRoute);
  asignaturaId: string = '';
  name: string = "";
  nameErr: string = "";
  contents: string = "";
  contentErr: string ="";
  date!: NgbDateStruct;
  dateErr: string = "";
  referenteList!: AllReferenteDTO[];
  codReferentes : string[] = [];
  refErr: string = "";
  


  constructor(private instrumentoService: InstrumentService, private modalService: NgbModal, private referenteService: ReferentsService){
    this.asignaturaId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
      this.loadNewPage();
      this.referenteService.getReferentesFromAsignatura(this.asignaturaId, this.page).subscribe(answ => {
        this.referenteList = answ.content;
      });
  }
  loadNewPage(){
    this.instrumentoService.getInstrumentosFromAsignatura(this.asignaturaId, this.page).subscribe(answ => {
      this.instrumentoList = answ.content;
      this.instrumentoInfo = answ;
    });
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content, { scrollable: true });
  }
  toSave(){
    let toSaveMonth = this.date.month.toString().split('').length > 1? this.date.month.toString(): "0"+this.date.month; 
    let toSaveDay = this.date.day.toString().split('').length > 1? this.date.day.toString(): "0"+this.date.day; 
    let realDate:string = this.date.year + "-" + toSaveMonth + "-" + toSaveDay;
    let newIns: POSTInstrumentoDTO = new POSTInstrumentoDTO(this.name, this.contents, realDate, this.codReferentes);
    this.codReferentes = [];
    this.instrumentoService.createInstrumentosFromAsignatura(this.asignaturaId, newIns).subscribe({
      next: data => {
        window.location.href = "http://localhost:4200/teacher/subject/"+this.asignaturaId;
      },
      error: err => {
        if(err.status = 400){
          let badReq = err.error;
          console.log(badReq);
          let errors = badReq.body.fields_errors;
          errors.forEach((error: { field: string; message: string; }) => {
            if(error.field == "nombre"){
              this.nameErr= error.message;
            }else if(error.field == "referentes"){
              this.refErr = error.message;
            }else if(error.field == "contenidos"){
              this.contentErr = error.message;
            }else if(error.field == "fecha"){
              this.dateErr = error.message;
            }else{
              alert("Que hisite weon rompiste la pinche página");
            }
            
          });
        }
      }
    });
  }
  addToReferentes(codRef: string, event:any ){
    if(event.target.checked){
      this.codReferentes.push(codRef);
    }else{
      this.codReferentes.splice(this.codReferentes.indexOf(codRef));
    }
  }
}
