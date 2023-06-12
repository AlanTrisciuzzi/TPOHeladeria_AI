import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeladolistComponent } from './heladolist.component';

describe('HeladolistComponent', () => {
  let component: HeladolistComponent;
  let fixture: ComponentFixture<HeladolistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeladolistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeladolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
