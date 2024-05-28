import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLoginComponent } from './admin-login.component';

@NgModule({
  declarations: [AdminLoginComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports:[AdminLoginComponent]
})
export class AdminLoginModule { }
