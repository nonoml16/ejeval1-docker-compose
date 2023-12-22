import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-teacher-subject',
  templateUrl: './teacher-subject.component.html',
  styleUrl: './teacher-subject.component.css'
})
export class TeacherSubjectComponent{
  isInstrumentSelected: boolean  =true;
  route: ActivatedRoute = inject(ActivatedRoute);

  constructor(){
    this.route.queryParams.subscribe(params => {
      if(Object.keys(params).length === 0){
        this.isInstrumentSelected = true;
      }else{
        console.log(params);
        if(params['instrumento'] == 'true'){
          this.isInstrumentSelected = true;
        }else{
          this.isInstrumentSelected = false;
        }
      }
    });
  }

  changeInstrument(isInstrumentClicked: boolean){
    this.isInstrumentSelected = isInstrumentClicked;
  }
}
