package model.audit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Action {
    private String name;
    private Timestamp timestamp;

    public Action(String name){
        this.name=name;
        this.timestamp=new java.sql.Timestamp(System.currentTimeMillis());
    }
}
