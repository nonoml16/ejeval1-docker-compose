import { Component, OnInit, TemplateRef, inject } from '@angular/core';
import { ReferentsService } from '../../services/referents.service';
import {
  AllReferenteDTO,
  ReferenteListResponse,
} from '../../models/referente-list.interface';
import { ActivatedRoute } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { POSTReferenteDTO } from '../../models/create-referente.interface';

@Component({
  selector: 'app-teacher-referent-list',
  templateUrl: './teacher-referent-list.component.html',
  styleUrl: './teacher-referent-list.component.css',
  providers: [NgbModalConfig, NgbModal],
})
export class TeacherReferentListComponent implements OnInit {
  referenteList!: AllReferenteDTO[];
  referenteInfo!: ReferenteListResponse;
  page: number = 0;
  route: ActivatedRoute = inject(ActivatedRoute);
  asignaturaId: string = '';
  codRef: string = '';
  desc: string = '';
  codRefErrNE: string = '';
  codRefR: string = '';
  descErr: string = '';
  codRefErrR: any;

  constructor(
    private referenteService: ReferentsService,
    private modalService: NgbModal
  ) {
    this.asignaturaId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.loadNewPage();
  }
  loadNewPage() {
    this.referenteService
      .getReferentesFromAsignatura(this.asignaturaId, this.page)
      .subscribe((answ) => {
        this.referenteList = answ.content;
        this.referenteInfo = answ;
      });
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  toSave() {
    let newRef: POSTReferenteDTO = new POSTReferenteDTO(this.codRef, this.desc);
    this.referenteService.createReferente(this.asignaturaId, newRef).subscribe({
      next: (data) => {
        window.location.href =
          'http://localhost:4200/teacher/subject/' +
          this.asignaturaId +
          '?instrumento=false';
      },
      error: (err) => {
        if ((err.status = 400)) {
          this.codRefErrNE = '';
          this.codRefErrR = '';
          this.descErr = '';
          let badReq = err;
          let errors = badReq.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any }) => {
            if (erro.field == 'codReferente') {
              console.log(erro.message);
              if (
                erro.message ==
                'La descripción del referente no puede estar vacía'
              ) {
                this.codRefErrNE = erro.message;
                this.codRefErrR = '';
              } else {
                this.codRefErrR = erro.message;
              }
            } else {
              this.descErr = erro.message;
            }
          });
        }
      },
    });
  }
}
