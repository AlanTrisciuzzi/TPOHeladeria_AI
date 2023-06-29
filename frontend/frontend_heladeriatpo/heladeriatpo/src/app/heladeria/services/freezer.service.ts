import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Freezer } from '../models/freezer';



@Injectable({
    providedIn: 'root'
})

export class FreezerService {
    private url = 'http://localhost:8080/freezer'
    constructor(private http: HttpClient){}

    getAll() : Observable <any> {
        return this.http.get(this.url + "/getAll")
    }

    delete(id : number) : Observable <any> {
        return this.http.post(this.url + "/" + id + "/delete", null)
    }

    add(freezer: Freezer) : Observable <any> {
        return this.http.post(this.url + "/addFreezer", freezer )
    }

    edit(id : number, freezer: Freezer) : Observable <any> {
        return this.http.post(this.url + '/' + freezer.id + '/update', freezer)
    }

}
