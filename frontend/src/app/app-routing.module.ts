import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from "./layout/layout.component";
import {ItemsComponent} from "./items/items.component";
import {EmployeesComponent} from "./employees/employees.component";
import {ReportsComponent} from "./reports/reports.component";
import {HomeComponent} from "./home/home.component";
import {AddEmployeeComponent} from "./add-employee/add-employee.component";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {AuthGaurdService} from "./service/auth-gaurd.service";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent, canActivate: [AuthGaurdService]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGaurdService]},
  {path: 'layout', component: LayoutComponent, canActivate: [AuthGaurdService]},
  {path: 'employees', component: EmployeesComponent, canActivate: [AuthGaurdService]},
  {path: 'items', component: ItemsComponent, canActivate: [AuthGaurdService]},
  {path: 'addemployee', component: AddEmployeeComponent, canActivate: [AuthGaurdService]},
  {path: 'reports', component: ReportsComponent, canActivate: [AuthGaurdService]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
