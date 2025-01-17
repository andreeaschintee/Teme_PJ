package Exercitiul1;
public class PerecheNumere {
    private Integer num;
    private Integer num2;
    public PerecheNumere() {}
    public PerecheNumere(int num, int num2) {
        if(num<num2) {
            this.num = num;
            this.num2 = num2;
        }
        else
        {
            this.num = num2;
            this.num2 = num;
        }
    }
    public int getNum() {
        return num;
    }
    public int getNum2() {
        return num2;
    }
    public void setNum(int num) {
        if(num<num2)
            this.num = num;
        else {
            this.num=num2;
            this.num2 = num;
        }
    }
    public void setNum2(int num2) {
        if(num2>num)
            this.num2 = num2;
        else
        {
            this.num2 = num;
            this.num = num2;
        }
    }
    @Override
    public String toString() {
        return "(" + num + "," + num2 + ")";
    }
    public boolean consec_fibo()
    {
        Integer a=0, b=1, aux;
        Boolean ok=false;

        while(num>=b && ok==false)
        {
            if(num==b)
            {
                ok=true;
            }
            aux=b;
            b=a+b;
            a=aux;
        }
        if( (ok==true || num==0) && num2==b )
            return true;

        return false;
    }
    public int cmmmc()
    {
        Integer n1=num,n2=num2;
        Integer r;
        while(n2!=0)
        {
            r=n1%n2;
            n1=n2;
            n2=r;
        }

        return (num*num2)/n1;
    }
    public boolean s_cif_equal()
    {
        Integer n1=num,n2=num2;
        Integer s1=0,s2=0;
        while(n1!=0)
        {
            s1=s1+n1%10;
            n1=n1/10;
        }
        while(n2!=0)
        {
            s2=s2+n2%10;
            n2=n2/10;
        }
        if(s1==s2)
            return true;

        return false;
    }
    public boolean nr_cif_pare_equal()
    {
        Integer n1=num,n2=num2;
        Integer ct1=0,ct2=0;
        while(n1!=0)
        {
            if(n1%2==0)
                ct1++;
            n1=n1/10;
        }
        while(n2!=0)
        {
            if(n2%2==0)
                ct2++;
            n2=n2/10;
        }

        if(ct1==ct2)
            return true;

        return false;
    }


}
