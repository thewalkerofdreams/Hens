package es.iesnervion.yeray.gallinas.ViewModels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityVM extends ViewModel {
    private MutableLiveData<String> nick;
    private MutableLiveData<String> password;

    public MainActivityVM(){
        nick = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }

    //Get y Sets
    public LiveData<String> getNick(){
        return this.nick;
    }

    public void setNick(String nick){
        this.nick.setValue(nick);
    }

    public LiveData<String> getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password.setValue(password);
    }
}
