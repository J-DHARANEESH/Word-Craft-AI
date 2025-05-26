import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';

@Component({
standalone: false,
selector: 'app-pdf-download',
templateUrl: './pdf-download.component.html',
styleUrls: ['./pdf-download.component.scss']
})
export class PdfDownloadComponent {
heading: string = '';
includeDate: boolean = true;

constructor(
@Inject(MAT_DIALOG_DATA) public data: { note: string },
private dialogRef: MatDialogRef<PdfDownloadComponent>
) {
pdfMake.vfs = pdfFonts.vfs;
}

downloadPDF() {
const content = [
{ text: this.heading || 'Untitled', style: 'header' },
...(this.includeDate ? [{ text: new Date().toLocaleString(), style: 'subheader' }] : []),
{ text: this.data.note, style: 'content' }
];


const docDefinition = {
  content,
  styles: {
    header: {
      fontSize: 22,
      bold: true,
      alignment: 'center',
      margin: [0, 0, 0, 10]
    },
    subheader: {
      fontSize: 10,
      alignment: 'right',
      margin: [0, 0, 0, 10]
    },
    content: {
      fontSize: 12,
      lineHeight: 1.4
    }
  }
};

pdfMake.createPdf(docDefinition).download(`${this.heading || 'note'}.pdf`);
this.dialogRef.close();
}

close() {
this.dialogRef.close();
}
}

