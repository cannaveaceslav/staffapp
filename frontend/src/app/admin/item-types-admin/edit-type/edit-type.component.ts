import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {MessageService} from "primeng/api";
import {ItemTypeService} from "../../../service/itemtype.service";
import {NgForm} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-edit-type',
  templateUrl: './edit-type.component.html',
  styleUrls: ['./edit-type.component.css']
})
export class EditTypeComponent implements OnInit {

  constructor(public itemTypeService: ItemTypeService
    , public dialogBox: MatDialogRef<EditTypeComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onClose() {
    this.dialogBox.close();
    this.itemTypeService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    this.itemTypeService.update$(form.value).subscribe(res=>{
      this.snackBar.open(res.message,'',{
        duration:5000,
        verticalPosition:'top'
      })
    })

  }
}
