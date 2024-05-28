import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGiftCardComponent } from './admin-gift-card.component';

describe('AdminGiftCardComponent', () => {
  let component: AdminGiftCardComponent;
  let fixture: ComponentFixture<AdminGiftCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminGiftCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminGiftCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
