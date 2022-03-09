import {Component, OnInit} from '@angular/core';
import {EmployeesService} from "../../../service/employees.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {ItemService} from "../../../service/item.service";

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  public locationsList: Array<any> = [];
  public employeesList: Array<any> = [];
  public typesList: Array<any> = [];


  constructor(public itemService: ItemService
    , public dialogBox: MatDialogRef<EditItemComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.dropDownEmployees()
    this.dropDownTypes()
  }

  dropDownEmployees() {
    this.itemService.getDropDownEmployees$.subscribe(data => {
      data.data?.employees?.forEach(element => {
        this.employeesList?.push(element);
      })
    })
  }

  dropDownTypes() {
    this.itemService.getDropDownItemTypes$.subscribe(data => {
      data.data?.itemTypes?.forEach(element => {
        this.typesList?.push(element);
      })
    })
  }


  onClose() {
    this.dialogBox.close();
    this.itemService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.itemService.update$(form.value).subscribe(res => {
      this.snackBar.open(res.message, '', {
        duration: 5000,
        verticalPosition: 'top'
      })
    })

  }

}
