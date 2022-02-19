import {ComponentFixture, TestBed} from '@angular/core/testing';
import {LoginComponent} from './login.component';
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";
import {AlertService} from "../service/alert.service";
import {DebugElement} from "@angular/core";
import {User} from "../interface/user";
import {By} from "@angular/platform-browser";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let router: Router;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticate: AuthenticationService;
  let alertService: AlertService;
  let submitEl: DebugElement;
  let loginEl: DebugElement;
  let passwordEl: DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);

    // get test component from the fixture
    component = fixture.componentInstance;

    submitEl = fixture.debugElement.query(By.css('button'));
    loginEl = fixture.debugElement.query(By.css('input[type=email]'));
    passwordEl = fixture.debugElement.query(By.css('input[type=password]'));
    component = fixture.componentInstance;
    fixture.detectChanges();
    TestBed.configureTestingModule({
      declarations: [LoginComponent]
    });
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('Setting enabled to false disabled the submit button', () => {
    component.enabled = false;
    fixture.detectChanges();
    expect(submitEl.nativeElement.disabled).toBeTruthy();
  });

  it('Setting enabled to true enables the submit button', () => {
    component.enabled = true;
    fixture.detectChanges();
    expect(submitEl.nativeElement.disabled).toBeFalsy();
  });


});
