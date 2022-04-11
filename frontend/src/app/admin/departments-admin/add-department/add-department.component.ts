import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ItemTypeService} from "../../../service/itemtype.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {DepartmentService} from "../../../service/department.service";

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {

  @Output()
  submitted = new EventEmitter();


  constructor(public departmentService: DepartmentService
    , public dialogBox: MatDialogRef<AddDepartmentComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.resetForm();
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    this.departmentService.formData = {

      id: 0,
      departmentName: "",
      createdAt: new Date(),
      description: "",
      enabled: true,
      modifiedAt: new Date(),


    }
  }

  onClose() {
    this.dialogBox.close();
    this.departmentService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.departmentService.save$(form.value).subscribe(res => {
        this.resetForm(form);
        this.snackBar.open(res.message,'',{
          duration:3500,
          verticalPosition:'top'
        });
      }
    )
  }


}
