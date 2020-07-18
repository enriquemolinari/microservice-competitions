package competition.model;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NewListenerEvent {
 private String msgAsJson;

 public NewListenerEvent(String message) {
  this.msgAsJson = message;
 }

 public Map<String, String> toMap() {
  return new Gson().fromJson(this.msgAsJson,
     new TypeToken<Map<String, String>>() {
    }.getType());
 }
}
