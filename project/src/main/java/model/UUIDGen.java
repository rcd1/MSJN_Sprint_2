package model;
import java.util.UUID;

public class UUIDGen {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){ 
        UUID id = UUID.randomUUID();
        System.out.println(id);
        }
    }
}
