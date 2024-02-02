package peaksoft.giftlistj7g2.model.enums;

public enum ShoesSize {
    THIRTY_FIVE(35),
    THIRTY_SIX(36),
    THIRTY_SEVEN(37),
    THIRTY_EIGHT(38),
    THIRTY_NINE(39),
    FORTY(40),
    FORTY_ONE(41),
    FORTY_TWO(42),
    FORTY_THREE(43),
    FORTRY_FOUR(44),
    FORTY_FIVE(45);

    private final int numericValue;
     ShoesSize(int numericValue){
         this.numericValue = numericValue;
     }

     public int getNumericValue(){
         return numericValue;
     }
}
