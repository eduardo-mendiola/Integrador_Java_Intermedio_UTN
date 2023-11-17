package com.integrador.SisRepInc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.integrador.SisRepInc.dataBase.ConexionDB;
import com.integrador.SisRepInc.model.Tecnico;


public class TecnicoDao {

    // Lista de Técnicos
	public List<Tecnico> listarUsuario() throws SQLException {
		ConexionDB con = new ConexionDB();
		Statement st = con.conectar();

		ResultSet rs = st.executeQuery("SELECT * FROM administradores");

		List<Tecnico> listUsu = new ArrayList<Tecnico>();

		while(rs.next()) {
			Tecnico usu = new Tecnico(rs.getInt("id"),rs.getString("user"),rs.getString("pass"));

			listUsu.add(usu);
		}

		return listUsu;
	}


	// Validar usuario - Login -
	public boolean validarUsu(String user, String pass) throws SQLException {


		ConexionDB conex = new ConexionDB();
		Statement st = conex.conectar();
		ResultSet rs = st.executeQuery("SELECT * FROM administradores WHERE user='"+user+"' AND pass='"+pass+"'");

		if (rs.next()){
			return true;
		}else{
			return false;
		}


	}


}




