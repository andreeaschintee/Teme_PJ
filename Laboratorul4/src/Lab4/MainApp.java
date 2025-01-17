package Lab4;

import Lab4.Echipament;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void afisare_echipamente(List<Echipament> echipamente)
    {
        for(int i=0;i<echipamente.size();i++)
            System.out.println((i+1)+") "+echipamente.get(i).toString());
    }
    public static void afisare_imprimante(List<Echipament> echipamente)
    {
        int ct=1;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.IMPRIMANTA)
            {
                System.out.println(ct+") "+echipamente.get(i).toString());
                ct++;
            }
    }
    public static void afisare_copiatoare(List<Echipament> echipamente)
    {
        int ct=1;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.COPIATOR) {
                System.out.println(ct + ") " + echipamente.get(i).toString());
                ct++;
            }
    }
    public static void afisare_sistem_de_calcul(List<Echipament> echipamente)
    {
        int ct=1;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.SISTEM_DE_CALCUL) {
                System.out.println(ct+ ") " + echipamente.get(i).toString());
                ct++;
            }
    }
    public static Imprimanta get_imprimanta(List<Echipament> echipamente, int nr)
    {
        int ct=0;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.IMPRIMANTA) {
                ct++;
                if(ct==nr) {
                    return (Imprimanta) echipamente.get(i);
                }
            }
        return null;
    }
    public static Copiator get_copiator(List<Echipament> echipamente, int nr)
    {
        int ct=0;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.COPIATOR) {
                ct++;
                if(ct==nr) {
                    return (Copiator) echipamente.get(i);
                }
            }
        return null;
    }
    public static Sistem_de_calcul get_sistem_de_calcul(List<Echipament> echipamente, int nr)
    {
        int ct=0;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getTipuri()==Tipuri.SISTEM_DE_CALCUL) {
                ct++;
                if(ct==nr) {
                    return (Sistem_de_calcul) echipamente.get(i);
                }
            }
        return null;
    }

    public static void afisare_echip_vandute(List<Echipament> echipamente)
    {
        int ct=1;
        for(int i=0;i<echipamente.size();i++)
            if(echipamente.get(i).getSituatie()==Situatii.VANDUT) {
                System.out.println(ct+ ") " + echipamente.get(i).toString());
                ct++;
            }
    }

    static void serializare(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object deserializare(String fis) {
        try {
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o=ois.readObject();
            ois.close();
            f.close();
            return o;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Echipament> echipamente=new ArrayList<Echipament>();
        Scanner scanner= new Scanner(System.in);
        Scanner scanner_f = new Scanner(new File("echipamente.txt"));
        int situatie_nr;
        int opt;
        String[] linie;
        String denumire;
        int nr;
        float pret;
        Zona_mag zona_mag;
        Situatii situatie;
        Tipuri tip;
        int p_ton;
        Format format;
        int ppm;
        String rezolutie;
        int p_car;
        Mod_tiparire mod_tip;
        String tip_mon;
        float vit_proc;
        int c_hdd;
        OS os;
        while(scanner_f.hasNextLine())
        {
            linie = scanner_f.nextLine().split(";");
            denumire = linie[0];
            nr = Integer.parseInt(linie[1]);
            pret = Float.parseFloat(linie[2]);
            zona_mag = Zona_mag.valueOf(linie[3].toUpperCase());
            situatie = Situatii.valueOf(linie[4].toUpperCase());
            tip = Tipuri.valueOf(linie[5].toUpperCase());
            switch(tip)
            {
                case COPIATOR:
                    p_ton = Integer.parseInt(linie[6]);
                    format = Format.valueOf(linie[7].toUpperCase());
                    echipamente.add(new Copiator(denumire,nr,pret,zona_mag,situatie,tip,p_ton,format));
                    break;
                case IMPRIMANTA:
                    ppm = Integer.parseInt(linie[6]);
                    rezolutie = linie[7];
                    p_car = Integer.parseInt(linie[8]);
                    mod_tip = Mod_tiparire.valueOf(linie[9].toUpperCase());
                    echipamente.add(new Imprimanta(denumire,nr,pret,zona_mag,situatie,tip,ppm,rezolutie,p_car,mod_tip));
                    break;
                case SISTEM_DE_CALCUL:
                    tip_mon = linie[6];
                    vit_proc = Float.parseFloat(linie[7]);
                    c_hdd = Integer.parseInt(linie[8]);
                    os = OS.valueOf(linie[9].toUpperCase());
                    echipamente.add(new Sistem_de_calcul(denumire,nr,pret,zona_mag,situatie,tip,tip_mon,vit_proc,c_hdd,os));
                    break;
            }

        }
        do{
            System.out.println("0. Exit");
            System.out.println("1. Afisarea tuturor echipamentelor");
            System.out.println("2. Afisarea imprimantelor");
            System.out.println("3. Afisarea copiatoarelor");
            System.out.println("4. Afisarea sistemelor de calcul");
            System.out.println("5. Modificarea starii in care se afla un echipament");
            System.out.println("6. Setarea unui anumit mod de scriere pt o imprimanta");
            System.out.println("7. Setarea unui format de copiere pentru copiatoare");
            System.out.println("8. Instalarea unui anumit sistem de operare pe un sistem de calcul");
            System.out.println("9. Afisarea echipamentelor vandute");
            System.out.println("10. Serializarea colectiei");
            System.out.println("11. Deserealizarea colectiei");
            System.out.println("Dati optiunea.:");
            opt=scanner.nextInt();

            switch(opt) {
                case 0:
                    System.out.println("Exit");
                    break;
                case 1:
                    afisare_echipamente(echipamente);
                    break;
                case 2:
                    afisare_imprimante(echipamente);
                    break;
                case 3:
                    afisare_copiatoare(echipamente);
                    break;
                case 4:
                    afisare_sistem_de_calcul(echipamente);
                    break;
                case 5:
                    afisare_echipamente(echipamente);
                    System.out.println("Scrieti numarul echipamentului a carui stare doriti sa o modificati:");
                    nr=scanner.nextInt();
                    System.out.println("Alegeti noua stare (0."+Situatii.ACHIZITIONAT+" 1."+Situatii.EXPUS+" 2."+Situatii.VANDUT+")");
                    situatie_nr=scanner.nextInt();
                    situatie=Situatii.values()[situatie_nr];
                    echipamente.get(nr-1).setSituatie(situatie);
                    System.out.println(echipamente.get(nr-1));
                    break;
                case 6:
                    afisare_imprimante(echipamente);
                    System.out.println("Scrieti numarul imprimantei a carei mod de tiparire doriti sa il modificati:");
                    nr=scanner.nextInt();
                    System.out.println("Alegeti noua stare (0."+Mod_tiparire.COLOR+" 1."+Mod_tiparire.NEGRU+")");
                    situatie_nr=scanner.nextInt();
                    mod_tip=Mod_tiparire.values()[situatie_nr];
                    get_imprimanta(echipamente,nr).setMod_tip(mod_tip);
                    System.out.println(get_imprimanta(echipamente,nr));

                    break;
                case 7:
                    afisare_copiatoare(echipamente);
                    System.out.println("Scrieti numarul copiatorului a carui format de tiparire doriti sa il modificati:");
                    nr=scanner.nextInt();
                    System.out.println("Alegeti noua stare (0."+Format.A3+" 1."+Format.A4+")");
                    situatie_nr=scanner.nextInt();
                    format=Format.values()[situatie_nr];
                    get_copiator(echipamente,nr).setFormat(format);
                    System.out.println(get_copiator(echipamente,nr));
                    break;
                case 8:
                    afisare_sistem_de_calcul(echipamente);
                    System.out.println("Scrieti numarul sistemului de calcul pe care doriti sa intalati sistemul de operare:");
                    nr=scanner.nextInt();
                    System.out.println("Alegeti noua stare (0."+OS.WINDOWS+" 1."+OS.LINUX+")");
                    situatie_nr=scanner.nextInt();
                    os=OS.values()[situatie_nr];
                    get_sistem_de_calcul(echipamente,nr).setOs(os);
                    System.out.println(get_sistem_de_calcul(echipamente,nr));
                    break;
                case 9:
                    afisare_echip_vandute(echipamente);
                    break;
                case 10:
                    serializare(echipamente,"echip.bin");
                    break;
                case 11:
                    List<Echipament> e;
                    e = (List<Echipament>) deserializare("echip.bin");
                    afisare_echipamente(e);
                    break;
                default:
                    System.out.println("Alta varianta");
                    break;
            }

        }while(opt!=0);
    }
}
