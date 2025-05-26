import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

// Interface for the expected response from backend
export interface AiResponse {
  result: string;
}

@Injectable({ providedIn: 'root' })
export class AiService {
  private apiUrl = 'http://localhost:8080/api/ai';

  constructor(private http: HttpClient) {}

  getSuggestion(text: string, action: string): Observable<AiResponse> {
    return this.http.post<AiResponse>(`${this.apiUrl}/${action}`, { text }).pipe(
      catchError(this.handleError)
    );
  }

  // Basic error handler
  private handleError(error: HttpErrorResponse) {
    let errorMsg = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side/network error
      errorMsg = `Error: ${error.error.message}`;
    } else {
      // Backend returned an unsuccessful response code
      errorMsg = `Server returned code ${error.status}, message was: ${error.message}`;
    }
    // Optionally, log the error somewhere
    console.error(errorMsg);
    return throwError(() => new Error(errorMsg));
  }
}
