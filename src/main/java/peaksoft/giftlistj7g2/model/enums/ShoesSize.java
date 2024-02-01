package peaksoft.giftlistj7g2.model.enums;

public enum ShoesSize {
    VALUE1(35),
    VALUE2(36),
    VALUE3(37),
    VALUE4(38),
    VALUE5(39),
    VALUE6(40),
    VALUE7(41),
    VALUE8(42),
    VALUE9(43),
    VALUE10(44),
    VALUE11(45);

    private final int numericValue;
     ShoesSize(int numericValue){
         this.numericValue = numericValue;
     }

     public int getNumericValue(){
         return numericValue;
     }
}
