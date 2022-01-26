package exercicios;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        String nome;
        Integer idade;
        String endereco;

        System.out.println("CADASTRO UNYLEYA - Tarefa 4.2\n");

        System.out.printf("Digite o Nome:\n ");
        nome = ler.nextLine();
        System.out.printf("Digite a Idade:\n ");
        idade = ler.nextInt();
        ler.nextLine();
        System.out.printf("Digite o Endereço:\n ");
        endereco = ler.nextLine();

        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setIdade(idade);
        p.setEndereço(endereco);

        char insert = 0;
        try {
            System.out.printf("Deseja Efetuar o Cadastro? (S/N):\n ");
            insert = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Character.toUpperCase(insert) == 'S') {
            String url = "jdbc:postgresql://ec2-34-194-171-47.compute-1.amazonaws.com/d53pt4g95nkpa4";
            Properties props = new Properties();
            props.setProperty("user", "bzjcqmdvrqcdoi");
            props.setProperty("password", "14b11a83502db54c41eb8dc6951afeac09a9d29399ce9f9bd2550943cb9eced0");
            props.setProperty("sslmode", "require");

            try {
                try {
                    Class.forName("org.postgresql.Driver");
                    System.out.println("CONECTADO AO SGBD UNYLEYA NA NUVEM - HEROKU\n ");
                } catch (ClassNotFoundException e) {
                    System.out.println("FALHA NA CONEXÃO");
                    e.printStackTrace();
                }

                Connection conn = DriverManager.getConnection(url, props);

                if (Character.toUpperCase(insert) == 'S') {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO pessoa (nome, idade, endereco) VALUES (?, ?, ?)");
                    ps.setString(1, p.getNome());
                    ps.setInt(2, p.getIdade());
                    ps.setString(3, p.getEndereço());
                    ps.executeUpdate();
                    ps.close();
                }

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT nome, idade, endereco FROM pessoa ORDER BY nome");
                while (rs.next()) {
                    System.out.print(" -> NOME: ");
                    System.out.print(((ResultSet) rs).getString(1) + " IDADE: ");
                    System.out.print(((ResultSet) rs).getString(2) + " ENDERECO: ");
                    System.out.println(((ResultSet) rs).getString(3));
                }

                st.close();
                rs.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("FALHA SQL/BD");
                e.printStackTrace();
            }
        }
    }
}
