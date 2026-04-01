import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Student } from '../../student';
import { StudentService } from '../../student-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-student',
  imports: [FormsModule],
  templateUrl: './create-student.html',
  styleUrl: './create-student.css',
})
export class CreateStudent {

  constructor(
    private studentService: StudentService,
    private router: Router
  ) {}

  student: Student = {
    name: '',
    age: 0
  };

  submit() {
    this.studentService.create(this.student).subscribe({
      next: (res) => {
        console.log('Student created:', res);

                this.studentService.onStudentAdded.emit(res);
                
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error(err);
        alert('You already created a student.');
      }
    });
  }
}
