import { Component, OnInit } from '@angular/core';
import {ItemTypeService} from "../../../service/itemtype.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {DepartmentService} from "../../../service/department.service";

@Component({
  selector: 'app-edit-department',
  templateUrl: './edit-department.component.html',
  styleUrls: ['./edit-department.component.css']
})
export class EditDepartmentComponent implements OnInit {


  constructor(public departmentService: DepartmentService
    , public dialogBox: MatDialogRef<EditDepartmentComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onClose() {
    this.dialogBox.close();
    this.departmentService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.departmentService.update$(form.value).subscribe(res=>{
      this.snackBar.open(res.message,'',{
        duration:5000,
        verticalPosition:'top'
      })
    })

  }
}
