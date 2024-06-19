import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  user: any = null;
  constructor(public authService: AuthService) {}

  ngOnInit() {
    this.authService.authSubject.subscribe((auth) => (this.user = auth.user));
  }

  onLogout() {
    this.authService.logout();
  }
}
