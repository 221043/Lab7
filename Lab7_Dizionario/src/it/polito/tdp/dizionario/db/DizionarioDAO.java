package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DizionarioDAO {

	public List<String> loadParole(int lunghezza){
		Connection conn = DBConnection.getConnection();
		String sql = "select nome from parola where length(nome)=?";
		PreparedStatement st;
		List<String> elenco = new ArrayList<>();
		try{	
			st = conn.prepareStatement(sql);
			st.setInt(1, lunghezza);
			ResultSet res = st.executeQuery();
			while(res.next()){
				elenco.add(res.getString("nome"));
			}
			conn.close();
			return elenco;
		} catch(SQLException e){
			System.out.println("Errore nella ricerca di parole di lunghezza "+lunghezza);
		}
		return elenco;		
	}
	
	public List<String> vicini(String parola){
		int lunghezza = parola.length();
		Connection conn = DBConnection.getConnection();
		String sql = "select nome from parola where length(nome)=?";
		PreparedStatement st;
		List<String> elenco = new ArrayList<>();
		try{	
			st = conn.prepareStatement(sql);
			st.setInt(1, lunghezza);
			ResultSet res = st.executeQuery();
			while(res.next()){
				elenco.add(res.getString("nome"));
			}
			conn.close();
			return elenco;
		} catch(SQLException e){
			System.out.println("Errore nella ricerca di parole di lunghezza "+lunghezza);
		}
		return elenco;		
	}
	
	public List<String> viciniVicini(String parola){
		int lunghezza = parola.length();
		Connection conn = DBConnection.getConnection();
		String sql = "select nome from parola where nome like ? and length(nome)=?";
		PreparedStatement st;
		List<String> elenco = new ArrayList<>();
		try{	
			st = conn.prepareStatement(sql);
			st.setString(1,  parola);
			st.setInt(2, lunghezza);	
			ResultSet res = st.executeQuery();
			while(res.next()){
				elenco.add(res.getString("nome"));
			}
			conn.close();
			return elenco;
		} catch(SQLException e){
			System.out.println("Errore nella ricerca di parole di lunghezzaL "+lunghezza);
		}
		return elenco;		
	}
	
}
