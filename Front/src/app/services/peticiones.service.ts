import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";


@Injectable()
export class PeticionesService {
    public url: string;


    constructor(private http: HttpClient) {
        this.url = "https://jsonplaceholder.typicode.com/posts";
    }


    get(urlTmp: string) {
        //return this._http.get(urlTmp).pipe(map(res => res.json()));        
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': JSON.parse(localStorage.getItem("currentUser")).token
            })
        };
        return this.http.get<any>(urlTmp, httpOptions);
    }



}
