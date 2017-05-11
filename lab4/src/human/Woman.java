// Lucas Grönborg & Rickard Björklund

package human;

class Woman extends Human {
    Woman(String n, String pnr) {
        this.name = n;
        this.securityNum = pnr;
    }

    public String toString() {
        return "Jag är kvinna och heter " + name;
    }
}
