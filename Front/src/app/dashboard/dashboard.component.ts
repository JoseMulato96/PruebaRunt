import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'app/services/authentication.service';
import * as Chartist from 'chartist';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public titulo = "Inicio de Sesion";
  // usuario: Usuario;
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string = "inicio";
  siteKey: string;

  formGroup: FormGroup;
  constructor(private authenticationService: AuthenticationService, private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      'username': ['', Validators.required],
      'password': ['', Validators.required],
    });
  }

  getError(el) {
    switch (el) {
      case 'user':
        if (this.formGroup.get('username').hasError('required')) {
          return 'Username required';
        }
        break;
      case 'pass':
        if (this.formGroup.get('password').hasError('required')) {
          return 'Password required';
        }
        break;
      default:
        return '';
    }
  } 

  onSubmit() {
    this.submitted = true;


    console.log("valor de login", this.loginForm);
    this.loading = true;
    console.log(this.formGroup);
    this.authenticationService
      .login(this.formGroup.controls.username.value, this.formGroup.controls.password.value)
      .pipe(first())
      .subscribe(
        (data) => {
          this.loading = true;
          console.log("valor data",data);
          if (data === "error") {
            this.loading = false;
          } else {
            setTimeout(() => {
              this.loading = false;
              this.router.navigate([this.returnUrl]);
            }, 300);
          }
        },
        (error) => {
          //this.alertService.error(error);
          alert("Usuario y/o Contraseña no corresponden.");          
          this.loading = false;
        }
      );
  }

}
