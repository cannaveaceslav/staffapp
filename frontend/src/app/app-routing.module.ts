import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from "./layout/layout.component";
import {ItemComponent} from "./item/item.component";
import {EmployeesComponent} from "./employees/employees.component";
import {ReportsComponent} from "./reports/reports.component";
import {HomeComponent} from "./home/home.component";
import {AddEmployeeComponent} from "./employees/add-employee/add-employee.component";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {AuthGaurdService} from "./service/auth-gaurd.service";
import {AdminComponent} from "./admin/admin.component";
import {RegistrationComponent} from "./registration/registration.component";
import {ItemtypeComponent} from "./itemtype/itemtype.component";
import {EmployeeComponent} from "./employee/employee.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'logout', component: LogoutComponent, canActivate: [AuthGaurdService]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGaurdService]},
  {path: 'layout', component: LayoutComponent, canActivate: [AuthGaurdService]},
  {path: 'employees', component: EmployeesComponent, canActivate: [AuthGaurdService]},
  {path: 'employee/:id', component: EmployeeComponent, canActivate: [AuthGaurdService]},
  {path: 'items', component: ItemComponent, canActivate: [AuthGaurdService]},
  {path: 'item-types', component: ItemtypeComponent, canActivate: [AuthGaurdService]},
  {path: 'addemployee', component: AddEmployeeComponent, canActivate: [AuthGaurdService]},
  {path: 'reports', component: ReportsComponent, canActivate: [AuthGaurdService]},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGaurdService]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
