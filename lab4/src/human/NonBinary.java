package human;

class NonBinary extends Human {
    NonBinary(String n, String pnr) {
        this.name = n;
        this.securityNum = pnr;
    }

    public String toString() {
        return "Jag är icke-binär och heter " + name;
    }
}
