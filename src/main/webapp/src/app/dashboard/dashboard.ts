import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  studentName: string | null = null;
  email: string | null = null;

  constructor(private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    // load student object if it exists
    const student = this.authService.getStudent();

    if (student) {
      this.studentName = student.name;
    }

    // always load email from token
    const token = this.authService.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.email = payload.sub; // email stored in JWT
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
