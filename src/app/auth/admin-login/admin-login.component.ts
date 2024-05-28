import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.scss'
})
export class AdminLoginComponent {

  public email: string;
  public password: string;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }

  public onSubmit(): void {
    console.log('data ',this.email);
    console.log('data ',this.password);
    if(this.email=='admin@admin.com' && this.password=='Admin@321'){
      this.router.navigateByUrl('/admin-gift-cards');
    }else{
      alert('Invalid credentials');
    }
  }


}
