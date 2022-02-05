import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RegistrationRequest} from "../interface/registrationRequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../service/authentication.service";
import {first} from "rxjs";
import {AlertService} from "../service/alert.service";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private baseURL = environment.serverUrl;
  registrationRequest!: RegistrationRequest;
  form!: FormGroup;
  loading = false;
  submitted = false;

  constructor(private router: Router
    , private httpClient: HttpClient
    , private alertService: AlertService
    , private formBuilder: FormBuilder
    , private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required,Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() {
    return this.form.controls;
  }

  public goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.form.value);

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;

    this.authenticationService.register(this.form.value)

      .pipe(first())

      .subscribe({
        next: () => {
          console.log('Registration successful');
          this.alertService.success('Registration successful', {keepAfterRouteChange: true});
          this.router.navigate(['../login']);
        },
        error: error => {
          this.alertService.error(error);
          this.loading = false;
        }
      });
  }

  register(registrationRequest: RegistrationRequest) {
    console.log('Register user:', registrationRequest);
    this.httpClient.post(this.baseURL + '/registration', registrationRequest);
    return this.router.navigate(['../login']);
  }
}
