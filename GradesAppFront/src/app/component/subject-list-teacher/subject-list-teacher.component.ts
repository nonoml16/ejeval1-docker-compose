import { Component } from '@angular/core';
import { SubjectA } from '../../models/subject.interface';
import { SubjectService } from '../../services/subject.service';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-subject-list-teacher',
  templateUrl: './subject-list-teacher.component.html',
  styleUrl: './subject-list-teacher.component.css'
})
export class SubjectListTeacherComponent {
  subjectList!: SubjectA[];
  page = 0;
  count = 0;
  pageSize = 0;
  id: string;

  constructor(private subjectService: SubjectService, private tokenService: TokenStorageService) {
    this.id= this.tokenService.getUser();
  }

  ngOnInit(): void {
    this.loadNewPage();
  }

  loadNewPage() {
    this.subjectService.getAsignaturaByProfesor(this.page - 1, this.id).subscribe((resp) => {
      this.subjectList = resp.content;
      this.pageSize = resp.size;
      if (resp.totalElements > 1000) {
        this.count = 10000;
      } else {
        this.count = resp.totalElements;
      }
      window.scrollTo({ top: 0, behavior: 'smooth' });
    });
  }
}
