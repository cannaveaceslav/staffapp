import {Component, OnInit} from '@angular/core';
import {ItemTypeService} from "../../../service/itemtype.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {UserService} from "../../../service/user.service";
import {UserRole} from "../../../interface/userRole";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  // userRoles?: Array<any>=[UserRole.USER.toString(),UserRole.ADMIN.toString()];
userRoles?: Array<any> = [UserRole[0],UserRole[1]]

  constructor(public userService: UserService
    , public dialogBox: MatDialogRef<EditUserComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    console.log(this.userRoles);
  }

  onClose() {
    this.dialogBox.close();
    this.userService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.userService.update$(form.value).subscribe(res => {
      this.snackBar.open(res.message, '', {
        duration: 5000,
        verticalPosition: 'top'
      })
    })

  }
}
