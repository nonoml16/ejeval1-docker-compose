import { Component, EventEmitter, Input, OnInit, Output, TemplateRef } from '@angular/core';
import { AlumnoP } from '../../models/alumno-profesor-list.inteface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AlumnoService } from '../../services/alumno.service';
import { EDITAlumnoDTO } from '../../models/edit-alumno-request.interface';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../enviroment/enviroment';

@Component({
  selector: 'app-student-item',
  templateUrl: './student-item.component.html',
  styleUrl: './student-item.component.css'
})
export class StudentItemComponent implements OnInit{
  

  @Input() alumno!: AlumnoP;
  @Output() editarAlumno: EventEmitter<string> = new EventEmitter<string>();
  idTeacher!: string;
  nombre: string = '';
  nombreEdit: string = '';
  apellido: string = '';
  apellidoEdit: string = '';
  email: string = '';
  emailEdit: string = '';
  telefono: string = '';
  telefonoEdit: string = '';
  nombreError: string = '';
  apellidoError: string = '';
  emailError: string = '';
  telefonoError: string = '';

  constructor(private alumnoService: AlumnoService, private modalService: NgbModal, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam !== null){
      this.idTeacher = idParam;
    }
    
    this.alumnoService.getAlumnoDetails(this.alumno.id).subscribe({
      next: data =>{
        this.emailEdit = data.email;
        this.telefonoEdit = data.telefono;
      },error: err => {
        if(err.status = 404){
          window.location.href= `${environment.localHost}notfound`
        }
      }
      
    });
  }

  open(content: TemplateRef<any>, event:any) {
    event.stopPropagation();
    this.modalService.open(content);
    console.log(this.emailEdit);
    console.log(this.telefonoEdit);
  }

  guardar() {
    let nuevoAlumno: EDITAlumnoDTO = new EDITAlumnoDTO(this.nombre, this.apellido, this.email, this.telefono);
    console.log(this.nombre, this.apellido, this.email, this.telefono)
    this.alumnoService.editAlumno(this.alumno.id, nuevoAlumno).subscribe({
      next: resp => {
        window.location.href = "http://localhost:4200/teacher/" + this.idTeacher + "/students";
      }, error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "nombre":
                this.nombreError = erro.message;
                break;
              case "apellidos":
                this.apellidoError = erro.message;
                break;
              case "email":
                this.emailError = erro.message;
                break;
              case "telefono":
                this.telefonoError = erro.message;
                break;
            }
          });
        }
      }
    });
  }

  borrar(event: any){
    event.stopPropagation();
    this.alumnoService.deleteAlumno(this.alumno.id).subscribe({
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
