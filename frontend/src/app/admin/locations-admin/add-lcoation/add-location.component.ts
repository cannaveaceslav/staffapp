import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ItemTypeService} from "../../../service/itemtype.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {LayoutService} from "../../../service/layout.service";
import {LocationType} from "../../../interface/LocationType";

@Component({
  selector: 'app-add-lcoation',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {
  locationTypes?: Array<any> = [LocationType[0],LocationType[1]]

  @Output()
  submitted = new EventEmitter();


  constructor(public locationService: LayoutService
    , public dialogBox: MatDialogRef<AddLocationComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.resetForm();
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    // @ts-ignore
    this.locationService.formData = {
      id: 0,
      description: "",
      available: false,
      pos_x: 0,
      pos_y: 0

    }
  }

  onClose() {
    this.dialogBox.close();
    this.locationService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.locationService.save$(form.value).subscribe(res => {
        this.resetForm(form);
        this.snackBar.open(res.message, '', {
          duration: 5000,
          verticalPosition: 'top'
        });
      }
    )
  }


}
