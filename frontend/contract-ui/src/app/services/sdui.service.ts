import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SduiComponent {
  id: string;
  type: 'header' | 'table' | 'action';
  properties: Record<string, unknown>;
}

export interface SduiScreen {
  id: string;
  title: string;
  components: SduiComponent[];
}

@Injectable({ providedIn: 'root' })
export class SduiService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'https://congenial-umbrella-g4qx4vqr75gfvq47-8080.app.github.dev/api';

  getContractsScreen(): Observable<SduiScreen> {
    return this.http.get<SduiScreen>(`${this.apiUrl}/v1/ui/contracts`);
  }

  getContracts(): Observable<Record<string, unknown>[]> {
    return this.http.get<Record<string, unknown>[]>(`${this.apiUrl}/v1/contracts`);
  }
}
