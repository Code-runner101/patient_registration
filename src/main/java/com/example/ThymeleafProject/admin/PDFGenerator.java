package com.example.ThymeleafProject.admin;

import com.example.ThymeleafProject.patient.Patient;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.io.File;
import java.io.IOException;

public class PDFGenerator {
    public void fillPdfTemplate(Patient patient) {
        try (PDDocument document = PDDocument.load(new File("/Users/macbookpro/Desktop/Уник/ППС/contract_template.pdf"))) {
            PDType0Font font = PDType0Font.load(document, new File("/System/Library/Fonts/Supplemental/Arial.ttf"));
            PDPage page1 = document.getPage(0);
            PDPageContentStream contentStreamPg1 = new PDPageContentStream(document, page1, PDPageContentStream.AppendMode.APPEND, true);
            contentStreamPg1.beginText();
            contentStreamPg1.setFont(font, 14);
            contentStreamPg1.newLineAtOffset(74  , 650);

            String surname = patient.getSurname();
            contentStreamPg1.showText(surname);
            float surnameWidth = font.getStringWidth(surname) / 1000 * 13;
            contentStreamPg1.newLineAtOffset(surnameWidth + 20, 0);

            String firstName = patient.getFirstName();
            contentStreamPg1.showText(firstName);
            float firstNameWidth = font.getStringWidth(firstName) / 1000 * 13;
            contentStreamPg1.newLineAtOffset(firstNameWidth + 20, 0);

            if(patient.getPatronymic() != null) {
                String patronymic = patient.getPatronymic();
                showTextWrapped(contentStreamPg1, font, 14, patronymic, 10);
//                contentStreamPg1.showText(patronymic);
            } else {
                contentStreamPg1.showText("");
            }

            contentStreamPg1.endText();
            contentStreamPg1.close();

            PDPage page2 = document.getPage(1);
            PDPageContentStream contentStreamPg2 = new PDPageContentStream(document, page2, PDPageContentStream.AppendMode.APPEND, true);
            String citizen = patient.getCitizenship();
//            PDType0Font font = PDType0Font.load(document, new File("/System/Library/Fonts/Supplemental/Arial.ttf"));

            contentStreamPg2.beginText();
            contentStreamPg2.setFont(font, 14);
            contentStreamPg2.newLineAtOffset(382 , 548);
            contentStreamPg2.showText(patient.getSurname());
            contentStreamPg2.newLineAtOffset(-34 , -16);
            contentStreamPg2.showText(patient.getFirstName());
            contentStreamPg2.newLineAtOffset(34 , -16 );
            if(patient.getPatronymic() != null)
                contentStreamPg2.showText(patient.getPatronymic());
            else
                contentStreamPg2.showText("");
            contentStreamPg2.newLineAtOffset(-34, -16);
            contentStreamPg2.showText(patient.getPhoneNum());
            contentStreamPg2.newLineAtOffset(107, -16);
            contentStreamPg2.showText(citizen);
            contentStreamPg2.newLineAtOffset(-90 , -16);
            if(citizen.equals("РФ"))
                contentStreamPg2.showText(patient.getPassportSer());
            else
                contentStreamPg2.showText("");
            contentStreamPg2.newLineAtOffset(0, -16);
            contentStreamPg2.showText(patient.getPassportNum());
            contentStreamPg2.newLineAtOffset(0, -16);
            contentStreamPg2.showText(String.valueOf(patient.getBirthdate()));
            contentStreamPg2.endText();

            contentStreamPg2.close();

            document.save("/Users/macbookpro/Desktop/Уник/ППС/Договоры/filled_contract_" + patient.getRegNum() + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTextWrapped(PDPageContentStream contentStream, PDType0Font font, float fontSize, String text, float width) throws IOException {
        int lastSpace = -1;
        float leading = 1.5f;

        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }
            String subString = text.substring(0, spaceIndex);
            float size = fontSize * font.getStringWidth(subString) / 1000;
            if (size > width) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                contentStream.showText(subString);
                contentStream.newLineAtOffset(0, -leading);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                contentStream.showText(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
    }
}
