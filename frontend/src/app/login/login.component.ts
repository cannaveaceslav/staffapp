import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

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
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  checkLogin2() {
    if (this.authenticationService.authenticate(this.username, this.password)) {
      console.log('authentication failed: '+this.username+'/'+this.password);
      this.router.navigate([''])
      this.invalidLogin = false
    } else
      this.invalidLogin = true
  }

  checkLogin() {
    (this.authenticationService.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigate([''])
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        console.log("Authentication failed")
      }
    )
    );

  }

}
