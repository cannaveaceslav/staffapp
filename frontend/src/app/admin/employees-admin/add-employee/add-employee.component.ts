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
import {Item} from "../../../interface/item";


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {


  public companiesList:Array<any>=[];
  public departmentsList: Array<any>=[];
  public locationsList: Array<any>=[];



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
      data.data?.companies?.forEach(element =>{
        this.companiesList?.push(element);
      })
    })
  }
  dropDownDepartments(){
    this.employeesService.getDropDownDepartments$.subscribe(data =>{
      data.data?.departments?.forEach(element =>{
        this.departmentsList?.push(JSON.parse(JSON.stringify(element)));
      })
      // this.departmentsList = data.data?.departments!.toString();
      console.log("--------------------111-----------------------");
      console.log(this.departmentsList[1]);
      console.log("-------------------------------------------");
      console.log(this.departmentsList);
      console.log("-------------------------------------------");

    })
  }
  dropDownLocations(){
    this.employeesService.getDropDownLocations$.subscribe(data =>{
      data.data?.locations?.forEach(element =>{
         this.locationsList?.push(element);
      })
    })
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    // @ts-ignore
    this.employeesService.formData = {

      id: 0,
      lastName: '',
      firstName: "",
      email: "",
      birthday: new Date(),
      createdAt: new Date(),
      enabled: false,
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
