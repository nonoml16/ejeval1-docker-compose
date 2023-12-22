import { Component, TemplateRef } from '@angular/core';
import { TeacherService } from '../../services/teacher.service';
import { Teacher } from '../../models/teacher-list-response';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { environment } from '../../enviroment/enviroment';

@Component({
  selector: 'app-page-teacher-list',
  templateUrl: './page-teacher-list.component.html',
  styleUrl: './page-teacher-list.component.css'
})
export class PageTeacherListComponent {

  nombre: string = '';
  apellidos: string = '';
  email: string = '';
  titulo: string = '';
  username: string = '';
  password: string = '';
  rpassword: string = '';
  nombreError: string = '';
  apellidosError: string = '';
  rpasswordError: string = '';
  emailError: string = '';
  tituloError: string = '';
  usernameError: string = '';
  passwordError: string = '';
  teacherList: Teacher[] = [];
  page = 0;
  count = 0;
  pageSize = 0;

  constructor(private teacherService: TeacherService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadNewPage();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  createTeacher() {

    this.nombreError = '';
    this.apellidosError = '';
    this.rpasswordError = '';
    this.emailError = '';
    this.tituloError = '';
    this.usernameError = '';
    this.passwordError = '';

    this.teacherService.createTeacher(this.username, this.email, this.password, this.rpassword, this.titulo, this.nombre, this.apellidos).subscribe({
      next: data => {
        this.modalService.dismissAll();
      },

      error: err => {
        let errors = err.error.body.fields_errors;
        errors.forEach((erro: { field: any; message: any; }) => {
          switch (erro.field) {
            case "email":
              this.emailError = erro.message;
              break;

            case "username":
              this.usernameError = erro.message;
              break;

            case "nombre":
              this.nombreError = erro.message;
              break;

            case "apellidos":
              this.apellidosError = erro.message;
              break;

            case "titulacion":
              this.tituloError = erro.message;
              break;

            case "password":
              this.passwordError = erro.message;
              break;
          }

          if (erro.message == "Las contraseñas no coinciden")
            this.rpasswordError = erro.message;

        });
      }

    })
  }

  loadNewPage() {
    this.teacherService.getTeacherListByPage(this.page - 1).subscribe((resp) => {
      this.teacherList = resp.content;
      this.pageSize = resp.size
      if (resp.totalElements > 1000) {
        this.count = 10000;
      } else {
        this.count = resp.totalElements;
      }
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    )
  }

}
