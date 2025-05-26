import { Component } from '@angular/core';
import { AiService } from '../../core/ai.service';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import { MatDialog } from '@angular/material/dialog';
import { PdfDownloadComponent } from '../pdf-download/pdf-download.component';

@Component({
  standalone: false,
  selector: 'app-note-editor',
  templateUrl: './note-editor.component.html',
  styleUrls: ['./note-editor.component.scss'],
})
export class NoteEditorComponent {
  note: string = '';
  isDarkMode = false;
  isLoading = false;
  errorMessage: any;
  pdfTitle: string = 'My Document';
  includeDate: boolean = true;

  constructor(private aiService: AiService, private dialog: MatDialog) {
    pdfMake.vfs = pdfFonts.vfs;
  }

  askAI(action: 'summarize' | 'rephrase' | 'expand') {
    this.isLoading = true;
    this.errorMessage = null;

    this.aiService.getSuggestion(this.note, action).subscribe({
      next: (res) => {
        this.note = res.result;
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Oops! Something went wrong. Please try again later.';
        console.error('AI Service Error:', err);
      }
    });
  }

  exportAsPDF() {
    this.dialog.open(PdfDownloadComponent, {
      width: '400px',
      data: { note: this.note }
    });
  }
  
}
