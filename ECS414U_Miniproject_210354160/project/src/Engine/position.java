package Engine;

public class position {
    public x x;
    public y y;
    public z z;
    public position(double x, double y, double z, double xd, double yd, double zd, double xp, double yp, double zp){
        this.x=new x(x, xd, xp);
        this.y=new y(y, yd, yp);
        this.z=new z(z, zd, zp);
    }
}
class x extends axis{public x(double Pos, double Dir, double Pln){super(Pos, Dir, Pln);}}
class y extends axis{public y(double Pos, double Dir, double Pln){super(Pos, Dir, Pln);}}
class z extends axis{public z(double Pos, double Dir, double Pln){super(Pos, Dir, Pln);}}
class axis{
    public double position;
    public double direction;
    public double plane;

    public axis(double Pos, double Dir, double Pln) {
        this.position=Pos;
        this.direction=Dir;
        this.plane=Pln;
    }
}