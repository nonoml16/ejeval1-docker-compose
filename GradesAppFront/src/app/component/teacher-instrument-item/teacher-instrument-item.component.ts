import { Component, Input, OnInit, TemplateRef, inject } from '@angular/core';
import { AllInstrumentoDTO } from '../../models/instrumento-list.interface';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ReferentsService } from '../../services/referents.service';
import { ActivatedRoute } from '@angular/router';
import { InstrumentService } from '../../services/instrument.service';
import { environment } from '../../enviroment/enviroment';
import { Referente } from '../../models/instrumento-details.interface';
import { POSTInstrumentoDTO } from '../../models/create-instrumento.interface';
import { AllReferenteDTO } from '../../models/referente-list.interface';

@Component({
  selector: 'app-teacher-instrument-item',
  templateUrl: './teacher-instrument-item.component.html',
  styleUrl: './teacher-instrument-item.component.css'
})
export class TeacherInstrumentItemComponent implements OnInit{
  @Input() instrument!: AllInstrumentoDTO;
  @Input() asignaturaId !: string;
  name: string = "";
  nameEdit: string = "";
  nameErr: string = "";
  contents: string = "";
  contentEdit: string = "";
  contentErr: string ="";
  date!: NgbDateStruct;
  dateEdit: string = "";
  dateErr: string = "";
  referenteList!: AllReferenteDTO[];
  codReferentes : string[] = [];
  refErr: string = "";
  refEdit: string[] = [];
  


  constructor(private instrumentoService: InstrumentService, private modalService: NgbModal, private referenteService: ReferentsService){}

  ngOnInit(): void {
      this.referenteService.getReferentesFromAsignatura(this.asignaturaId, 0).subscribe({
        next: data =>{
          this.referenteList = data.content;
          this.nameEdit = this.instrument.nombre;
          this.dateEdit = this.instrument.fecha;
        },error: err => {
          if(err.status = 404){
            window.location.href= `${environment.localHost}notfound`
          }
        }
        
      });
      this.instrumentoService.getInstrumentoDetails(this.instrument.id).subscribe({
        next: data=>{
          data.referentes.forEach(ref => {
            this.refEdit.push(ref.codReferente);
            this.codReferentes.push(ref.codReferente);
          });
        }, error: err => {
          if(err.status = 404){
            window.location.href= `${environment.localHost}notfound`
          }
        }
      });
  }


  
  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }
  open(content: TemplateRef<any>, event:any) {
    event.stopPropagation();
    this.modalService.open(content, { scrollable: true });
  }
  toSave(){
    let toSaveMonth = this.date.month.toString().split('').length > 1? this.date.month.toString(): "0"+this.date.month; 
    let toSaveDay = this.date.day.toString().split('').length > 1? this.date.day.toString(): "0"+this.date.day; 
    let realDate:string = this.date.year + "-" + toSaveMonth + "-" + toSaveDay;
    let newIns: POSTInstrumentoDTO = new POSTInstrumentoDTO(this.name, this.contents, realDate, this.codReferentes);
    this.codReferentes = [];
    this.instrumentoService.editInstrument(this.instrument.id, newIns).subscribe({
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
      debugger;
      this.codReferentes.splice(this.codReferentes.indexOf(codRef));
    }
  }
  delete(event: any){
    event.stopPropagation();
    this.instrumentoService.deleteInstrument(this.instrument.id).subscribe({
      next: data =>{
        window.location.reload();
      }, error: err =>{
        if(err.status == 404){
          window.location.href=`${environment.localHost}not-found`;
        }
      }
    });
  }
  
}
