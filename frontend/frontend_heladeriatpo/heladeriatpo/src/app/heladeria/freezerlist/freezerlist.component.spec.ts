import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreezerlistComponent } from './freezerlist.component';

describe('FreezerlistComponent', () => {
  let component: FreezerlistComponent;
  let fixture: ComponentFixture<FreezerlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreezerlistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreezerlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
