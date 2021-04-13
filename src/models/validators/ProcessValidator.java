package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Process;

public class ProcessValidator {

    public static List<String> validate(Process p){
        List<String> errors = new ArrayList<String>();

        String process_name_error = _validateProcess_name(p.getProcess_name());
        if(!process_name_error.equals("")){
            errors.add(process_name_error);
        }

        String message_error = _validateMessage(p.getMessage());
        if(!message_error.equals("")){
            errors.add(message_error);
        }

        return errors;
    }

    private static String _validateProcess_name(String process_name){
        if(process_name == null || process_name.equals("")){
            return "工程名を入力してください。";
        }

        return "";
    }

    private static String _validateMessage(String message){
        if(message == null || message.equals("")){
            return "メッセージを入力してください。";
        }

        return "";
    }

}
