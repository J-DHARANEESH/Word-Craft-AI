import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MarkdownModule } from 'ngx-markdown';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { NoteEditorComponent } from './pages/note-editor/note-editor.component';
import { MatCardModule } from '@angular/material/card';
import { HomeComponent } from './pages/home/home.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { PdfDownloadComponent } from './pages/pdf-download/pdf-download.component';
import { MatDialogContent } from '@angular/material/dialog';
import { MatDialogActions } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  declarations: [AppComponent, NoteEditorComponent, HomeComponent, PdfDownloadComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MarkdownModule.forRoot(),
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    NgxSpinnerModule,
    MatSlideToggleModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatDialogContent,
    MatDialogActions,
    MatCheckboxModule  
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
