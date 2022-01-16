import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';
import {AlertService} from "../service/alert.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false

  constructor(private router: Router,
              private authenticationService: AuthenticationService
    , private alertService: AlertService) {
  }

  ngOnInit() {
  }


  checkLogin() {
    (this.authenticationService.authenticate(this.username, this.password).subscribe(
        data => {
          this.router.navigate([''])
          this.invalidLogin = false
        },
        error => {
          this.alertService.error(error);
          this.invalidLogin = true
          console.log("Authentication failed")
        }
      )
    );

  }


  public goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }

}
