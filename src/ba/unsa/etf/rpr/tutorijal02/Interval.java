package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pripadaPocetnaIntervalu;
    private boolean pripadaKrajnjaIntervalu;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pripadaPocetnaIntervalu, boolean pripadaKrajnjaIntervalu) {
        if(pocetnaTacka>krajnjaTacka) throw new IllegalArgumentException("Pocetna tacka je veca od krajnje!");
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pripadaPocetnaIntervalu = pripadaPocetnaIntervalu;
        this.pripadaKrajnjaIntervalu = pripadaKrajnjaIntervalu;
    }

    public Interval() {
        pocetnaTacka=0;
        krajnjaTacka=0;
        pripadaPocetnaIntervalu=false;
        pripadaKrajnjaIntervalu=false;
    }

    public boolean isNull() {
        return pocetnaTacka==0 && krajnjaTacka==0;
    }

    public boolean isIn(double tacka) {
        if(pripadaPocetnaIntervalu) {
            if(pripadaKrajnjaIntervalu) {
                return (tacka>=pocetnaTacka && tacka<=krajnjaTacka);
            }
            else return(tacka>=pocetnaTacka && tacka<krajnjaTacka);
        }
        else {
            if(pripadaKrajnjaIntervalu) {
                return (tacka>pocetnaTacka && tacka<=krajnjaTacka);
            }
        }
        return (tacka>pocetnaTacka && tacka<krajnjaTacka);

    }

    public Interval intersect(Interval I){
        Interval i = new Interval();
        double a=this.pocetnaTacka, b=this.krajnjaTacka, c=I.pocetnaTacka, d=I.krajnjaTacka;
        if((a<c && a<d && b<c && b<d) || (a>c && a>d && b>c && b>d));
        else if (a < c && c < b && b < d  && a < d) {
            if (I.pripadaPocetnaIntervalu && this.pripadaKrajnjaIntervalu)
                i = new Interval(c, b, true, true);
            else if (I.pripadaPocetnaIntervalu)
                i = new Interval(c, b, true, false);
            else if (this.pripadaKrajnjaIntervalu)
                i = new Interval(c, b, false, true);
            else i = new Interval(c, b, false, false);
        }
        else if (d > a && d > b  && c < a && c < b ) {
            if (this.pripadaPocetnaIntervalu && this.pripadaKrajnjaIntervalu)
                i = new Interval(a, b, true, true);
            else if (this.pripadaPocetnaIntervalu)
                i = new Interval(a, b, true, false);
            else if (this.pripadaKrajnjaIntervalu)
                i = new Interval(a, b, false, true);
            else i = new Interval(a, b, false, false);
        }
        else if (a > c && a < d && b > c && b > d ) {
            if (this.pripadaPocetnaIntervalu && I.pripadaKrajnjaIntervalu)
                i = new Interval(a, d, true, true);
            else if (this.pripadaPocetnaIntervalu)
                i = new Interval(a, d, true, false);
            else if (I.pripadaKrajnjaIntervalu)
                i = new Interval(a, d, false, true);
            else i = new Interval(a, d, false, false);
        }
        else if (b > c && b > d &&  d > a && c > a) {
            if(I.pripadaPocetnaIntervalu && I.pripadaKrajnjaIntervalu)
                i = new Interval(c, d, true, true);
            else if(I.pripadaPocetnaIntervalu)
                i = new Interval(c, d, true, false);
            else if(I.pripadaKrajnjaIntervalu)
                i = new Interval(c, d, false, true);
            else
                i = new Interval(c, d, false, false);
        }
        return i;
    }

    public static Interval intersect(Interval I1, Interval I2){
        Interval I = new Interval();
        double a=I1.pocetnaTacka, b=I1.krajnjaTacka, c=I2.pocetnaTacka, d=I2.krajnjaTacka;
        if((a<c && a<d && b<c && b<d) || (a>c && a>d && b>c && b>d));
        else if (a < c && c < b && b < d  && a < d) {
            if (I2.pripadaPocetnaIntervalu && I1.pripadaKrajnjaIntervalu)
                I = new Interval(c, b, true, true);
            else if (I2.pripadaPocetnaIntervalu)
                I = new Interval(c, b, true, false);
            else if (I1.pripadaKrajnjaIntervalu)
                I = new Interval(c, b, false, true);
            else I = new Interval(c, b, false, false);
        }
        else if (d > a && d > b  && c < a && c < b ) {
            if (I1.pripadaPocetnaIntervalu && I1.pripadaKrajnjaIntervalu)
                I = new Interval(a, b, true, true);
            else if (I1.pripadaPocetnaIntervalu)
                I = new Interval(a, b, true, false);
            else if (I1.pripadaKrajnjaIntervalu)
                I = new Interval(a, b, false, true);
            else I = new Interval(a, b, false, false);
        }
        else if (a > c && a < d && b > c && b > d ) {
            if (I1.pripadaPocetnaIntervalu && I2.pripadaKrajnjaIntervalu)
                I = new Interval(a, d, true, true);
            else if (I1.pripadaPocetnaIntervalu)
                I = new Interval(a, d, true, false);
            else if (I2.pripadaKrajnjaIntervalu)
                I = new Interval(a, d, false, true);
            else I = new Interval(a, d, false, false);
        }
        else if (b > c && b > d &&  d > a && c > a) {
            if(I2.pripadaPocetnaIntervalu && I2.pripadaKrajnjaIntervalu)
                I = new Interval(c, d, true, true);
            else if(I2.pripadaPocetnaIntervalu)
                I = new Interval(c, d, true, false);
            else if(I2.pripadaKrajnjaIntervalu)
                I = new Interval(c, d, false, true);
            else
                I = new Interval(c, d, false, false);
        }
        return I;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Interval) {
            Interval I = (Interval) o;
            return (I.pocetnaTacka==this.pocetnaTacka && I.krajnjaTacka==this.krajnjaTacka && I.pripadaPocetnaIntervalu==this.pripadaPocetnaIntervalu && I.pripadaKrajnjaIntervalu==this.pripadaKrajnjaIntervalu);
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "";
        if(pripadaPocetnaIntervalu) s+="[";
        else s+="(";
        if(pocetnaTacka==0 && krajnjaTacka==0) s+="";
        else s+=pocetnaTacka + "," + krajnjaTacka;
        if(pripadaKrajnjaIntervalu) s+="]";
        else s+=")";
        return s;
    }
}
