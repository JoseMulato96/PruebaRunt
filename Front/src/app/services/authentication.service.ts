import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { map } from "rxjs/operators";
import { JwtHelperService } from "@auth0/angular-jwt";
import { Router } from "@angular/router";
// import { StoreService } from "./store.service";
// import { ACTION_HEADER_STATE } from "../store/actions/appAction";
// import swal from "sweetalert2";

@Injectable()
export class AuthenticationService {
  public url: string = "http://localhost:9090/login";
  bodyLogin:any;

  constructor(private http: HttpClient, public router: Router, private jwtHelper: JwtHelperService) { }

  login(username: string, password: string) {
    localStorage.clear();
    let headers = new HttpHeaders();
    headers = headers.set("Content-Type", "application/x-www-form-urlencoded");

    this.bodyLogin = {
        "username":username,
        "password":password
    }
    
    return this.http.post<any>(this.url, this.bodyLogin).pipe(
      map((user) => {
        console.log("valor despues de login",user);        
        if (user && user.token) {          
        //   this.storeService.updateAppState({
        //     action: ACTION_HEADER_STATE,
        //     payload: true
        //   });
          localStorage.setItem("currentUser", JSON.stringify(user));
        } else {
            alert("Usuario y/o Contraseña no corresponden.");
        //   swal.fire("Error", "Usuario y/o Contraseña no corresponden.", "error");
          user = "error";
        }

        return user;
      })
    );
  }

  public isAuthenticated(): boolean {
    const token = this.getTokenSession();
    return token == null ? false : !this.jwtHelper.isTokenExpired(token);
  }

  public validaSession() {
    if (!this.isAuthenticated()) {
      this.router.navigate(["login"]);
      // this.snackbar.open('Su session ha expirado', 'close',{duration: 4000, announcementMessage : 'Error' });
      // clearInterval(this.interval);
    }
  }

  public getTokenExpirationDate(): any {
    const token = this.getTokenSession();
    return token == null ? null : this.jwtHelper.getTokenExpirationDate(token);
  }

  getUserSession(): any {
    return JSON.parse(localStorage.getItem("currentUser"));
  }

  getUserName(): string {
    const currentUser = JSON.parse(localStorage.getItem("currentUser"));
    return currentUser != null ? currentUser.username : null;
  }

  getTokenSession(): any {
    const currentUser = JSON.parse(localStorage.getItem("currentUser"));
    return currentUser != null ? currentUser.token : null;
  }

  validateRolOnSession(rol: string[]) {
    // const roles = this.getAuthorities();
    // return roles.split(",").some(r => rol.some( r1 => r1 == r ));
  }

  logout(): void {
    localStorage.removeItem("currentUser");
  }
}
