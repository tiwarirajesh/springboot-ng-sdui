import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SduiComponent, SduiScreen, SduiService } from './services/sdui.service';

@Component({
  selector: 'app-dynamic-renderer',
  imports: [CommonModule],
  templateUrl: './dynamic-renderer.component.html',
  styleUrl: './dynamic-renderer.component.scss'
})
export class DynamicRendererComponent implements OnInit {
  private readonly sduiService = inject(SduiService);

  protected readonly screen = signal<SduiScreen | null>(null);
  protected readonly contracts = signal<Record<string, unknown>[]>([]);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);

  ngOnInit(): void {
    this.sduiService.getContractsScreen().subscribe({
      next: (screen) => {
        this.screen.set(screen);
        this.loadData(screen);
      },
      error: () => {
        this.loading.set(false);
        this.error.set('The screen definition could not be loaded.');
      }
    });
  }

  protected components(): SduiComponent[] {
    return this.screen()?.components ?? [];
  }

  protected componentText(component: SduiComponent, key: string): string {
    return String(component.properties[key] ?? '');
  }

  protected columns(component: SduiComponent): { field: string; label: string }[] {
    return (component.properties['columns'] as { field: string; label: string }[]) ?? [];
  }

  protected value(contract: Record<string, unknown>, field: string): string {
    return String(contract[field] ?? '');
  }

  private loadData(screen: SduiScreen): void {
    const table = screen.components.find((component) => component.type === 'table');
    const dataUrl = table?.properties['dataUrl'];

    if (typeof dataUrl !== 'string') {
      this.loading.set(false);
      return;
    }

    this.sduiService.getContracts().subscribe({
      next: (contracts) => {
        this.contracts.set(contracts);
        this.loading.set(false);
      },
      error: () => {
        this.loading.set(false);
        this.error.set('The contract data could not be loaded.');
      }
    });
  }
}
