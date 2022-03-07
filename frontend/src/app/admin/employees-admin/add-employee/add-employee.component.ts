import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {MessageService} from 'primeng/api';
import {EmployeesService} from "../../../service/employees.service";
import {Employee} from "../../../interface/employee";
import {MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";
import {Company} from "../../../interface/company";
import {Location} from "../../../interface/location";
import {Department} from "../../../interface/department";


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {


  defaultEmployee: Employee = new Employee();
  companies!: any[];
  departments!: any[];


  @Output()
  submitted = new EventEmitter();

  constructor(public employeeService: EmployeesService
    , public dialogBox: MatDialogRef<AddEmployeeComponent>
    , private messageService: MessageService) {
  }

  ngOnInit(): void {
  this.resetForm();

  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    this.employeeService.formData = {

      id: 0,
      lastName: '',
      firstName: "",
      email: "",
      birthday: new Date(),
      createdAt: new Date(),
      enabled: false,
      company: new Company(),
      department: new Department,
      image: new Blob(),
      location: new Location(),
      modifiedAt: new Date(),


    }
  }

  onClose() {
    this.dialogBox.close();
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.employeeService.save$(form.value).subscribe(res=>
      {
        this.resetForm(form);
        alert(res);
      }
    )
  }
}
