package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public class TranslateBot extends TelegramLongPollingBot {

    //private String db = "mongodb://java1:monkey1@ds241121.mlab.com:41121/heroku_sbfcgdq8";

    private static final String BOT_TOKEN = "613394827:AAG1FJv7NHEV-JlMhDoeyYbM0QydVtwDWps";
    private static final String TRANSLATE_BOT = "TranslateBot";

//    MongoClientURI mongoClientURI = new MongoClientURI(db);
//    MongoClient mongoClient = new MongoClient(mongoClientURI);
//    MongoDatabase mongoDatabase = mongoClient.getDatabase(mongoClientURI.getDatabase());

    @Override
    public void onUpdateReceived(Update update) {

        //MongoCollection<Document> expenses = mongoDatabase.getCollection("margulizExpenses");

        //String text1 = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();//create message
        //sendMessage.setChatId(update.getMessage().getChatId());
//        if(text1.equals("/start")){
//            sendMessage.setText("Enter expenses");
//        }else if(text1.equals("/all")){
//            FindIterable<Document> all = expenses.find();
//            double sum = 0;
//            for(Document doc:all){
//                for(Map.Entry<String, Object> entry: doc.entrySet()){
//                    if(!entry.getKey().equals("_id")){
//                        String value = (String) entry.getValue();
//                        sum += Double.valueOf(value);
//
//                    }
//                }
//            }sendMessage.setText(sum + " ");
//        }
//
//        else{
//            Document document = new Document();
//            document.append(Integer.toString(update.getMessage().getMessageId()), text1);
//            expenses.insertOne(document);
//
//            sendMessage.setText("added");
//        }
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

        Translator tr = new Translator();
        if(!update.hasMessage()){
            return;
        }
        String text = update.getMessage().getText(); //get message from user
        long chatId = update.getMessage().getChatId();


        try {
            sendMessage.setText(tr.TrText(text));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sendMessage.setChatId(chatId);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) { // send message
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return TRANSLATE_BOT;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
