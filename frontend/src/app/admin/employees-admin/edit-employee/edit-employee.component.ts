import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EmployeesService} from "../../../service/employees.service";

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {

  constructor(public employeesService: EmployeesService
    , public dialogBox: MatDialogRef<EditEmployeeComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onClose() {
    this.dialogBox.close();
    this.employeesService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.employeesService.update$(form.value).subscribe(res => {
      this.snackBar.open(res.message, '', {
        duration: 5000,
        verticalPosition: 'top'
      })
    })

  }

}
