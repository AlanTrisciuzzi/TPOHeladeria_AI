import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Freezer } from '../models/freezer';
import { Helado } from '../models/helado';

import { FreezerService } from '../services/freezer.service';


@Component({
  selector: 'app-freezerlist',
  templateUrl: './freezerlist.component.html',
  styleUrls: ['./freezerlist.component.css']
})
export class FreezerlistComponent implements OnInit {
  public freezerList: Freezer[] = []
  freezer = new Freezer()
  freezerForm: FormGroup


  // view 
  public InputIDV: number
  public InputDescriptionV: string
  public InputMarcaV: string
  public InputTemperaturaV: number
  public InputListV: Helado[] = []
  

  constructor (private freezerService: FreezerService, private modalService: NgbModal){}
  
  ngOnInit(): void {
    this.freezer.id = 0
    this.freezer.description = ""
    this.freezer.marca = ""
	  this.freezer.temperatura = 0
    this.freezer.helado = []
    this.freezerForm = new FormGroup({
      'id': new FormControl(this.freezer.id, Validators.required),
      'description': new FormControl(this.freezer.description, Validators.required),
      'marca': new FormControl(this.freezer.marca, Validators.required),
      'helado': new FormControl(this.freezer.helado, Validators.required),
      'temperatura': new FormControl(this.freezer.temperatura, Validators.required)
    })

    this.freezerService.getAll().subscribe(freezerResponse => {
      this.freezerList = freezerResponse
      console.log(this.freezerList)
    }, error => {
      console.error(error)
    })
  }


  get id(){return this.freezerForm.get('id')}
  get description(){return this.freezerForm.get('description')}
  get marca(){return this.freezerForm.get('marca')}
  get temperatura(){return this.freezerForm.get('temperatura')}
  get helado(){return this.freezerForm.get('helado')}

  delete(id: number){
    this.freezerService.delete(id).subscribe(() => {
      location.reload()
      alert('Baja Exitosa!')
    }, error => {
      console.error(error)
      if(error.status === 500) {
        alert('Error: el freezer no puede ser borrado')
      } 
    })
    
  }

view(ver: any, f: Freezer){
  
  this.InputIDV = f.id
  this.InputDescriptionV = f.description
  this.InputMarcaV = f.marca
  this.InputTemperaturaV = f.temperatura
  this.InputListV = f.helado
  
  this.modalService.open(ver).result.then(() => {
    let freezer = new Freezer()
    freezer.id = this.InputIDV
    freezer.description = this.InputDescriptionV
    freezer.marca = this.InputMarcaV
    freezer.temperatura = this.InputTemperaturaV
    freezer.helado = this.InputListV
    
    alert(this.InputIDV)
  
    this.freezerService.edit(this.InputIDV, freezer).subscribe(() => {
      location.reload()

    }, error => {
      console.error(error)
      alert('Error ' + error.error.messege)
    })  
  })  
}


add(){
  let freezer = new Freezer()

  freezer.id = this.id.value
  freezer.description = this.description.value
  freezer.marca = this.marca.value
  freezer.temperatura = this.temperatura.value
  freezer.helado = this.helado.value

  this.freezerService.add(freezer).subscribe(() => {
    alert('Alta Exitosa')
    document.getElementsByTagName("input")[0].focus()
    location.reload()
  }, error => {
    console.error(error)
    alert('Error: ' + error.error.messege)
    document.getElementsByTagName("input")[0].focus()
  })
}

}