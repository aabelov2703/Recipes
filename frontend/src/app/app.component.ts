import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    AuthComponent,
    HomePageComponent,
    FooterComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  user: any = null;

  constructor(public authService: AuthService) {}

  ngOnInit() {
    this.authService.getUserProfile().subscribe({
      error: (error) => console.log(error),
    });
    this.authService.authSubject.subscribe((auth) => (this.user = auth.user));
  }
}
