import { Component } from '@angular/core';
import { Router, RouterModule, NavigationEnd } from '@angular/router';
import { UserService } from '../user-service';
import { StudentService } from '../student-service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  email = '';
  studentName: string | null = null;

  constructor(
    private router: Router,
    private userService: UserService,
    private studentService: StudentService
  ) {}

  ngOnInit() {
    this.email = localStorage.getItem('userEmail') || '';

    // Load student once when dashboard loads
    this.loadStudent();

    // Update immediately when a student is created
    this.studentService.onStudentAdded.subscribe(student => {
      this.studentName = student.name;
    });
  }

  loadStudent() {
    this.studentService.getMyStudent().subscribe({
      next: (student) => {
        this.studentName = student ? student.name : null;
      },
      error: () => {
        this.studentName = null;
      }
    });
  }

  logout(): void {
    this.userService.logout();
    this.router.navigate(['/login']);


  }
}

