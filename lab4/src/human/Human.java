// Lucas Grönborg & Rickard Björklund

package human;

public abstract class Human {
    protected String name;
    protected String securityNum;

    public static Human create (String name, String pnr) {
        int num = pnr.charAt(9)-'0';
        if(num == 0) {
            return new NonBinary(name, pnr);
        }
        else if ((num%2) == 0) {
            return new Woman(name, pnr);
        }
        else {
            return new Man(name, pnr);
        }
    }

    Human(){}

}
