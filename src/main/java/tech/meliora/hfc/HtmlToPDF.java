package tech.meliora.hfc;

import com.lowagie.text.Document;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class HtmlToPDF {
    public static void main(String[] args) throws IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("HtmlToPdf.pdf")));

        String userPassword = "some-pwd";
        String ownerPassword = "some-other-password";

        writer.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(),
                PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY, PdfWriter.STANDARD_ENCRYPTION_128);

        document.open();
        String htmlString = "<html><body> This is my Project </body></html>";
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(htmlString));
        document.close();

        System.out.println("PDF created successfully.");
    }
}
