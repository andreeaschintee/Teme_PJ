package Exercitiul1;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    public static void Afisarea_persoanelor(Connection connection) {
        String sql = "select * from persoane";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                        + rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Afisarea_excursiilor(Connection connection) {
        String sql = "select * from excursii";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println("id=" + rs.getInt(2) + ", destinatia=" + rs.getString(3) + ", anul=" + rs.getObject(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Stergere_excursie(Connection connection,int id){
        String sql="delete from excursii where id_excursie=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            if(nr_randuri>0){
                System.out.println("\nNumar randuri afectate de stergere="+nr_randuri);
            }
            else
                System.out.println("\nNu exista o excursie cu acest id");

        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void Stergere_persoana(Connection connection,int id){
        String sql="delete from persoane where id=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            if(nr_randuri>0){
                System.out.println("\nNumar randuri afectate de stergere="+nr_randuri);
            }
            else
                System.out.println("\nNu exista o persoana cu acest id");

        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void Adaugare_persoana(Connection connection, String nume, int varsta) {
        String sql="insert into persoane(nume,varsta) values (?,?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void Adaugare_excursie(Connection connection,int id, String destinatia, int anul) throws SQLException {
        String sql = "select COUNT(*) from persoane where id=?";
        String sql2="insert into excursii(id_persoana,destinatia,anul) values (?,?,?)";
        ResultSet rs;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1)==1)
            {
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, id);
                ps2.setString(2, destinatia);
                ps2.setObject(3, anul);
                int nr_randuri = ps2.executeUpdate();
                System.out.println("\nNumar randuri afectate de adaugare=" + nr_randuri);
            }
            else
                System.out.println("Persoana aceasta nu exista in baza de date!");
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }

    }

    public static void Afisare_persoane_excursii(Connection connection) {
        String sql = "select p.id, p.nume, p.varsta, e.id_excursie, e.destinatia, e.anul  from persoane p left join excursii e on p.id=e.id_persoana";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                if(rs.getInt(4)==0)
                    System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                            + rs.getInt(3));
                else
                    System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                            + rs.getInt(3)+", id_excursie=" + rs.getInt(4)+", destinatia="+rs.getString(5)+", anul="+rs.getObject(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int get_varsta(Connection connection,int id) {

        String sql="select varsta from persoane where id=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return 0;
    }

    public static void Afisare_excursii_a_persoanei(Connection connection,String nume) {
        String sql1 = "select id from persoane where nume=?";
        String sql2 = "select id_excursie, destinatia, anul  from excursii where id_persoana=?";
        try (PreparedStatement ps=connection.prepareStatement(sql1)) {
            ps.setString(1, nume);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, rs.getInt(1));
                rs = ps2.executeQuery();
                while (rs.next())
                    System.out.println("id=" + rs.getInt(1) + ", destinatia=" + rs.getString(2) + ", anul=" + rs.getObject(3));
            }
            else
                System.out.println("Nu exista o persoana cu acest nume!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Afisare_persoane_din_destinatie(Connection connection,String destinatie) {
        String sql = "select id, nume, varsta from persoane p, excursii e where p.id=e.id_persoana and destinatia=?";
        try (PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1, destinatie);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                        + rs.getInt(3));
                while (rs.next())
                    System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                            + rs.getInt(3));
            }
            else
                System.out.println("Nu exista o destinatie cu acest nume!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Afisare_persoane_din_an(Connection connection,int an) {
        String sql = "select id, nume, varsta from persoane p, excursii e where p.id=e.id_persoana and anul=?";
        try (PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, an);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                        + rs.getInt(3));
                while (rs.next())
                    System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ", varsta="
                            + rs.getInt(3));
            }
            else
                System.out.println("Nu exista excursii in acest an!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, Varsta {
        String url = "jdbc:mysql://localhost:3306/lab8?serverTimezone=Europe/Bucharest";
        Connection connection = DriverManager.getConnection(url,"root","abcd");
        Statement statement = connection.createStatement();
        Scanner scanner= new Scanner(System.in);
        String nume;
        String destinatia;
        Integer opt;
        Integer varsta;
        Integer id;
        Integer anul;

        do {
            System.out.println("0.Iesire");
            System.out.println("1.Adăugarea unei persoane în tabela persoane");
            System.out.println("2.Adăugarea unei excursii în tabela excursii");
            System.out.println("3.Afișarea tuturor persoanelor şi pentru fiecare persoană a excursiilor în care a fos");
            System.out.println("4.Afișarea excursiilor în care a fost o persoană al cărei nume se citește de la tastatură");
            System.out.println("5.Afișarea tuturor persoanelor care au vizitat o anumita destinație");
            System.out.println("6.Afișarea persoanelor care au făcut excursii într-un an introdus ");
            System.out.println("7.Ștergerea unei excursii ");
            System.out.println("8.Ștergerea unei persoane (împreună cu excursiile în care a fost)");
            System.out.println("dati optiunea:");
            opt=scanner.nextInt();

            switch (opt) {
                case 0:
                    System.out.println("Exit");
                    break;
                case 1:
                    System.out.println("Numele persoanei:");
                    nume= scanner.next();
                    try {
                        System.out.println("Varsta persoanei:");
                        varsta = scanner.nextInt();
                        if(varsta<0 || varsta>150)
                            throw new Varsta("Varsta nu e corecta!");
                        Adaugare_persoana(connection, nume, varsta);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Nu sunt cifre!");
                        scanner.nextLine();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    Afisarea_persoanelor(connection);
                    System.out.println("Id-ul persoanei:");
                    id= scanner.nextInt();
                    System.out.println("Destinatia excursiei:");
                    destinatia= scanner.next();
                    try {
                        System.out.println("Anul excursiei:");
                        anul = scanner.nextInt();
                        if(LocalDateTime.now().getYear()-get_varsta(connection,id)>anul && anul > LocalDateTime.now().getYear())
                            throw new AnulExcursiei("An gresit!");
                        Adaugare_excursie(connection, id, destinatia, anul);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Nu sunt cifre!");
                        scanner.nextLine();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    Afisare_persoane_excursii(connection);
                    break;
                case 4:
                    Afisarea_persoanelor(connection);
                    System.out.println("Numele persoanei:");
                    nume= scanner.next();
                    Afisare_excursii_a_persoanei(connection, nume);

                    break;
                case 5:
                    Afisarea_excursiilor(connection);
                    System.out.println("Destinatia excursiei:");
                    destinatia= scanner.next();
                    Afisare_persoane_din_destinatie(connection, destinatia);
                    break;
                case 6:
                    Afisarea_excursiilor(connection);
                    try {
                        System.out.println("Anul excursiei:");
                        anul = scanner.nextInt();
                        if(anul > LocalDateTime.now().getYear())
                            throw new AnulExcursiei("An incorect!");
                        Afisare_persoane_din_an(connection, anul);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Nu sunt cifre!");
                        scanner.nextLine();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    Afisarea_excursiilor(connection);
                    System.out.println("Id-ul excursiei:");
                    id= scanner.nextInt();
                    Stergere_excursie(connection, id);
                    break;
                case 8:
                    Afisarea_persoanelor(connection);
                    System.out.println("Id-ul persoanei:");
                    id= scanner.nextInt();
                    Stergere_persoana(connection, id);
                    break;
                default:
                    System.out.println("Optiunea nu exista!");
                    break;
            }


        }while(opt!=0);
        connection.close();
        statement.close();
    }
}
