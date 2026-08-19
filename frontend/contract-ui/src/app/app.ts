import { Component } from '@angular/core';
import { DynamicRendererComponent } from './dynamic-renderer.component';

@Component({
  selector: 'app-root',
  imports: [DynamicRendererComponent],
  template: '<app-dynamic-renderer />',
  styleUrl: './app.scss'
})
export class App {
}
