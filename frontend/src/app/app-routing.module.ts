import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from "./layout/layout.component";
import {ItemsComponent} from "./items/items.component";
import {EmployeesComponent} from "./employees/employees.component";
import {ReportsComponent} from "./reports/reports.component";
import {HomeComponent} from "./home/home.component";
import {AddEmployeeComponent} from "./add-employee/add-employee.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'layout', component: LayoutComponent},
  {path: 'employees', component: EmployeesComponent},
  {path: 'items', component: ItemsComponent},
  {path: 'addemployee', component: AddEmployeeComponent},
  {path: 'reports', component: ReportsComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
