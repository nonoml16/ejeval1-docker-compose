import { Component,OnInit } from '@angular/core';
import { SubjectService } from '../../services/subject.service';
import { SubjectA} from '../../models/subject.interface';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrl: './subject-list.component.css',
})
export class SubjectListComponent implements OnInit {
  subjectList!: SubjectA[];
  page = 0;
  count = 0;
  pageSize = 0;

  constructor(private subjectService: SubjectService) {}

  ngOnInit(): void {
    this.loadNewPage();
  }

  loadNewPage() {
    this.subjectService.getAsignaturas(this.page - 1).subscribe((resp) => {
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
