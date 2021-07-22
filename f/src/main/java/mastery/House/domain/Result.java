package mastery.House.domain;

import java.util.ArrayList;
import java.util.List;

public class Result<T>  extends Response{

    private T payload;


    public T getPayload(){
        return payload;

    }
    public void setPayload(T payload){
        this.payload = payload;
    }



}
