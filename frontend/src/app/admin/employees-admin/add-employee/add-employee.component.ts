import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {EmployeesService} from "../../../service/employees.service";
import {MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {


  public companiesList: Array<any> = [];
  public departmentsList: Array<any> = [];
  public locationsList: Array<any> = [];
  selectedFile = null;


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

  dropDownCompanies() {
    this.employeesService.getDropDownCompanies$.subscribe(data => {
      data.data?.companies?.forEach(element => {
        this.companiesList?.push(element);
      })
    })
  }

  dropDownDepartments() {
    this.employeesService.getDropDownDepartments$.subscribe(data => {
      data.data?.departments?.forEach(element => {
        this.departmentsList?.push(element);
      })
    })
  }

  dropDownLocations() {
    this.employeesService.getDropDownLocations$.subscribe(data => {
      data.data?.locations?.forEach(element => {
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

  onFileSelected(event:any) {
    console.log(event);
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    console.log("unUpload clicked")
  }
}
