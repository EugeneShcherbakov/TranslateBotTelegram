package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TranslateBot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "613394827:AAG1FJv7NHEV-JlMhDoeyYbM0QydVtwDWps";
    private static final String TRANSLATE_BOT = "TranslateBot";

    @Override
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage()){
            return;
        }
        String text = update.getMessage().getText(); //get message from user
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();//create message
        sendMessage.setText("I can repeat: " +text);
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
