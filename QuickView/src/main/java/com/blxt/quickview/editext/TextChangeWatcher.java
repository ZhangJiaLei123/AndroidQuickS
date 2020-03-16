package com.blxt.quickview.editext;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TextChangeWatcher implements TextWatcher {
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
  
  public void onTextChanged(CharSequence s, int start, int before, int count) {}
  
  public void afterTextChanged(Editable s) {}
}


/* Location:              E:\Documents\workspace\Work4HeNeng\Project\QBox\QboxManage\app\libs\quickview-1.0.5.aar!\classes.jar!\com\blxt\quickview\editext\TextChangeWatcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */