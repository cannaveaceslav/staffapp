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

  public companiesList:Array<any>=[];
  public departmentsList: Array<any>=[];
  public locationsList: Array<any>=[];


  constructor(public employeesService: EmployeesService
    , public dialogBox: MatDialogRef<EditEmployeeComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
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
        this.departmentsList?.push(element);
      })
    })
  }
  dropDownLocations(){
    this.employeesService.getDropDownLocations$.subscribe(data =>{
      data.data?.locations?.forEach(element =>{
        this.locationsList?.push(element);
      })
    })
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
