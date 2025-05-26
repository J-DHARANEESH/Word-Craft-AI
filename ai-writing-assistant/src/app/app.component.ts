import { Component } from '@angular/core';
import { NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ai-writing-assistant';
  constructor(private spinner: NgxSpinnerService, private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        this.spinner.show();
      } else if (
        event instanceof NavigationEnd ||
        event instanceof NavigationCancel ||
        event instanceof NavigationError
      ) {
        setTimeout(() => {
          this.spinner.hide();
        }, 400); // Optional delay for smooth transition
      }
    });
  }
}

