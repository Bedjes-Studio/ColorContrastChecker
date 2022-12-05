package com.example.colorpicker.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContrastViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ContrastViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contrast fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}