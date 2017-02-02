package travelsafe.service.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by Dorian on 2/1/2017.
 */
public class PdfFooter extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer(),
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() + 6, 0);
    }

    private Phrase footer(){
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 6, Font.BOLD);
        Phrase p = new Phrase("Travelsafe™ Company",ffont);
        return p;
    }

}
