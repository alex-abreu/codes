public class Horario
{
    private int h, m;
    
    Horario(int h, int m)
    {
       setH(h);
       setM(m);
    }

    public void setH(int h)
    {
        if(h < 0 || h > 23)
        {
            this.h = 0;
        } else {
            this.h = h;
        }
    }

    public int getH()
    {
        return this.h;
    }

    public void setM(int m)
    {
        if(m < 0 || m > 59)
        {
            this.m = 0;
        } else {
            this.m = m;
        }
    }

    public int getM()
    {
        return this.m;
    }

}