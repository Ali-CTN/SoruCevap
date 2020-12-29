package com.example.SinavSavaslari.Common;

import com.example.SinavSavaslari.Model.Question;
import com.example.SinavSavaslari.Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Common {

    public static String categoryId,categoryName;
    public static User currentUser;
    public static List<Question> questionList=new ArrayList<>();
    public static void shuffleList(){
        Collections.shuffle(questionList);
    }
    

}
