public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r = Math.sqrt((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
        return r;
    }
    public double calcForceExertedBy(Planet P){
        double r = this.calcDistance(P);
        double f = 6.67e-11 * this.mass * P.mass/(r * r);
        return f;
    }

    public double calcForceExertedByX(Planet P){
        double f = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dx = P.xxPos - this.xxPos;
        double fx = f * dx / r;
        return fx;
    }

    public double calcForceExertedByY(Planet P){
        double f = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dy = P.yyPos - this.yyPos;
        double fy = f * dy / r;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netfx = 0;
        for (Planet element : allPlanets) {
            if (this.equals(element)){
                continue;
            }else{
                netfx = netfx + this.calcForceExertedByX(element);
            }
        }
        return netfx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netfy = 0;
        for (Planet element : allPlanets) {
            if (this.equals(element)){
                continue;
            }else{
                netfy = netfy + this.calcForceExertedByY(element);
            }
        }
        return netfy;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}