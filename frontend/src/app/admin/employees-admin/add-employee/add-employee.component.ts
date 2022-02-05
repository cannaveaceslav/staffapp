import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {MessageService} from 'primeng/api';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';
import {EmployeesService} from "../../../service/employees.service";
import {Employee} from "../../../interface/employee";

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {


  defaultEmployee: Employee = new Employee();
  companies!: any[];
  departments!: any[];
  // socket: WebSocketSubject<WebSocketMessage> = webSocket('ws://localhost:8080/web-socket/' + 'WS01'/*this.userService.currentUser.username*/);

  @Output()
  submitted = new EventEmitter();
  articleDialog: boolean = true;

  constructor(private employeeService: EmployeesService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.companies = [
      {label: 'ISD SRL', value: '1'},
      {label: 'SERVICE SRL', value: '2'},
    ];
    this.companies = [
      {label: 'Administration department', value: '1'},
      {label: 'Service department', value: '2'},
      {label: 'IPC department', value: '3'},
      {label: 'Cost engineering', value: '4'},
      {label: 'IPA department', value: '5'},
      {label: 'PLC department', value: '6'},
    ];
    // this.connectWS();
  }

  // ngOnDestroy(): void {
  //   this.closeWebSocketSession();
  // }

  save() {
    if (this.checkProduct()) {
      let employee = new Employee();
      employee.lastName = this.defaultEmployee.lastName;
      employee.firstName = this.defaultEmployee.firstName;
      employee.company = this.defaultEmployee.company;
      employee.department = this.defaultEmployee.department;
      employee.email = this.defaultEmployee.email;
      employee.birthday = this.defaultEmployee.birthday;
      employee.location = this.defaultEmployee.location;
      employee.enabled = this.defaultEmployee.enabled;
      employee.image = this.defaultEmployee.image;
      this.employeeService.save$(employee);
      this.submitted.emit();
      this.defaultEmployee = new Employee();
    }
  }

  cancel() {
    this.submitted.emit();
  }

  checkProduct() {
    if (!(this.defaultEmployee.firstName
      && this.defaultEmployee.lastName
      && this.defaultEmployee.email
      && this.defaultEmployee.company
      && this.defaultEmployee.department
      && this.defaultEmployee.birthday)) {
      this.messageService.add({severity: 'error', summary: 'Complete all fields', detail: ''});
      return false;
    }
    return true;
  }

  // connectWS() {
  //   this.socket.subscribe(
  //     message => {
  //       console.log("Response: " + message.productId);
  //       this.defaultProduct.rfId = message.productId;
  //     },
  //     error => {
  //       console.error(error);
  //     });
  // }

  // closeWebSocketSession() {
  //   this.socket.complete();
  // }

}
