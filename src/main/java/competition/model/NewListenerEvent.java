package competition.model;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NewListenerEvent {
 private static final String EMAIL = "email";
 private static final String ID_LISTENER = "idListener";
 private Map<String, String> map;

 public NewListenerEvent(String message) {
  this.map =  new Gson().fromJson(message,
    new TypeToken<Map<String, String>>() {
   }.getType());
 }
 
 public int idListener() {
  return Integer.valueOf(this.map.get(ID_LISTENER)); 
 }
 
 public String email() {
  return this.map.get(EMAIL); 
 }
}
