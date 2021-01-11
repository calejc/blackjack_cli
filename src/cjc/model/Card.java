package cjc.model;


public class Card {

    private String number;
    private String suit;
    private String cardName;
    private Integer value;
    private Boolean visible;
    private String art;

    public Card(){}
    public Card(String number, String suit){
        this.number = number;
        this.suit = suit;
        this.cardName = number + suit.charAt(0);
        this.visible = true; //Default to true, set to false for dealer's face down card
        setValue();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return this.visible ? "Card{" +
                cardName +
//                "number='" + number + '\'' +
//                ", suit='" + suit + '\'' +
//                ", cardName='" + cardName + '\'' +
//                ", value=" + value +
//                ", visible=" + visible +
                '}' : "Card{FACE_DOWN_CARD}";
    }

    public Integer getValue() {
        return value;
    }

    public void setValue() {
        if (this.getNumber().equals("A")){
            this.value = 11;
        } else {
            try {
                this.value = Integer.parseInt(this.getNumber());
            } catch (NumberFormatException e){
                this.value = 10;
            }
        }
    }

    public void setValue(int value){
        this.value = value;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


}
