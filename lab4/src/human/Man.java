package human;

class Man extends Human {
    Man(String n, String pnr) {
        this.name = n;
        this.securityNum = pnr;
    }
    public String toString() {
        return "Jag är man och heter " + name;
    }
}
