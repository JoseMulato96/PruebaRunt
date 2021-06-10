import { Component, OnInit } from '@angular/core';
import { PeticionesService } from 'app/services/peticiones.service';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public dProfesores: any;
  public dAsiganturas: any;
  public dEstudiantes: any;
  public dcursosHasAsignaturas: any;
  public urlProfesores: string;
  public urlAsiganturas: string;
  public urlEstudiantes: string;
  public urlcursosHasAsignaturas: string;
  public selectedProfesor: number;


  public arrayAsignaturasProfe: Array<any>;
  public arrayEstudiantes: Array<any>;
  public arrayCursos: Array<any>;


  public showTable: boolean = false;

  public dTabla: any;
  public dTabla1: any;

  constructor(private _peticionesService: PeticionesService) {
    this.urlProfesores = environment.url + "profesores";
    this.urlAsiganturas = environment.url + "asignaturas";
    this.urlEstudiantes = environment.url + "estudiantes";
    this.urlcursosHasAsignaturas = environment.url + "cursosHasAsignaturas";
  }

  ngOnInit() {
    this.searchProfesores();
    this.searchAsignaturas();
    this.searchEstudiantes();
    this.searchEstudiantesHasCurso();
  }


  searchProfesores() {
    this._peticionesService.get(this.urlProfesores).subscribe(
      (result) => {
        this.dProfesores = result;
        //console.log(this.dProfesores);
      },
      (error) => {
        var errorMessage = <any>error;
        //console.log(errorMessage);
      }
    );
  }

  searchAsignaturas() {
    this._peticionesService.get(this.urlAsiganturas).subscribe(
      (result) => {
        this.dAsiganturas = result;
        //console.log(this.dAsiganturas);
      },
      (error) => {
        var errorMessage = <any>error;
        //console.log(errorMessage);
      }
    );
  }




  searchEstudiantes() {
    this._peticionesService.get(this.urlEstudiantes).subscribe(
      (result) => {
        this.dEstudiantes = result;
        //console.log(this.dEstudiantes);
      },
      (error) => {
        var errorMessage = <any>error;
        //console.log(errorMessage);
      }
    );
  }

  searchEstudiantesHasCurso() {
    this._peticionesService.get(this.urlcursosHasAsignaturas).subscribe(
      (result) => {
        this.dcursosHasAsignaturas = result;
        //console.log(this.dcursosHasAsignaturas);
      },
      (error) => {
        var errorMessage = <any>error;
        //console.log(errorMessage);
      }
    );
  }


  writeJsonDatos() {
    this.arrayAsignaturasProfe = [];
    this.arrayCursos = [];
    this.arrayEstudiantes = [];
    this.dTabla = [];
    this.dTabla1 = [];
    this.showTable = true;
    

    for (let i = 0; i < this.dAsiganturas.length; i++) {
      if (this.selectedProfesor == this.dAsiganturas[i].idProfesor_Profesores) {
        this.arrayAsignaturasProfe.push(this.dAsiganturas[i]);
      }
    }

    this.dTabla = this.arrayAsignaturasProfe;

    for (let i = 0; i < this.dTabla.length; i++) {
      for (let j = 0; j < this.dcursosHasAsignaturas.length; j++) {
        if (this.arrayAsignaturasProfe[i].idAsignatura == this.dcursosHasAsignaturas[j].idAsignatura_Asignaturas) {
          this.arrayCursos.push(this.dcursosHasAsignaturas[j].idCurso_Cursos);
        }        
      }      
    }
    
    for (let i = 0; i < this.arrayCursos.length; i++) {
      for (let j = 0; j < this.dEstudiantes.length; j++) {
        if (this.arrayCursos[i] == this.dEstudiantes[j].idCurso_Cursos) {
          this.arrayEstudiantes.push(this.dEstudiantes[j]);
        } 
        
      }      
    }

    this.dTabla1 = this.removeDuplicates(this.arrayEstudiantes,"nombre");    
  }

   removeDuplicates(originalArray, prop) {
    var newArray = [];
    var lookupObject  = {};

    for(var i in originalArray) {
       lookupObject[originalArray[i][prop]] = originalArray[i];
    }

    for(i in lookupObject) {
        newArray.push(lookupObject[i]);
    }
     return newArray;
}
}

