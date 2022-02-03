import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { EmployeesComponent } from './employees/employees.component';
import { ItemComponent } from './item/item.component';
import { ReportsComponent } from './reports/reports.component';
import { HomeComponent } from './home/home.component';
import { AddEmployeeComponent } from './employees/add-employee/add-employee.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminComponent } from './admin/admin.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import {BasicAuthHttpInterceptorService} from "./service/basicauthhttpinterceptor.service";
import { RegistrationComponent } from './registration/registration.component';
import { ItemtypeComponent } from './itemtype/itemtype.component';
import { EmployeeComponent } from './employee/employee.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
// import {
//   MatToolbarModule,
//   MatTabsModule,
//   MatButtonModule,
//   MatInputModule,
//   MatDatepickerModule,
//   MatNativeDateModule,
//   MatCheckboxModule,
//   MatRadioModule
// } from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    EmployeesComponent,
    ItemComponent,
    ReportsComponent,
    HomeComponent,
    AddEmployeeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    AdminComponent,
    RegistrationComponent,
    ItemtypeComponent,
    EmployeeComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    // MatToolbarModule,
    // MatTabsModule,
    // MatButtonModule,
    // MatInputModule,
    // MatDatepickerModule,
    // MatNativeDateModule,
    // MatCheckboxModule,
    // MatRadioModule,
    DragDropModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule
  ],
  providers: [  {
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
