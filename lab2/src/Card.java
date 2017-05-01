// Rickard Björklund & Lucas Grönborg

public class Card {

    private String value;
    private boolean faceup = false;
    private boolean matched = false;

    public Card(String value){
        this.value = value;
    }

    public void flip(){
        faceup = !faceup;
    }

    public String getValue(){
        if (faceup){
            return value;
        }else{
            return "";
        }
    }

    public void match(){
        matched = true;
    }

    public boolean isMatched(){
        return matched;
    }

}
