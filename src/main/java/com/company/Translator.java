package com.company;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class Translator {

    public String TrText(String textToTran) throws IOException, GeneralSecurityException {

        String text;
        Translate t = new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance(), null)
                // Set your application name
                .setApplicationName("Marguliz")
                .build();
        Translate.Translations.List list = t.new Translations().list(
                Arrays.asList(
                        // Pass in list of strings to be translated
                        textToTran),
                // Target language
                "EN");

        // TODO: Set your API-Key from https://console.developers.google.com/
        list.setKey("AIzaSyAUzsj4Tt9FfqlvEdCfPqsAZQQtsbN8aEM");
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations()) {
            text = StringEscapeUtils.unescapeHtml4(translationsResource.getTranslatedText());
            return text;
            //translationsResource.getTranslatedText();
        }
        return "";
    }


}