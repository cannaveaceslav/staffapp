import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LayoutComponent} from './layout/layout.component';
import {EmployeesComponent} from './employees/employees.component';
import {ItemComponent} from './item/item.component';
import {ReportsComponent} from './reports/reports.component';
import {HomeComponent} from './home/home.component';
import {AddEmployeeComponent} from './employees/add-employee/add-employee.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import {AdminComponent} from './admin/admin.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {BasicAuthHttpInterceptorService} from "./service/basicauthhttpinterceptor.service";
import {RegistrationComponent} from './registration/registration.component';
import {ItemtypeComponent} from './itemtype/itemtype.component';
import {EmployeeComponent} from './employee/employee.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {DashboardComponent} from './admin/dashboard/dashboard.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import {MatTabsModule} from "@angular/material/tabs";
import {MatRadioModule} from "@angular/material/radio";
import {MatNativeDateModule} from "@angular/material/core";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {PaginatorModule} from 'primeng/paginator';
import {TreeTableModule} from 'primeng/treetable';
import {TableModule} from 'primeng/table';
import {MatTableModule} from '@angular/material/table';
import {LocationsAdminComponent} from './admin/locations-admin/locations-admin.component';
import {ItemsAdminComponent} from "./admin/items-admin/items-admin.component";
import {EmployeesAdminComponent} from "./admin/employees-admin/employees-admin.component";
import {ItemTypesAdminComponent} from "./admin/item-types-admin/item-types-admin.component";
import {UsersAdminComponent} from './admin/users-admin/users-admin.component';
import {ConfirmPageComponent} from './registration/confirm-page/confirm-page.component';
import {RadioButtonModule} from "primeng/radiobutton";
import {ButtonModule} from "primeng/button";
import {MatDialogModule} from "@angular/material/dialog";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTooltipModule} from "@angular/material/tooltip";
import { ItemsComponent } from './items/items.component';
import {MatSortModule} from "@angular/material/sort";

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
    DashboardComponent,
    LocationsAdminComponent,
    ItemsAdminComponent,
    EmployeesAdminComponent,
    ItemTypesAdminComponent,
    UsersAdminComponent,
    ConfirmPageComponent,
    ItemsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        MatToolbarModule,
        MatTabsModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatCheckboxModule,
        MatRadioModule,
        DragDropModule,
        BrowserAnimationsModule,
        LayoutModule,
        MatToolbarModule,
        MatButtonModule,
        RadioButtonModule,
        ButtonModule,
        MatSidenavModule,
        MatDialogModule,
        MatIconModule,
        MatListModule,
        MatGridListModule,
        MatCardModule,
        MatMenuModule,
        PaginatorModule,
        TreeTableModule,
        TableModule,
        MatTableModule,
        MatPaginatorModule,
        MatTooltipModule,
        MatSortModule

    ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
