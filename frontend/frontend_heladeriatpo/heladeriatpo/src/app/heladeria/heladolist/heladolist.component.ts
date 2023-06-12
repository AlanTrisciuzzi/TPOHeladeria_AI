import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';

import { Helado } from '../models/helado';
import { Freezer } from '../models/freezer';
import { HeladoService } from '../services/helados.service';
import { FreezerService } from '../services/freezer.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-heladolist',
  templateUrl: './heladolist.component.html',
  styleUrls: ['./heladolist.component.css']
})

export class HeladolistComponent implements OnInit{
  public heladoList: Helado[] = []
  public freezerList: Freezer[]

  helado = new Helado()
  heladoForm: FormGroup

  //View 
  public InputIDV: number
  public InputNameV: string
  public InputCategoryV: string 
  public InputStockV: number
  public InputFreezerV: Freezer



  constructor(private heladoService: HeladoService, private modalService: NgbModal, private freezerService: FreezerService){}
  ngOnInit(): void {
    this.helado.id= 0
    this.helado.name = ""
    this.helado.category = ""
    this.helado.stock = 0 
    this.helado.freezer = new Freezer
    this.heladoForm = new FormGroup({
      'id': new FormControl(this.helado.id, Validators.required),
      'name': new FormControl(this.helado.name, Validators.required),
      'category': new FormControl(this.helado.category, Validators.required),
      'stock': new FormControl(this.helado.stock, Validators.required),
      'freezer': new FormControl(this.helado.freezer, Validators.required)
    })
  
    
    this.freezerService.getAll().subscribe(freezerResponse =>{
      this.freezerList = freezerResponse
      console.log(freezerResponse)
    }, error => {
      console.error(error)
    })

    this.heladoService.getAll().subscribe(heladoResponse => {
      this.heladoList = heladoResponse
      console.log(heladoResponse)
    }, error =>{
      console.error(error)
    });
  }

    get id(){return this.heladoForm.get('id')} 
    get name(){return this.heladoForm.get('name')}
    get category(){return this.heladoForm.get('category')}
    get stock(){return this.heladoForm.get('stock')}
    get freezer(){return this.heladoForm.get('freezer')}

  add(){
      let helado = new Helado()
      helado.id = this.id.value  
      helado.name = this.name.value
      helado.category = this.category.value
      helado.stock = this.stock.value
      console.log(helado);
    
    this.heladoService.add(helado).subscribe(() => {
      alert('Alta Exitosa')
      document.getElementsByTagName("input")[0].focus()
      location.reload()
    }, error => {
      console.error(error)
      alert('Error: ' + error.error.messege)
      document.getElementsByTagName("input")[0].focus()
    }
    )
  }

  delete(id: number){
    this.heladoService.delete(id).subscribe(() =>{
      location.reload()
      alert('Baja Exitosa')  
    }, error => {
      console.error(error)
      if (error.status === 500){
        alert('Error: el helado no puede ser borrado')
      }
    })
  }
  
  view(ver: any, h: Helado){
    this.InputIDV = h.id
    this.InputNameV = h.name
    this.InputCategoryV = h.category
    this.InputStockV = h.stock
    this.InputFreezerV = h.freezer
    this.modalService.open(ver).result.then(() => {
      let helado = new Helado()
      helado.id = this.InputIDV
      helado.name = this.InputNameV
      helado.category = this.InputCategoryV
      helado.stock = this.InputStockV
      helado.freezer = this.InputFreezerV
      alert(this.InputIDV)
      this.heladoService.edit(this.InputIDV, helado).subscribe(() =>{
        location.reload()
      }, error => {
        console.error(error)
        alert('Error ' + error.error.messege)
      })
    })

  }







}

