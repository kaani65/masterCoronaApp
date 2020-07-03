package Controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.OracleDsSingleton;
import Model.Person;

import java.util.*;

public class PersonDAO {

	public PersonDAO(){}
	OracleDsSingleton ora = OracleDsSingleton.getInstance();



	public void updateP(Person person, int id){
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();

			String sql;
			sql = ("UPDATE NORONA "
					+ "SET name='" + person.getName()  + "',"
					+ "vorname='" + person.getVorname()) + "',"
					+ "strasse='" + person.getStrasse()  + "',"
					+ "strnummer='" + person.getStrnummer() + "',"
					+ "plz='" + person.getPlz()  + "',"
					+ "ort='" + person.getOrt() + "',"
					+ "land='" + person.getLand() + "',"
					+ "infizierte='" + person.getInfiziert() + "',"
					+ "passwort='" + person.getPw() + "',"
					+ "username='" + person.getUsername() + "' "
					+ "WHERE schlussel='" + id + "'";

			stmt.execute(sql);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public void addP(Person person, int id){
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();

			String sql;
			sql = ("INSERT INTO NORONA (name, vorname, strasse, plz, ort, land, infizierte, strnummer, passwort, username, schlussel)"
					+ " VALUES ('"+ person.getName() + "','"+ person.getVorname() + "','"+ person.getStrasse() + "','"
					+ person.getPlz() + "','"
					+ person.getOrt() + "','"
					+ person.getLand() + "','"
					+ person.getInfiziert() + "','"
					+ person.getStrnummer() + "','"
					+ person.getPw() + "','"
					+ person.getUsername() + "','"
					+ id + "')");

			stmt.execute(sql);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}


	public Person getP(int id){
		Person P = new Person();
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();
			String sql;
			sql = ("SELECT * FROM NORONA WHERE schlussel ='" + id + "'");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){

				int schlussel = id;
				String name = rs.getString("name");
				String vorname = rs.getString("vorname");
				String strasse = rs.getString("strasse");
				int strnummer = rs.getInt("strnummer");
				int plz = rs.getInt("plz");
				String ort = rs.getString("ort");
				String land = rs.getString("land");
				String infiziert = rs.getString("infizierte");
				String pw = rs.getString("passwort");
				String username = rs.getString("username");

				P.setId(id);
				P.setName(name);
				P.setVorname(vorname);
				P.setStrasse(strasse);
				P.setStrnummer(strnummer);
				P.setPlz(plz);
				P.setOrt(ort);
				P.setLand(land);
				P.setInfiziert(infiziert);
				P.setPw(pw);
				P.setUsername(username);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return P;
	}
	public List<Person> searchL(String suche){
		List<Person> matchedPersons = new ArrayList<Person>();
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();
			String sql;
			sql = ("SELECT * FROM NORONA WHERE name LIKE INITCAP('"+ suche +"')"
					+ "OR vorname LIKE INITCAP('"+ suche +"') "
					+ "OR strasse LIKE INITCAP('"+ suche +"')"
					+ "OR ort LIKE INITCAP('"+ suche +"') "
					+ "OR land LIKE INITCAP('"+ suche +"') ");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Person person = new Person();

				person.setId(rs.getInt("schlussel"));
				person.setName(rs.getString("name"));
				person.setVorname(rs.getString("vorname"));
				person.setStrasse(rs.getString("strasse"));
				person.setStrnummer(rs.getInt("strnummer"));
				person.setPlz(rs.getInt("plz"));
				person.setOrt(rs.getString("ort"));
				person.setLand(rs.getString("land"));
				person.setInfiziert(rs.getString("infizierte"));
				person.setPw(rs.getString("passwort"));
				person.setUsername(rs.getString("username"));

				matchedPersons.add(person);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return matchedPersons;
	}

	public boolean checkId(int id){
		boolean exists =false;
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();
			String sql;
			sql = ("SELECT schlussel FROM NORONA WHERE schlussel ='" + id + "'");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){

				int schlussel = rs.getInt("schlussel");
				if (schlussel > 0){
					exists = true;
				}

			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public Person searchP(String input){
		Person P = new Person();
		try {
			Connection con = ora.getConnection();
			Statement stmt = con.createStatement();
			String sql;
			sql = ("SELECT * FROM NORONA WHERE vorname LIKE'" + input + "'" + " OR name='" + input + "'" + " OR ort='" + input + "'" + " OR land='" + input + "'"+ " OR strasse='" + input + "'");
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){

				int schlussel = rs.getInt("schlussel");
				String name = rs.getString("name");
				String vorname = rs.getString("vorname");
				String strasse = rs.getString("strasse");
				int strnummer = rs.getInt("strnummer");
				int plz = rs.getInt("plz");
				String ort = rs.getString("ort");
				String land = rs.getString("land");
				String infiziert = rs.getString("infizierte");
				String pw = rs.getString("passwort");
				String username = rs.getString("username");

				P.setId(schlussel);
				P.setName(name);
				P.setVorname(vorname);
				P.setStrasse(strasse);
				P.setStrnummer(strnummer);
				P.setPlz(plz);
				P.setOrt(ort);
				P.setLand(land);
				P.setInfiziert(infiziert);
				P.setPw(pw);
				P.setUsername(username);

			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return P;
	}


}