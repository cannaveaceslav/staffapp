import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {NgForm} from "@angular/forms";
import {ItemService} from "../../../service/item.service";

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {



  public locationsList: Array<any>=[];
  public employeesList: Array<any>=[];
  public typesList: Array<any>=[];



  @Output()
  submitted = new EventEmitter();

  constructor(public itemService: ItemService
    , public dialogBox: MatDialogRef<AddItemComponent>
    , private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.resetForm();
    this.dropDownEmployees()
    this.dropDownTypes()
  }

  dropDownEmployees(){
    this.itemService.getDropDownEmployees$.subscribe(data =>{
      data.data?.employees?.forEach(element =>{
        this.employeesList?.push(element);
      })
    })
  }
  dropDownTypes(){
    this.itemService.getDropDownItemTypes$.subscribe(data =>{
      data.data?.itemTypes?.forEach(element =>{
        this.typesList?.push(element);
      })
    })
  }


  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();

    // @ts-ignore
    this.itemService.formData = {
      id: 0,
      itemName: "",
      barcode: "",
      description: "",
      enabled: false,


    }
  }

  onClose() {
    this.dialogBox.close();
    this.itemService.filter('Register click');
  }

  onSubmit(form: NgForm) {
    console.log(form.value);

    this.itemService.save$(form.value).subscribe(res => {
        this.resetForm(form);
        this.snackBar.open(res.message, '', {
          duration: 3500,
          verticalPosition: 'top'
        });
      }
    )
  }
}
