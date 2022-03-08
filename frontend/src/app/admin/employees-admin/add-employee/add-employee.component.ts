import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {MessageService} from 'primeng/api';
import {EmployeesService} from "../../../service/employees.service";
import {MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";
import {Company} from "../../../interface/company";
import {Location} from "../../../interface/location";
import {Department} from "../../../interface/department";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {


  public companiesList:Array<Company>=[];
  public departmentsList:Array<Department>=[];
  public locationsList: Array<Location>=[];


  @Output()
  submitted = new EventEmitter();

  constructor(public employeesService: EmployeesService
    , public dialogBox: MatDialogRef<AddEmployeeComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.resetForm();
    this.dropDownLocations();
    this.dropDownCompanies()
    this.dropDownDepartments()
  }

  dropDownCompanies(){
    this.employeesService.getDropDownCompanies$.subscribe(data =>{
      console.log(data);
      data.data?.companies?.forEach(element =>{
        this.companiesList?.push(element);
      })
    })
  }
  dropDownDepartments(){
    this.employeesService.getDropDownDepartments$.subscribe(data =>{
      console.log(data);
      data.data?.departments?.forEach(element =>{
        this.departmentsList?.push(element);
      })
    })
  }
  dropDownLocations(){
    this.employeesService.getDropDownLocations$.subscribe(data =>{
      // console.log(data.data?.locations);
      data.data?.locations?.forEach(element =>{
         this.locationsList?.push(element);
      })
    })
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    this.employeesService.formData = {

      id: 0,
      lastName: '',
      firstName: "",
      email: "",
      birthday: new Date(),
      createdAt: new Date(),
      enabled: false,
      company: this.companiesList[1],
      department: this.departmentsList[1],
      image: new Blob(),
      location: this.locationsList[1],
      modifiedAt: new Date(),


    }
  }

  onClose() {
    this.dialogBox.close();
    this.employeesService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.employeesService.save$(form.value).subscribe(res => {
        this.resetForm(form);
        this.snackBar.open(res.message, '', {
          duration: 3500,
          verticalPosition: 'top'
        });
      }
    )
  }
}
