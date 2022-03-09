import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {MessageService} from "primeng/api";
import {ItemTypeService} from "../../../service/itemtype.service";
import {NgForm} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.css']
})
export class AddTypeComponent implements OnInit {

  @Output()
  submitted = new EventEmitter();


  constructor(public itemTypeService: ItemTypeService
    , public dialogBox: MatDialogRef<AddTypeComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.resetForm();
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    this.itemTypeService.formData = {

      id: 0,
      typeName: "",
      createdAt: new Date(),
      description: "",
      enabled: false,
      image: new Blob(),
      modifiedAt: new Date(),


    }
  }

  onClose() {
    this.dialogBox.close();
    this.itemTypeService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.itemTypeService.save$(form.value).subscribe(res => {
        this.resetForm(form);
        this.snackBar.open(res.message,'',{
          duration:3500,
          verticalPosition:'top'
        });
      }
    )
  }


}
