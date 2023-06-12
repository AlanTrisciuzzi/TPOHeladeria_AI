import { TestBed } from "@angular/core/testing";
import { HeladoService } from "./helados.service";

describe('HeladoService' , () =>{
    let service : HeladoService;

    beforeEach(() => {
        TestBed.configureTestingModule({})
        service = TestBed.inject(HeladoService);
    }), 

    it('should be created', () => {
        expect(service).toBeTruthy(),
    }),
})
