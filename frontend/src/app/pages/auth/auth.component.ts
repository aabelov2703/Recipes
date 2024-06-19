import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatRadioModule,
    ReactiveFormsModule,
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
})
export class AuthComponent {
  isLogin = false;

  constructor(public authService: AuthService) {}

  registerForm = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
  });

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  onRegister() {
    this.authService.onRegister(this.registerForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
      },
    });
  }

  onLogin() {
    this.authService.onLogin(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
      },
    });
  }

  togglePanel() {
    this.isLogin = !this.isLogin;
  }
}
