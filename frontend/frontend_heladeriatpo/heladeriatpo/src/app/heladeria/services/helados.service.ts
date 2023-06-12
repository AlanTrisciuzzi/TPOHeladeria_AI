import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Helado } from "../models/helado";

@Injectable({
    providedIn: 'root'
})


export class HeladoService{
    private url = 'http://localhost:8080/heladeria'
    constructor(private http: HttpClient){}

    getAll() : Observable <any> {
        return this.http.get(this.url )
    }

    delete(id: number) : Observable <any>{
        return this.http.post(this.url + "/" + id + "/delete", null )
    }

    add(helado: Helado) : Observable <any>{
        return this.http.post(this.url,Helado)
    }

    edit(helado: Helado, id : number) : Observable <any>{
        return this.http.post(this.url + '/' + helado.id + '/update', helado)
    }


}