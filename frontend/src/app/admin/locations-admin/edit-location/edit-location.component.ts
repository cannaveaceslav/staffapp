import { Component, OnInit } from '@angular/core';
import {ItemTypeService} from "../../../service/itemtype.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {LayoutService} from "../../../service/layout.service";

@Component({
  selector: 'app-edit-location',
  templateUrl: './edit-location.component.html',
  styleUrls: ['./edit-location.component.css']
})
export class EditLocationComponent implements OnInit {

  constructor(public locationService: LayoutService
    , public dialogBox: MatDialogRef<EditLocationComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onClose() {
    this.dialogBox.close();
    this.locationService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.locationService.update$(form.value).subscribe(res=>{
      this.snackBar.open(res.message,'',{
        duration:5000,
        verticalPosition:'top'
      })
    })

  }
}
