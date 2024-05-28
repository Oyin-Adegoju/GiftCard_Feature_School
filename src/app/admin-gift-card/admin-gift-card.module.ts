import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminGiftCardComponent } from './admin-gift-card.component';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [AdminGiftCardComponent],
  imports: [
    CommonModule,
    BrowserModule,
  ],
  exports: [
    AdminGiftCardComponent 
  ]
})
export class AdminGiftCardModule { }
